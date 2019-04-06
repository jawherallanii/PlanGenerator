/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.generator.PlanGenerator.resource;

import com.plan.generator.PlanGenerator.DTO.LoanParametersDTO;
import com.plan.generator.PlanGenerator.DTO.RepaymentPlanDTO;
import com.plan.generator.PlanGenerator.exception.InvalidInputException;
import com.plan.generator.PlanGenerator.service.RepaymentPlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Resource of the RepaymentPlanAPI. Exposes the RepaymentPlanService methods
 * through REST
 *
 * @author Jawher
 */
@Controller
@RequestMapping("RepaymentPlan")
public class RepaymentPlanAPI {

    //Dependency Injection of the RepaymentPlanService 
    @Autowired
    RepaymentPlanService repaymentPlanService;

    /**
     * Gets a repayment plan by loan parameters
     * Loan Parameters consist of:
     * The duration
     * The nominal interest rate
     * the borrowed loan amount
     * the start date of the loan
     * @param loanParametersDTO
     * @return 
     */
    @PostMapping(value = "/getRepaymentPlan")
    public ResponseEntity getRepaymentPlan(@RequestBody LoanParametersDTO loanParametersDTO) {
        List<RepaymentPlanDTO> repaymentPlanDTOs;
        try {
            repaymentPlanDTOs = repaymentPlanService.getRepaymentPlan(loanParametersDTO);
            return ResponseEntity.ok(repaymentPlanDTOs);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body("Invalid Input: " + e.getMessage());
        } catch (RuntimeException runTimeException) {
            return ResponseEntity.badRequest().body("Operation Failed");
        }

    }
}
