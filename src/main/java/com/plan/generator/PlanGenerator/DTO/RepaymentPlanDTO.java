/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.generator.PlanGenerator.DTO;

import java.util.Date;

/**
 * Represents a repayment plan of a loan
 * 
 * borrower payment amount - the annuity of the loan
 * date - the date of the current payment
 * Initial Outstanding Principal - The initially borrowed loan amount 
 * Interest - the interest amount to be paid for the current payment
 * Principal - the amount to pay before interest
 * @author Jawher
 */
public class RepaymentPlanDTO {

    private double borrowerPaymentAmount;

    private Date date;

    private double initialOutstandingPrincipal;

    private double interest;

    private double principal;

    private double remainingOutstandingPrincipal;

    public RepaymentPlanDTO() {
    }

    public RepaymentPlanDTO(double borrowerPaymentAmount, Date date, double initialOutstandingPrincipal, double interest, double principal, double remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public double getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public void setBorrowerPaymentAmount(double borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public void setRemainingOutstandingPrincipal(double remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

}
