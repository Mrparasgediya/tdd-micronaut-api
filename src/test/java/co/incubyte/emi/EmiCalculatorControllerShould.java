package co.incubyte.emi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmiCalculatorControllerShould {
  EmiCalculatorService emiCalculatorService = mock(EmiCalculatorService.class);
  EmiCalculatorController emiCalculatorController =
      new EmiCalculatorController(emiCalculatorService);

  @Test
  @DisplayName("calculate emi")
  public void calculate_emi() {
    // Given
    int loanAmount = 200000;
    float interestRate = 3.5F;
    int monthlyIncome = 3000;
    int durationInYears = 30;
    // When
    Emi calculatedEmi =
        emiCalculatorController.calculate(durationInYears, loanAmount, interestRate, monthlyIncome);
    verify(emiCalculatorService)
        .calculate(durationInYears, loanAmount, interestRate, monthlyIncome);
  }
}
