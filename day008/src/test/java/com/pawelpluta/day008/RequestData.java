package com.pawelpluta.day008;

import java.math.BigDecimal;

record RequestData(
        Integer integer,
        BigDecimal decimal,
        String text,
        Boolean magicLogicFlag,
        String magicLogicField
) {
    RequestData withInteger(Integer integer) {
        return new RequestData(integer, decimal, text, magicLogicFlag, magicLogicField);
    }

    RequestData withDecimal(BigDecimal decimal) {
        return new RequestData(integer, decimal, text, magicLogicFlag, magicLogicField);
    }

    RequestData withText(String text) {
        return new RequestData(integer, decimal, text, magicLogicFlag, magicLogicField);
    }

    RequestData withMagicLogicFlag(Boolean magicLogicFlag) {
        return new RequestData(integer, decimal, text, magicLogicFlag, magicLogicField);
    }

    RequestData withoutInteger() {
        return new RequestData(null, decimal, text, magicLogicFlag, magicLogicField);
    }

    RequestData withoutDecimal() {
        return new RequestData(integer, null, text, magicLogicFlag, magicLogicField);
    }

    RequestData withoutText() {
        return new RequestData(integer, decimal, "", magicLogicFlag, magicLogicField);
    }

    RequestData withoutMagicLogicField() {
        return new RequestData(integer, decimal, text, magicLogicFlag, "");
    }

    String asJson() {
        return """
                {
                    "integer" : %d,
                    "decimal" : %f,
                    "text" : "%s",
                    "data" : {
                        "magicLogicFlag" : %b,
                        "magicLogicField" : "%s"
                    }
                }
                """.formatted(integer, decimal, text, magicLogicFlag, magicLogicField);
    }


}
