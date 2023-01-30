package com.pawelpluta.day029;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CustomerQuery {

    Optional<CustomerAssignmentToBank> getCustomerDataById(String customerId) {
        return Optional.empty();
    }
}
