package pt.ua.tqs.carsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ua.tqs.carsservice.entities.Car;
import pt.ua.tqs.carsservice.services.CarManagerService;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car response = carManagerService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) {
        return ResponseEntity.ok().body(carManagerService.getCarDetails(carId));
    }
}
