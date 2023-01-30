package com.pawelpluta.day029;

record CustomerAssignmentToBank(
        String customerId,
        String firstName,
        String lastName,
        String bankCountry,
        String bankPostalCode,
        String bankCity,
        String bankStreet,
        String bankBuildingNumber
) {
}
