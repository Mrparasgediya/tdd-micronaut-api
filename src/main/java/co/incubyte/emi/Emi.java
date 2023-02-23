package co.incubyte.emi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Emi {
  private final int monthlyPayment;
  private final int totalInterestPaid;
  private boolean loanApproval;

  public Emi(
      @JsonProperty("total_interest_paid") int totalInterestPaid,
      @JsonProperty("total") int monthlyPayment) {
    this.totalInterestPaid = totalInterestPaid;
    this.monthlyPayment = monthlyPayment;
  }

  public int getTotalInterestPaid() {
    return totalInterestPaid;
  }

  public int getMonthlyPayment() {
    return monthlyPayment;
  }

  public boolean getLoanApproval() {
    return loanApproval;
  }

  public void setLoanApproval(boolean loanApproval) {
    this.loanApproval = loanApproval;
  }

  @Override
  public String toString() {
    return "Emi{"
        + "monthlyPayment="
        + monthlyPayment
        + ", totalInterestPaid="
        + totalInterestPaid
        + ", loanApproval="
        + loanApproval
        + '}';
  }
}
