package co.incubyte.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarControllerShould {

  @Mock CarService carService;
  CarController carController;

  @BeforeEach
  public void init() {
    carController = new CarController(carService);
  }

  @Test
  @DisplayName("should call service")
  public void should_call_service() {
    CarWrappper carWrapper = carController.getCarById("1");
    verify(carService).getCarById("1");
  }

  @Test
  @DisplayName("should call service to get all cars")
  public void should_call_service_to_get_all_cars() {
    // Given
    // When
    CarsWrapper carsWrapper = carController.getCars();
    // Then
    Mockito.verify(carService).getCars();
  }

  @Test
  @DisplayName("should  call service to get all cars of year")
  public void should_call_service_to_get_all_cars_of_year() {
    // Given
    // When
    CarsWrapper carsWrapper = carController.getCarsByYear(2001);
    // Then
    Mockito.verify(carService).getCarsByYear(2001);
  }

  @Test
  @DisplayName("should call service to get cars of model Mustang")
  public void should_call_service_to_get_cars_of_model_mustang() {
    // Given
    // When
    CarsWrapper carsWrapper = carController.getCarsByModel("Mustang");
    // Then
    Mockito.verify(carService).getCarsByModel("Mustang");
  }
}
