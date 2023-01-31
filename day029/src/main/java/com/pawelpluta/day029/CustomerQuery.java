package com.pawelpluta.day029;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CustomerQuery {

    private static final String SELECT_BY_CUSTOMER_ID = "select customer_id, first_name, last_name, bank_country, bank_postal_code, bank_city, bank_street, bank_building_number " +
            "from customer_assignment_to_bank where customer_id = ?";
    private final JdbcTemplate jdbcTemplate;

    CustomerQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Optional<CustomerAssignmentToBank> getCustomerDataById(String customerId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                SELECT_BY_CUSTOMER_ID,
                (rs, rowNum) -> new CustomerAssignmentToBank(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ),
                customerId));
    }
}
