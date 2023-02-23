package co.incubyte.car;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://myfakeapi.com/api/cars")
public interface CarClient {
  @Get
  CarsWrapper fetchAll();

  @Get("/{id}")
  CarWrappper fetchCarById(String id);
}
