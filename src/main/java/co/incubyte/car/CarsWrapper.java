package co.incubyte.car;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarsWrapper {
  private final List<Car> cars;

  public CarsWrapper(@JsonProperty("cars") List<Car> cars) {
    this.cars = cars;
  }

  public List<Car> getCars() {
    return cars;
  }

  public void addCars(Car... cars) {
    this.cars.addAll(List.of(cars));
  }
}
