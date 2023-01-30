package com.pawelpluta.day029;

record CustomerRegisteredInBankEvent(
        String customerId,
        String firstName,
        String lastName,
        String bankId,
        Address customerAddress,
        Address bankAddress) implements Event {

}
