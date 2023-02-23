package co.incubyte.emi;

import jakarta.inject.Singleton;

@Singleton
public class EmiCalculatorService {
  private final EmiClient emiClient;

  public EmiCalculatorService(EmiClient emiClient) {
    this.emiClient = emiClient;
  }

  public Emi calculate(int durationInYears, int loanAmount, float interestRate, int monthlyIncome) {
    Emi fetchedEmi = emiClient.fetch(durationInYears, loanAmount, interestRate);
    fetchedEmi.setLoanApproval(fetchedEmi.getMonthlyPayment() * 2 < monthlyIncome);
    return fetchedEmi;
  }
}
