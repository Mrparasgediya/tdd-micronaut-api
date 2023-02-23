package co.incubyte.emi;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

@Client("https://mocki.io/v1/")
public interface EmiClient {
  @Get("1c29c915-3658-4939-825a-842c06849237")
  Emi fetch(
      @QueryValue int durationInYears, @QueryValue int loanAmount, @QueryValue float interestRate);
}
