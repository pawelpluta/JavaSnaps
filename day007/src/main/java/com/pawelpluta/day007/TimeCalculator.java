package com.pawelpluta.day007;

import java.time.Duration;

class TimeCalculator {
    Long minutesToSeconds(Long minutes) {
        return Duration.ofMinutes(minutes).toSeconds();
    }

    Long secondsToHours(Long seconds) {
        return Duration.ofSeconds(seconds).toHours();
    }

    Long weeksToDays(Long weeks) {
        return weeks * 7;
    }

    Long daysToWeeks(Long days) {
        return days / 7;
    }

    Long weeksToSeconds(Long weeks) {
        return Duration.ofDays(weeksToDays(weeks)).toSeconds();
    }
}
