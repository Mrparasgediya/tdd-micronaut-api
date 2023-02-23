package co.incubyte.car;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class CarControllerTest {
  @Client("/")
  @Inject
  HttpClient httpClient;

  @Test
  @DisplayName("should return car by id 1")
  public void should_return_car_by_id_1() {
    // When
    CarWrappper fetchedCarWrapper = httpClient.toBlocking().retrieve("cars/1", CarWrappper.class);
    // Then
    Car fetchedCar = fetchedCarWrapper.getCar();
    String carId = fetchedCar.getId();
    String carMaker = fetchedCar.getCarMaker();
    assertThat(carId).isEqualTo("1");
    assertThat(carMaker).isEqualTo("Mitsubishi");
  }

  @Test
  @DisplayName("should return all cars")
  public void should_return_all_cars() {
    // Given
    // When
    CarsWrapper carsWrapper = httpClient.toBlocking().retrieve("/cars", CarsWrapper.class);
    // Then
    List<Car> cars = carsWrapper.getCars();
    assertThat(cars.size()).isGreaterThan(10);
  }

  @Test
  @DisplayName("should return cars of year 2001")
  public void should_return_cars_of_year_2001() {
    // Given
    // When
    CarsWrapper fetchedCarsWrapper =
        httpClient.toBlocking().retrieve("cars/year/2001", CarsWrapper.class);
    // Then
    List<Car> cars = fetchedCarsWrapper.getCars();
    assertThat(cars.size()).isGreaterThan(10);
  }

  @Test
  @DisplayName("should return cars of model mustang")
  public void should_return_cars_of_model_mustang() {
    // Given
    // When
    CarsWrapper response =
        httpClient.toBlocking().retrieve("/cars/model/Mustang", CarsWrapper.class);
    // Then
    List<Car> fetchedCars = response.getCars();
    assertThat(fetchedCars).allMatch(car -> "Mustang".equals(car.getModel()));
  }
}
