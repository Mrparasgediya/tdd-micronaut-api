package co.incubyte.car;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/cars")
public class CarController {
  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @Get()
  public CarsWrapper getCars() {
    return carService.getCars();
  }

  @Get("/{id}")
  public CarWrappper getCarById(String id) {
    return carService.getCarById(id);
  }

  @Get("/year/{year}")
  public CarsWrapper getCarsByYear(int year) {
    return carService.getCarsByYear(year);
  }

  @Get("/model/{model}")
  public CarsWrapper getCarsByModel(String model) {
    return carService.getCarsByModel(model);
  }
}
