package com.pawelpluta.day029;

import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
class CustomerRegisteredInBankListener {

    private static final String INSERT_ASSIGNMENT_QUERY = "insert into " +
            "customer_assignment_to_bank(customer_id, first_name, last_name, bank_country, bank_postal_code, bank_city, bank_street, bank_building_number) " +
            "values (?,?,?,?,?,?,?,?)";
    private final JdbcTemplate jdbcTemplate;

    CustomerRegisteredInBankListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void consume(CustomerRegisteredInBankEvent event) {
        jdbcTemplate.update(INSERT_ASSIGNMENT_QUERY, (ps) -> {
                    ps.setString(1, event.customerId());
                    ps.setString(2, event.firstName());
                    ps.setString(3, event.lastName());
                    ps.setString(4, event.bankAddress().country());
                    ps.setString(5, event.bankAddress().postalCode());
                    ps.setString(6, event.bankAddress().city());
                    ps.setString(7, event.bankAddress().street());
                    ps.setString(8, event.bankAddress().buildingNumber());
                }
        );
    }
}
