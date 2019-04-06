/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.generator.PlanGenerator.DTO;

import java.util.Date;

/**
 * Represents the Loan through its parameters
 * 
 * Duration - number of instalments per month
 * Nominal interest rate - the interest against which the repayment is calculated
 * Loan Amount - Total principal amount
 * Start Date - the date of Disbursment/Payouts
 * @author Jawher
 */
public class LoanParametersDTO {

    private int duration;

    private double nominalInterestRate;

    private double loanAmount;

    private Date startDate;

    public LoanParametersDTO() {
    }

    public LoanParametersDTO(int duration, double nominalInterestRate, double loanAmount, Date startDate) {
        this.duration = duration;
        this.nominalInterestRate = nominalInterestRate;
        this.loanAmount = loanAmount;
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getNominalInterestRate() {
        return nominalInterestRate;
    }

    public void setNominalInterestRate(double nominalInterestRate) {
        this.nominalInterestRate = nominalInterestRate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
