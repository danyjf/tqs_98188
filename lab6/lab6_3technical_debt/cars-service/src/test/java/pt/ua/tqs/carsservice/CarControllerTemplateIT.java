package pt.ua.tqs.carsservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import pt.ua.tqs.carsservice.entities.Car;
import pt.ua.tqs.carsservice.repositories.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase
@TestPropertySource(locations = "application-integrationtest.properties")
class CarControllerTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
        Car car = new Car("BMW", "E36");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/cars", car, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("BMW");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        Car bmw = new Car("BMW", "E36");
        Car mercedes = new Car("Mercedes", "Benz");
        repository.save(bmw);
        repository.save(mercedes);
        repository.flush();

        ResponseEntity<List<Car>> response = restTemplate.exchange(
                "/cars",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Car>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("BMW", "Mercedes");
    }

    @Test
    void whenGetCarById_thenReturnCarWithId() {
        Car car = new Car("BMW", "E36");
        repository.saveAndFlush(car);

        ResponseEntity<Car> response = restTemplate.getForEntity("/cars/1", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).isEqualTo("BMW");
    }
}
