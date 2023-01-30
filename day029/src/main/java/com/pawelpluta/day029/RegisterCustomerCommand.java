package com.pawelpluta.day029;

record RegisterCustomerCommand(String bankId, CustomerData customer) {
    RegisterCustomerCommand withBankId(String bankId) {
        return new RegisterCustomerCommand(bankId, customer);
    }

    record CustomerData(String customerId, String firstName, String lastName, String country, String postalCode,
                        String city, String street, String buildingNumber) {
        Customer customerAsModel() {
            return new Customer(customerId(), firstName(), lastName(), addressAsModel());
        }

        private Address addressAsModel() {
            return new Address(country, postalCode, city, street, buildingNumber);
        }
    }
}
