package com.pawelpluta.day012

internal data class Address(
        val countryCode: String,
        val postalCode: String,
        val city: String,
        val street: String,
        val houseNumber: String) {
    companion object {}
}