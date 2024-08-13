package io.github.orionlibs.orion_calendar.tasks;

import static org.apache.commons.lang3.StringUtils.isBlank;

import io.github.orionlibs.orion_calendar.date.DateRules;
import io.github.orionlibs.orion_calendar.date.InvalidDateException;
import java.time.format.DateTimeFormatter;

public class GetDateFormatterToUseTask
{
    public static DateTimeFormatter run(String dateString, String format) throws InvalidDateException
    {
        DateRules.isValid(dateString);
        if(!isBlank(format))
        {
            return DateTimeFormatter.ofPattern(format);
        }
        else
        {
            if(dateString.indexOf("-") >= 0)
            {
                return DateTimeFormatter.ofPattern("uuuu-MM-dd");
            }
            else
            {
                return DateTimeFormatter.ofPattern("uuuu/MM/dd");
            }
        }
    }
}
