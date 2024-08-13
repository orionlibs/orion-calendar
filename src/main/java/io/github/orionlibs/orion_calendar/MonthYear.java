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
public class MonthYear
{
    private String monthYearID;
    private String month;
    private String year;


    public static MonthYear of()
    {
        return MonthYear.builder().build();
    }
}