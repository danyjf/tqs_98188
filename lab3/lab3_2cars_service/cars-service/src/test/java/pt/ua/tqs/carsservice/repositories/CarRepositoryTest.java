package pt.ua.tqs.carsservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pt.ua.tqs.carsservice.entities.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    @Test
    void whenFindByExistingId_thenReturnCar() {
        Car car = new Car("BMW", "E36");
        entityManager.persistAndFlush(car);

        Car fromDb = repository.findByCarId(car.getCarId()).orElse(null);
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getMaker()).isEqualTo(car.getMaker());
        assertThat(fromDb.getModel()).isEqualTo(car.getModel());
    }

    @Test
    void whenFindByInvalidId_thenReturnNull() {
        Car fromDb = repository.findByCarId(-1L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void whenFindAll_thenReturnAllCars() {
        Car bmw = new Car("BMW", "E36");
        Car renault = new Car("Renault", "Megane");
        Car seat = new Car("Seat", "Ibiza");
        Car mercedes = new Car("Mercedes", "Benz");

        entityManager.persist(bmw);
        entityManager.persist(renault);
        entityManager.persist(seat);
        entityManager.persist(mercedes);
        entityManager.flush();

        List<Car> allCars = repository.findAll();

        assertThat(allCars)
                .hasSize(4)
                .extracting(Car::getMaker)
                .containsOnly(bmw.getMaker(), renault.getMaker(), seat.getMaker(), mercedes.getMaker());
    }
}
