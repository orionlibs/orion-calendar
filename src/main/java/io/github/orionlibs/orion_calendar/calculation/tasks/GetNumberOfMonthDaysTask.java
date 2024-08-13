package io.github.orionlibs.orion_calendar.calculation.tasks;

import io.github.orionlibs.orion_assert.InvalidArgumentException;
import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.DateTimeRules;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetNumberOfMonthDaysTask
{
    public static int run(Date date) throws InvalidArgumentException
    {
        DateRules.isValid(date);
        Calendar calendar = new GregorianCalendar(date.getYear(), date.getMonth() - 1, date.getDay());
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static int run(DateTime date) throws InvalidArgumentException
    {
        DateTimeRules.isValidIgnoringZoneID(date);
        DateRules.isValid(date.getDate());
        return run(date.getDate());
    }
}