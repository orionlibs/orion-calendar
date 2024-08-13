package io.github.orionlibs.orion_calendar.date.tasks;

import io.github.orionlibs.orion_calendar.date.Date;

public class EqualsForDateTask
{
    public static boolean run(Date date, Object object)
    {

        if(object == null || date == null || object.getClass() != date.getClass())
        {
            return false;
        }
        else
        {
            Date otherDate = (Date)object;

            if(doYearsMatch(date, otherDate) && doMonthsMatch(date, otherDate) && doDaysMatch(date, otherDate))
            {
                return true;
            }

        }

        return false;
    }


    private static boolean doYearsMatch(Date date, Date otherDate)
    {
        return date.getYear() == otherDate.getYear();
    }


    private static boolean doMonthsMatch(Date date, Date otherDate)
    {
        return date.getMonth() == otherDate.getMonth();
    }


    private static boolean doDaysMatch(Date date, Date otherDate)
    {
        return date.getDay() == otherDate.getDay();
    }
}