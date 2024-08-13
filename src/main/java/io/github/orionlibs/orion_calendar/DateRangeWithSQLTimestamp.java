package io.github.orionlibs.orion_calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DateRangeWithSQLTimestamp
{
    private SQLTimestamp startDate;
    private SQLTimestamp endDate;


    public static DateRangeWithSQLTimestamp of()
    {
        return DateRangeWithSQLTimestamp.builder().build();
    }
}