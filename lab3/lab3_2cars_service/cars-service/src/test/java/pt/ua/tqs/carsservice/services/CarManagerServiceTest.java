package pt.ua.tqs.carsservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.carsservice.entities.Car;
import pt.ua.tqs.carsservice.repositories.CarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarManagerServiceTest {
    @Mock
    private CarRepository repository;

    @InjectMocks
    private CarManagerService service;

    @Test
    void whenValidId_thenCarShouldBeFound() {
        Car car = new Car("BMW", "E36");
        car.setCarId(0L);

        when(repository.findByCarId(0L)).thenReturn(car);

        Car fromDb = service.getCarDetails(0L);
        assertThat(fromDb.getMaker()).isEqualTo("BMW");

        verify(repository, times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    void whenInvalidId_thenCarShouldNotBeFound() {
        when(repository.findByCarId(-1L)).thenReturn(null);

        Car fromDb = service.getCarDetails(-1L);
        assertThat(fromDb).isNull();

        verify(repository, times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    void given4Cars_whenGetAll_thenReturn4Records() {
        Car bmw = new Car("BMW", "E36");
        Car renault = new Car("Renault", "Megane");
        Car seat = new Car("Seat", "Ibiza");
        Car mercedes = new Car("Mercedes", "Benz");
        List<Car> allCars = Arrays.asList(bmw, renault, seat, mercedes);

        when(repository.findAll()).thenReturn(allCars);

        List<Car> response = service.getAllCars();

        assertThat(response)
                .hasSize(4)
                .extracting(Car::getMaker)
                .contains(bmw.getMaker(), renault.getMaker(), seat.getMaker(), mercedes.getMaker());

        verify(repository, times(1)).findAll();
    }
}
