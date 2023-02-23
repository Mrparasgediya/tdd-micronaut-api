package co.incubyte.emi;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class EmiCalculatorTest {

  @Client("/")
  @Inject
  HttpClient httpClient;

  private int monthlyIncome;

  @Test
  @DisplayName("calculate emi")
  public void calculate_emi() {
    // Given
    int loanAmount = 200000;
    float interestRate = 3.5F;
    int monthlyIncome = 3000;
    // When
    Emi calculatedEmi =
        httpClient
            .toBlocking()
            .retrieve(
                "emi-calculator/30?loanAmount="
                    + loanAmount
                    + "&interestRate="
                    + interestRate
                    + "&monthlyIncome="
                    + monthlyIncome,
                Emi.class);
    // Then
    int totalPayment = calculatedEmi.getTotalInterestPaid();
    assertThat(totalPayment).isEqualTo(123312);
  }

  @Test
  @DisplayName("emi should be approved if emi is less then half of monthly income")
  public void emi_should_be_approved_if_emi_is_less_then_half_of_monthly_income() {
    // Given
    int loanAmount = 200000;
    float interestRate = 3.5F;
    int monthlyIncome = 300000;
    // When
    Emi calculatedEmi =
        httpClient
            .toBlocking()
            .retrieve(
                "emi-calculator/30?loanAmount="
                    + loanAmount
                    + "&interestRate="
                    + interestRate
                    + "&monthlyIncome="
                    + monthlyIncome,
                Emi.class);
    // Then
    int totalPayment = calculatedEmi.getTotalInterestPaid();
    boolean approved = calculatedEmi.getLoanApproval();
    assertThat(totalPayment).isEqualTo(123312);
    assertThat(approved).isTrue();
  }
}
