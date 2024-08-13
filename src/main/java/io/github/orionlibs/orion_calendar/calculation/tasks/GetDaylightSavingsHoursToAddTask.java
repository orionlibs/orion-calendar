package io.github.orionlibs.orion_calendar.calculation.tasks;

import io.github.orionlibs.orion_calendar.Calendar;
import java.time.Instant;
import java.time.ZoneId;
import java.util.TimeZone;

public class GetDaylightSavingsHoursToAddTask
{
    public static int run(ZoneId zoneID)
    {
        if(zoneID != null)
        {
            if(zoneID.getRules().isDaylightSavings(Instant.now()))
            {
                return TimeZone.getTimeZone(zoneID).getDSTSavings() / Calendar.millisecondsInAnHour;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if(TimeZone.getDefault().inDaylightTime(new java.util.Date()))
            {
                return TimeZone.getDefault().getDSTSavings() / Calendar.millisecondsInAnHour;
            }
            else
            {
                return 0;
            }
        }
    }
}