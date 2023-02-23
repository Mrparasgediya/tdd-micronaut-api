package co.incubyte.car;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarWrappper {
  private final Car car;

  public CarWrappper(@JsonProperty("Car") Car car) {
    this.car = car;
  }

  public Car getCar() {
    return car;
  }
}
