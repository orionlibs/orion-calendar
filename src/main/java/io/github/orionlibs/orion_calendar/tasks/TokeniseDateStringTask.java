package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.date.DateRules;
import io.github.orionlibs.orion_calendar.date.InvalidDateException;

public class TokeniseDateStringTask
{
    public static String[] run(String date) throws InvalidDateException
    {
        DateRules.isValid(date);
        if(date.indexOf("-") >= 0)
        {
            return date.split("-");
        }
        else if(date.indexOf("/") >= 0)
        {
            return date.split("/");
        }
        else
        {
            return null;
        }
    }
}