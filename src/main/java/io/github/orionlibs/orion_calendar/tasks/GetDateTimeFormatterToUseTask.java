package io.github.orionlibs.orion_calendar.tasks;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.format.DateTimeFormatter;

public class GetDateTimeFormatterToUseTask
{
    public static DateTimeFormatter run(String format)
    {

        if(!isBlank(format))
        {
            return DateTimeFormatter.ofPattern(format);
        }
        else
        {
            return DateTimeFormatter.ISO_DATE_TIME;
        }

    }
}
