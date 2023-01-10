package com.pawelpluta.day008;

import java.math.BigDecimal;

record SampleRequest(
        Integer integer,
        BigDecimal decimal,
        String text,
        NestedRequestData data) {

    record NestedRequestData(
            Boolean magicLogicFlag,
            String magicLogicField) {
    }
}
