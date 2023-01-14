package com.pawelpluta.day012

internal data class Person(
        val firstName: String,
        val lastName: String,
        val birthYear: Int,
        val homeAddress: Address,
        val preferredAddress: Address?,
        val siblings: List<Person>) {
    companion object {}

    fun displayName(): String {
        return "$firstName $lastName"
    }

    fun mailingAddress(): Address {
        return preferredAddress ?: homeAddress
    }

    fun olderSiblings(): List<Person> {
        return siblings.filter { it.birthYear < birthYear }
    }
}