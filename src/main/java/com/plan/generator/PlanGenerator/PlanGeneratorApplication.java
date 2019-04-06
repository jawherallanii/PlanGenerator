package com.plan.generator.PlanGenerator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.plan.generator.PlanGenerator.DTO.LoanParametersDTO;
import com.plan.generator.PlanGenerator.DTO.RepaymentPlanDTO;
import com.plan.generator.PlanGenerator.exception.InvalidInputException;
import com.plan.generator.PlanGenerator.service.RepaymentPlanService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanGeneratorApplication implements CommandLineRunner {

    @Value("${mode.cmd}")
    private boolean commandLineMode;

    @Autowired
    RepaymentPlanService repaymentPlanService;

    public static void main(String[] args) {
        SpringApplication.run(PlanGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Command Line Mode Enabled
        if (commandLineMode == true) {
            while (true) {
                try {
                    //Init of command line scanner
                    Scanner scanner = new Scanner(System.in);
                    //--------------------------------------------
                    System.out.println("Provide the loan parameters: ");
                    //Getting the loan amount
                    System.out.println("Enter the loan amount: ");
                    double loanAmount = scanner.nextDouble();
                    //Getting the interest rate
                    System.out.println("Enter the interest rate: ");
                    double interestRate = scanner.nextDouble();
                    //Getting the duration
                    System.out.println("Enter the duration of the loan: ");
                    int duration = scanner.nextInt();
                    String dateFormat = "yyyy-dd-mm";
                    System.out.println("Enter the start date of the loan - (Date Fromat: " + dateFormat + "): ");
                    String date = scanner.next();
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
                    Date startDate = dateFormatter.parse(date);
                    LoanParametersDTO loanParametersDTO = new LoanParametersDTO(duration, interestRate, loanAmount, startDate);
                    List<RepaymentPlanDTO> repaymentPlanDTOs = repaymentPlanService.getRepaymentPlan(loanParametersDTO);

                    //Construct the JSON file
                    String json = new Gson().toJson(repaymentPlanDTOs);
                    FileWriter file = new FileWriter("src/main/resources/RepaymentPlan.json");
                    file.write(json);
                    file.flush();
                    System.out.println("File create succesfully under the file project");
                    break;
                } catch (InputMismatchException | ParseException | InvalidInputException | IOException e) {
                    System.out.println("Check input: seperator for interest rate, loan amount must be , not . ");
                }
            }

        }

    }

}
