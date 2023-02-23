package co.incubyte.emi;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/")
public class EmiCalculatorController {
  private final EmiCalculatorService emiCalculatorService;

  public EmiCalculatorController(EmiCalculatorService emiCalculatorService) {
    this.emiCalculatorService = emiCalculatorService;
  }

  @Get("emi-calculator/{durationInYears}")
  public Emi calculate(
      int durationInYears,
      @QueryValue int loanAmount,
      @QueryValue float interestRate,
      @QueryValue int monthlyIncome) {
    return emiCalculatorService.calculate(durationInYears, loanAmount, interestRate, monthlyIncome);
  }
}
