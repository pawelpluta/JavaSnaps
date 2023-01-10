package com.pawelpluta.day008;

import javax.validation.Valid;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

record SampleRequest(
    @NotNull @PositiveOrZero Integer integer,
    @NotNull @Negative BigDecimal decimal,
    @NotNull @Size(min = 8, max = 16) String text,
    @Valid NestedRequestData data){

    @FlagBasedValueRequirement
    record NestedRequestData(
        Boolean magicLogicFlag,
        String magicLogicField) {
    }
}
