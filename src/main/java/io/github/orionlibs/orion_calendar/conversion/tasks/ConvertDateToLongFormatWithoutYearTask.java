package io.github.orionlibs.orion_calendar.conversion.tasks;

import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.DateTokens;

public class ConvertDateToLongFormatWithoutYearTask
{
    public static String run(DateTokens dateParts)
    {
        StringBuilder sb = new StringBuilder("");
        sb.append(dateParts.getDays());
        sb.append(" ");
        sb.append(Calendar.monthNumberToAbbreviatedNameMapper.get(dateParts.getMonth()));
        return sb.toString();
    }
}