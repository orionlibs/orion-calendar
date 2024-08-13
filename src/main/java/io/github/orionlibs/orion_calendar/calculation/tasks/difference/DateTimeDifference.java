package io.github.orionlibs.orion_calendar.calculation.tasks.difference;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
class DateTimeDifference
{
    @Builder.Default
    private long numberOfYears = 0L;
    @Builder.Default
    private long numberOfMonths = 0L;
    @Builder.Default
    private long numberOfWeeks = 0L;
    @Builder.Default
    private long numberOfDays = 0L;
    @Builder.Default
    private long numberOfHours = 0L;
    @Builder.Default
    private long numberOfMinutes = 0L;
    @Builder.Default
    private long numberOfSeconds = 0L;
    private List<String> unitsToUse;
}
