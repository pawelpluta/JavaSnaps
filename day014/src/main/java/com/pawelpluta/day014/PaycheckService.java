package com.pawelpluta.day014;

record PaycheckService(
        PayslipCalculator payslip,
        TaxOffice taxOffice,
        PayoutOffice payoutOffice) {

    Payslip pay(EmployeeId id) {
        Payslip employeePayslip = payslip.calculateFor(id);
        taxOffice.pay(id, employeePayslip.taxes());
        payoutOffice.pay(id, employeePayslip.net());
        return employeePayslip;
    }
}
