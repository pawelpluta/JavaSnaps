package com.pawelpluta.day012

import com.pawelpluta.day012.AddressFixture.any
import com.pawelpluta.day012.PersonFixture.any
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PersonTest {

    @Test
    fun displayNameIsComposedFromFirstAndLastnames() {
        // given
        val person = Person.any().copy(firstName = "someFirstName", lastName = "lastName")

        // expect
        assertEquals("someFirstName lastName", person.displayName())
    }

    @Test
    fun shouldReturnPreferredAddressIfAvailable() {
        // given
        val preferredAddress = Address.any()
        val person = Person.any().copy(preferredAddress = preferredAddress)

        // expect
        assertEquals(preferredAddress, person.mailingAddress())
    }

    @Test
    fun shouldReturnHomeAddressIfPreferredAddressWasNotProvided() {
        // given
        val homeAddress = Address.any()
        val person = Person.any().copy(homeAddress = homeAddress, preferredAddress = null)

        // expect
        assertEquals(homeAddress, person.mailingAddress())
    }

    @Test
    fun shouldReturnOlderSiblings() {
        // given
        val siblingBornIn1990 = Person.any().copy(birthYear = 1990)
        val siblingBornIn1995 = Person.any().copy(birthYear = 1995)
        val siblingBornIn2000 = Person.any().copy(birthYear = 2000)
        val person = Person.any().copy(birthYear = 1997, siblings = listOf(siblingBornIn1990, siblingBornIn1995, siblingBornIn2000))

        // expect
        assertEquals(2, person.olderSiblings().size)
        assertTrue(person.olderSiblings().contains(siblingBornIn1990))
        assertTrue(person.olderSiblings().contains(siblingBornIn1995))
    }

    @Test
    fun siblingsOfTheSameAgeAreNotConsideredOlder() {
        // given
        val siblingBornIn1990 = Person.any().copy(birthYear = 1990)
        val person = Person.any().copy(birthYear = 1990, siblings = listOf(siblingBornIn1990))

        // expect
        assertTrue(person.olderSiblings().isEmpty())
    }
}