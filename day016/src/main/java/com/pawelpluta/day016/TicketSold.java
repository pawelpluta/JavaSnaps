package com.pawelpluta.day016;

import java.math.BigDecimal;

record TicketSold(String eventId, String performanceId, String person, BigDecimal price) {
}
