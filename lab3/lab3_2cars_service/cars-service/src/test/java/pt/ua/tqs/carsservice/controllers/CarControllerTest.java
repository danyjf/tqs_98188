package pt.ua.tqs.carsservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.CoreMatchers.is;
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

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car car = new Car("BMW", "E36");
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = objectMapper.writeValueAsString(car);

        when(service.save(Mockito.any())).thenReturn(car);

        mvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).content(carJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("BMW")))
                .andExpect(jsonPath("$.model", is("E36")));

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

        mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].maker", is("BMW")))
                .andExpect(jsonPath("$[1].maker", is("Renault")))
                .andExpect(jsonPath("$[2].maker", is("Seat")))
                .andExpect(jsonPath("$[3].maker", is("Mercedes")));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void whenGetCarById_thenReturnCarWithId() throws Exception {
        Car car = new Car("BMW", "E36");
        car.setCarId(0L);

        when(service.getCarDetails(car.getCarId())).thenReturn(car);

        mvc.perform(get("/cars/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId", is(0L)))
                .andExpect(jsonPath("$.maker", is("BMW")))
                .andExpect(jsonPath("$.model", is("E36")));
    }
}
