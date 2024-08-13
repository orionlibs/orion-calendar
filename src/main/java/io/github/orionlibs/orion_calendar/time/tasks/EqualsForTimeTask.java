package io.github.orionlibs.orion_calendar.time.tasks;

import io.github.orionlibs.orion_calendar.time.Time;

public class EqualsForTimeTask
{
    public static boolean run(Time time, Object object)
    {
        if(object == null || time == null || object.getClass() != time.getClass())
        {
            return false;
        }
        else
        {
            Time otherTime = (Time)object;
            return doHoursMatch(time, otherTime) && doMinutesMatch(time, otherTime) && doSecondsMatch(time, otherTime)
                            && doMillisecondsMatch(time, otherTime);
        }
    }


    private static boolean doHoursMatch(Time time, Time otherTime)
    {
        return time.getHours() == otherTime.getHours();
    }


    private static boolean doMinutesMatch(Time time, Time otherTime)
    {
        return time.getMinutes() == otherTime.getMinutes();
    }


    private static boolean doSecondsMatch(Time time, Time otherTime)
    {
        return time.getSeconds() == otherTime.getSeconds();
    }


    private static boolean doMillisecondsMatch(Time time, Time otherTime)
    {
        return time.getMilliseconds() == otherTime.getMilliseconds();
    }
}