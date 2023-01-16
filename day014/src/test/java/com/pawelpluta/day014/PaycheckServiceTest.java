package com.pawelpluta.day014;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaycheckServiceTest {

    @Mock
    private PayslipCalculator payslipCalculatorStub;
    @Mock
    private TaxOffice taxOffice;
    @Mock
    private PayoutOffice payoutOffice;

    @Test
    void employeeTaxesShouldBeSendToTheTaxOfficeOnPayday() {
        // given
        EmployeeId employeeId = new EmployeeId(randomAlphanumeric(16));
        Money taxesAmount = usdOf(752);
        Money netAmount = usdOf(3511);
        Payslip payslip = new Payslip(taxesAmount, netAmount);
        // and
        when(payslipCalculatorStub.calculateFor(employeeId)).thenReturn(payslip);
        PaycheckService paycheckService = new PaycheckService(payslipCalculatorStub, taxOffice, payoutOffice);

        // when
        paycheckService.pay(employeeId);

        // then
        verify(taxOffice, new Times(1)).pay(employeeId, taxesAmount);
    }

    @Test
    void employeeShouldReceivePayment() {
        // given
        EmployeeId employeeId = new EmployeeId(randomAlphanumeric(16));
        Money taxesAmount = usdOf(123);
        Money netAmount = usdOf(823);
        Payslip payslip = new Payslip(taxesAmount, netAmount);
        // and
        when(payslipCalculatorStub.calculateFor(employeeId)).thenReturn(payslip);
        PaycheckService paycheckService = new PaycheckService(payslipCalculatorStub, taxOffice, payoutOffice);

        // when
        paycheckService.pay(employeeId);

        // then
        verify(payoutOffice, new Times(1)).pay(employeeId, netAmount);
    }

    private Money usdOf(Integer amount) {
        return new Money(BigDecimal.valueOf(amount), Currency.getInstance("USD"));
    }

}