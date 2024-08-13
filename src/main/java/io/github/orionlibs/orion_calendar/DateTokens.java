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
public class DateTokens
{
    private int days;
    private int month;
    private int year;


    public static DateTokens of()
    {
        return DateTokens.builder().build();
    }
}