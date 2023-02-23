package co.incubyte.car;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CarService {
  private final CarClient carClient;

  public CarService(CarClient carClient) {
    this.carClient = carClient;
  }

  public CarWrappper getCarById(String id) {
    return carClient.fetchCarById(id);
  }

  public CarsWrapper getCarsByYear(int year) {
    List<Car> fetchedCars = carClient.fetchAll().getCars();
    List<Car> filteredCars =
        fetchedCars.stream().filter(car -> car.getYear() == year).collect(Collectors.toList());
    return new CarsWrapper(filteredCars);
  }

  public CarsWrapper getCars() {
    return carClient.fetchAll();
  }

  public CarsWrapper getCarsByModel(String model) {
    List<Car> cars = carClient.fetchAll().getCars();
    List<Car> filteredCars =
        cars.stream()
            .filter(car -> model.toLowerCase().equals(car.getModel().toLowerCase()))
            .collect(Collectors.toList());
    return new CarsWrapper(filteredCars);
  }
}
