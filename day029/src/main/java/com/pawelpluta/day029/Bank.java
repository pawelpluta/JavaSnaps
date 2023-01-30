package com.pawelpluta.day029;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

record Bank(String id, Address address, List<Customer> customers, List<Event> events) {

    static Bank of(String id, Address address, List<Customer> customers) {
        return new Bank(id, address, customers, emptyList());
    }
    public Bank addCustomer(Customer customer) {
        List<Customer> updatedCustomers = new ArrayList<>(customers);
        List<Event> updatedEvents = new ArrayList<>(events);
        updatedCustomers.add(customer);
        updatedEvents.add(customerRegisteredInBank(customer));
        return new Bank(id, address, updatedCustomers, updatedEvents);
    }

    private Event customerRegisteredInBank(Customer customer) {
        return new CustomerRegisteredInBankEvent(
                customer.customerId(),
                customer.firstName(),
                customer.lastName(),
                id,
                customer.address(),
                address);
    }
}
