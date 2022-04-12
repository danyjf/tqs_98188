package pt.ua.tqs.carsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.tqs.carsservice.entities.Car;
import pt.ua.tqs.carsservice.repositories.CarRepository;

import java.util.List;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarDetails(Long carId) {
        return carRepository.findByCarId(carId);
    }
}
