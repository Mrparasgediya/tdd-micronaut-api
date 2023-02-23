package co.incubyte.emi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmiCalculatorServiceShould {

  EmiClient emiClient = mock(EmiClient.class);
  EmiCalculatorService emiCalculatorService = new EmiCalculatorService(emiClient);

  @Test
  @DisplayName("call emi client")
  public void call_emi_client() {
    // Given
    int loanAmount = 200000;
    float interestRate = 3.5F;
    int monthlyIncome = 3000;
    int durationInYears = 30;
    Emi emi = new Emi(123312, monthlyIncome);
    when(emiClient.fetch(durationInYears, loanAmount, interestRate)).thenReturn(emi);
    // when
    Emi calculatedEmi =
        emiCalculatorService.calculate(durationInYears, loanAmount, interestRate, monthlyIncome);
    Mockito.verify(emiClient).fetch(durationInYears, loanAmount, interestRate);
  }

  @Test
  @DisplayName("emi is approved if emi is less then half of monthly income")
  public void emi_is_approved_if_emi_is_less_then_half_of_monthly_income() {
    // Given
    int loanAmount = 200000;
    float interestRate = 3.5F;
    int monthlyIncome = 30000;
    int durationInYears = 30;
    Emi emi = new Emi(123312, 898);
    when(emiClient.fetch(durationInYears, loanAmount, interestRate)).thenReturn(emi);
    // when
    Emi calculatedEmi =
        emiCalculatorService.calculate(durationInYears, loanAmount, interestRate, monthlyIncome);
    // Then
    Mockito.verify(emiClient).fetch(durationInYears, loanAmount, interestRate);
    boolean approved = calculatedEmi.getLoanApproval();
    assertThat(approved).isTrue();
  }

  @Test
  @DisplayName("emi is not approved if emi is more then half of monthly income")
  public void emi_is_not_approved_if_emi_is_more_then_half_of_monthly_income() {
    // Given
    int loanAmount = 200000;
    float interestRate = 3.5F;
    int monthlyIncome = 500;
    int durationInYears = 30;
    Emi emi = new Emi(123312, monthlyIncome);
    when(emiClient.fetch(durationInYears, loanAmount, interestRate)).thenReturn(emi);
    // when
    Emi calculatedEmi =
        emiCalculatorService.calculate(durationInYears, loanAmount, interestRate, monthlyIncome);
    // Then
    Mockito.verify(emiClient).fetch(durationInYears, loanAmount, interestRate);
    boolean approved = calculatedEmi.getLoanApproval();
    assertThat(approved).isFalse();
  }
}
