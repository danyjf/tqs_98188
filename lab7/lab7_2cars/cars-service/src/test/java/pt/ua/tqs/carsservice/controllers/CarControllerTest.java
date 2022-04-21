package pt.ua.tqs.carsservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ua.tqs.carsservice.entities.Car;
import pt.ua.tqs.carsservice.services.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    private void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car car = new Car("BMW", "E36");
        ObjectMapper objectMapper = new ObjectMapper();

        when(service.save(Mockito.any())).thenReturn(car);

        RestAssuredMockMvc
                .given()
                .header("Content-type", "application/json")
                .and()
                .body(car)
                .when()
                .post("/cars")
                .then()
                .statusCode(201)
                .and()
                .expect(jsonPath("$.maker", is("BMW")))
                .and()
                .expect(jsonPath("$.model", is("E36")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void whenGetCars_thenReturnAllCars() throws Exception {
        Car bmw = new Car("BMW", "E36");
        Car renault = new Car("Renault", "Megane");
        Car seat = new Car("Seat", "Ibiza");
        Car mercedes = new Car("Mercedes", "Benz");

        List<Car> allCars = Arrays.asList(bmw, renault, seat, mercedes);

        when(service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc
                .given()
                .when()
                .get("/cars")
                .then()
                .body("maker", hasItems("BMW", "Renault", "Seat", "Mercedes"))
                .and()
                .body("maker", hasSize(4));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void whenGetCarById_thenReturnCarWithId() throws Exception {
        Car car = new Car("BMW", "E36");
        car.setCarId(0L);

        when(service.getCarDetails(car.getCarId())).thenReturn(car);

        RestAssuredMockMvc
                .given()
                .when()
                .get("/cars/0")
                .then()
                .body("maker", equalTo("BMW"));
    }
}
