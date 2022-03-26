package pt.ua.tqs.carsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ua.tqs.carsservice.entities.Car;
import pt.ua.tqs.carsservice.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
//        return carRepository.save(car);
        return null;
    }

    public List<Car> getAllCars() {
//        return carRepository.findAll();
        return null;
    }

    public Optional<Car> getCarDetails(Long carId) {
//        return carRepository.findByCarId(carId).orElse(null);
        return null;
    }
}
