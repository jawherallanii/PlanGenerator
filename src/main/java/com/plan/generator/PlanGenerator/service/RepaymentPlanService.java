/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.generator.PlanGenerator.service;

import com.plan.generator.PlanGenerator.DTO.LoanParametersDTO;
import com.plan.generator.PlanGenerator.DTO.RepaymentPlanDTO;
import com.plan.generator.PlanGenerator.exception.InvalidInputException;
import java.util.List;

/**
 * Interface of the RepaymentPlanService, represents the available repayment
 * plan methods
 *
 * @author Jawher
 */
public interface RepaymentPlanService {

    List<RepaymentPlanDTO> getRepaymentPlan(LoanParametersDTO loanParametersDTO) throws InvalidInputException;

}
