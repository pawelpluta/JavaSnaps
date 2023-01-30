package com.pawelpluta.day029;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomersIT implements PostgresqlSupport {

    private static final String KNOWN_BANK_ID = "111";

    @Autowired
    CustomerRegistration customerRegistration;
    @Autowired
    CustomerQuery customerQuery;

    @Test
    void registrationOfCustomerShouldPropagateToReadModel() {
        // given
        RegisterCustomerCommand.CustomerData commandCustomerData = someCustomerData();
        RegisterCustomerCommand command = new RegisterCustomerCommand(KNOWN_BANK_ID, commandCustomerData);

        // when
        customerRegistration.register(command);

        // then
        CustomerAssignmentToBank customerData = customerQuery.getCustomerDataById(commandCustomerData.customerId()).get();
        assertEquals(commandCustomerData.customerId(), customerData.customerId());
        assertEquals(commandCustomerData.firstName(), customerData.firstName());
        assertEquals(commandCustomerData.lastName(), customerData.lastName());
        assertEquals("bank 111 country", customerData.bankCountry());
        assertEquals("bank 111 postal_code", customerData.bankPostalCode());
        assertEquals("bank 111 city", customerData.bankCity());
        assertEquals("bank 111 street", customerData.bankStreet());
        assertEquals("bank 111 building_number", customerData.bankBuildingNumber());
    }

    private RegisterCustomerCommand.CustomerData someCustomerData() {
        return new RegisterCustomerCommand.CustomerData(
                "customerId_" + randomAlphabetic(10),
                "firstName_" + randomAlphabetic(10),
                "lastName_" + randomAlphabetic(10),
                "country_" + randomAlphabetic(10),
                "postalCode_" + randomAlphabetic(10),
                "city_" + randomAlphabetic(10),
                "street_" + randomAlphabetic(10),
                "houseNumber_" + randomAlphabetic(10));
    }

}