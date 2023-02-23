package co.incubyte.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceShould {
  @Mock CarClient carClient;
  CarService carService;

  @BeforeEach
  public void init() {
    carService = new CarService(carClient);
  }

  private CarsWrapper generateCars() {
    CarsWrapper carsWrapper = new CarsWrapper(new ArrayList<Car>());
    Car montero = new Car("1", "Mitsubishi", 2002, "Mustang");
    Car gtr = new Car("412", "Nissan", 2011, "Mustang");
    Car mustang = new Car("255", "Ford", 1971, "Mustang");
    Car lancer = new Car("5", "Mitsubishi", 2002, "Mustang");
    carsWrapper.addCars(montero, gtr, mustang, lancer);
    return carsWrapper;
  }

  @Test
  @DisplayName("call car client to get car by id")
  public void call_car_client_to_get_car_by_id() {
    // When
    CarWrappper car = carService.getCarById("1");
    // Then
    Mockito.verify(carClient).fetchCarById("1");
  }

  @Test
  @DisplayName("call car client to get all cars")
  public void call_car_client_to_get_all_cars() {
    // Given
    // When
    CarsWrapper carsWrapper = carService.getCars();
    // Then
    Mockito.verify(carClient).fetchAll();
  }

  @Test
  @DisplayName("return cars of year 2001")
  public void return_cars_of_year_2001() {
    // Given
    Mockito.when(carClient.fetchAll()).thenReturn(generateCars());
    // When
    CarsWrapper fetchedCarsWrapper = carService.getCarsByYear(2001);
    // Then
    List<Car> cars = fetchedCarsWrapper.getCars();
    assertThat(cars).allMatch(car -> car.getYear() == 2001);
  }

  @Test
  @DisplayName("return cars of model Mustang")
  public void return_cars_of_model_mustang() {
    // Given
    Mockito.when(carClient.fetchAll()).thenReturn(generateCars());
    // When
    CarsWrapper carsWrapper = carService.getCarsByModel("Mustang");
    // Then
    List<Car> cars = carsWrapper.getCars();
    assertThat(cars).allMatch(car -> "Mustang".equals(car.getModel()));
  }
}
