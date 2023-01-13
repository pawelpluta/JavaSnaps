package com.pawelpluta.day012

import org.apache.commons.lang3.RandomStringUtils
import java.util.*

internal object AddressFixture {
    fun Address.Companion.any(): Address {
        return Address(
                Locale.getISOCountries()[1],
                "00-000",
                "city_${RandomStringUtils.randomAlphabetic(8)}",
                "street_${RandomStringUtils.randomAlphabetic(8)}",
                "11")
    }
}