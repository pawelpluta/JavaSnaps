package com.pawelpluta.day012

import com.pawelpluta.day012.AddressFixture.any
import org.apache.commons.lang3.RandomStringUtils
import java.util.*
import kotlin.random.Random

internal object PersonFixture {
    fun Person.Companion.any(): Person {
        return Person(
                "firstName_${RandomStringUtils.randomAlphabetic(8)}",
                "firstName_${RandomStringUtils.randomAlphabetic(8)}",
                Random.nextInt(1970, 2010),
                Address.any(),
                null,
                emptyList())
    }
}