package io.github.orionlibs.orion_calendar.time.tasks;

import io.github.orionlibs.orion_calendar.time.Time;
import io.github.orionlibs.orion_calendar.time.TimeRules;

public class HashCodeForTimeTask
{
    public static int run(Time time)
    {
        TimeRules.isValid(time);
        int defaultPrimeNumberForHashing = 31;
        int hash = 3;
        hash = defaultPrimeNumberForHashing * hash + time.getHours();
        hash = defaultPrimeNumberForHashing * hash + time.getMinutes();
        hash = defaultPrimeNumberForHashing * hash + time.getSeconds();
        return defaultPrimeNumberForHashing * hash + time.getMilliseconds();
    }
}