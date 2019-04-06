/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.generator.PlanGenerator.service;

import com.plan.generator.PlanGenerator.DTO.LoanParametersDTO;
import com.plan.generator.PlanGenerator.DTO.RepaymentPlanDTO;
import com.plan.generator.PlanGenerator.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Represents the service to handle all operations for a Repayment Plan
 *
 * @author Jawher
 */
@Service
public class RepaymentPlanServiceImpl implements RepaymentPlanService {

    /**
     * Spring service method to get a repayment plan by the provided loan
     * parameters
     *
     * @param loanParametersDTO
     * @return
     * @throws InvalidInputException
     */
    @Override
    public List<RepaymentPlanDTO> getRepaymentPlan(LoanParametersDTO loanParametersDTO) throws InvalidInputException {
        //List of RepaymentPlanDTO, represents the resulting repayment plan itself
        List<RepaymentPlanDTO> repaymentPlanDTOs = new ArrayList();
        // Validating input values
        if (loanParametersDTO.getLoanAmount() <= 0) {
            throw new InvalidInputException("The Loan Amount value is negative. Cannot proceed");
        } else if (loanParametersDTO.getDuration() <= 0) {
            throw new InvalidInputException("The Duration value is negative. Cannot proceed");
        } else if (loanParametersDTO.getNominalInterestRate() <= 0) {
            throw new InvalidInputException("The Interest Rate value is negative. Cannot proceed");
        }
        //Getting initial values from the loan params
        double initialOutstandingPrincipal = loanParametersDTO.getLoanAmount();
        double nominalInterestRate = loanParametersDTO.getNominalInterestRate() / 100;
        double duration = loanParametersDTO.getDuration();
        double period = duration;
        //Calculating the monthly interest rate using the nominal interest rate(yearly interest rate)
        double monthlyInterestRate = (nominalInterestRate * 30) / 360;

        //Initial RepaymentPlanDTO object for the next recursive call to function 
        RepaymentPlanDTO repaymentPlanDTO = new RepaymentPlanDTO();
        repaymentPlanDTO.setDate(null);
        repaymentPlanDTO.setInitialOutstandingPrincipal(initialOutstandingPrincipal);
        //setting the first remaining outstanding principal as the initial outstanding principal
        repaymentPlanDTO.setRemainingOutstandingPrincipal(initialOutstandingPrincipal);

        while (period > 0) {
            repaymentPlanDTO = RepaymentPlanServiceImpl.calculateRepaymentPlanRecursively(period, monthlyInterestRate, repaymentPlanDTO.getRemainingOutstandingPrincipal(), repaymentPlanDTO.getDate(), loanParametersDTO.getStartDate());
            repaymentPlanDTOs.add(repaymentPlanDTO);
            period--;
        }
        return repaymentPlanDTOs;
    }

    /**
     * Function to recursively calculate the repayment plan
     *
     * @param period
     * @param monthlyInterestRate
     * @param initialOutstandingPrincipal
     * @param paymentDate
     * @param startDate
     * @return
     */
    private static RepaymentPlanDTO calculateRepaymentPlanRecursively(double period, double monthlyInterestRate,
            double initialOutstandingPrincipal, Date paymentDate, Date startDate) {

        //calculating current payment annuity
        double currentAnnuity = round(initialOutstandingPrincipal / ((1 - (Math.pow(1 + monthlyInterestRate, (-period)))) / monthlyInterestRate), 2);
        //calculating current payment interest
        double currentInterest = round(monthlyInterestRate * initialOutstandingPrincipal, 2);
        double currentPrincipal;
        // if, calculated interest amount exceeds the initial outstanding
        //principal amount, take initial outstanding principal amount instead
        if (currentInterest > initialOutstandingPrincipal) {
            currentPrincipal = initialOutstandingPrincipal;
        } else {
            //getting current payment principal
            currentPrincipal = round(currentAnnuity - currentInterest, 2);
        }
        //calculating the remaining outstanding principal
        double remainingOutstandingPrincipal = round(initialOutstandingPrincipal - currentPrincipal, 2);
        //If the payment date is the start date means first iteration, we don't add a month
        if (paymentDate == null) {
            paymentDate = startDate;
        } else {
            //Init of the Calendar object
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(paymentDate);
            //adding one month to the current payment date
            calendar.add(Calendar.MONTH, 1);
            paymentDate = calendar.getTime();
        }
        // Constructing the RepaymentPlanDTO object
        RepaymentPlanDTO repaymentPlanDTO = new RepaymentPlanDTO();
        repaymentPlanDTO.setDate(paymentDate);
        repaymentPlanDTO.setPrincipal(currentPrincipal);
        repaymentPlanDTO.setInterest(currentInterest);
        repaymentPlanDTO.setInitialOutstandingPrincipal(initialOutstandingPrincipal);
        repaymentPlanDTO.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);
        repaymentPlanDTO.setBorrowerPaymentAmount(currentAnnuity);

        return repaymentPlanDTO;
    }

    /**
     * Calculate round of a double value againt the provided decimal precision
     *
     * @param value
     * @param decimalPrecision
     * @return
     */
    public static double round(double value, int decimalPrecision) {
        double scale = Math.pow(10, decimalPrecision);
        return Math.round(value * scale) / scale;
    }
}
