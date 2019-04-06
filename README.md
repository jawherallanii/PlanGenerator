# Plan Generator

This tool is written in **Java8/Spring Boot**.
This is an app to generate the repayment plan of a borrowed loan. 

The application can be used through http requests (REST API) or by the command line.

**CMD MODE**
You can activate the command line in the application.properties 
In application.properties. Switch the cmd mode like below
```
mode.cmd=true
```
**Loan Parameters**
When using cmd mode. provide the loan parameters in command line.
- duration (number of instalments in months)
- nominal interest rate
- total loan amount ("total principal amount")
- Date of Disbursement/Payout

**Using the CMD Mode, the app generates a json file called RepaymentPlan.json under the "src/main/resources/" in the project hierarchy**

---

**REST MODE**
REST Mode is always activated.

The app exposes a REST API Method  "getRepaymentPlan"

**POST http://localhost:8080/RepaymentPlan/getRepaymentPlan**
A payload has to be provided as the body for the POST http request

**e.g**

```
{
"loanAmount": 5000,
"nominalRate": 5.0,
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}
```

**result JSON**

```
[
 {
 "borrowerPaymentAmount": 219.36,
 "date": "2018-01-01T00:00:00Z",
 "initialOutstandingPrincipal": 5000.00,
 "interest": 20.83,
 "principal": 198.53,
 "remainingOutstandingPrincipal": 4801.47,
 },
 {
 "borrowerPaymentAmount": 219.36,
 "date": "2018-02-01T00:00:00Z",
 "initialOutstandingPrincipal": 4801.47,
 "interest": 20.01,
 "principal": 199.35,
 "remainingOutstandingPrincipal": 4602.12,
 },
...
 {
 "borrowerPaymentAmount": 219.28,
 "date": "2020-01-01T00:00:00Z",
 "initialOutstandingPrincipal": 218.37,
 "interest": 0.91,
 "principal": 218.37,
 "remainingOutstandingPrincipal": 0,
 }
]
```


