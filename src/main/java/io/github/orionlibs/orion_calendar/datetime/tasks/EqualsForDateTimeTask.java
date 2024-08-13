package io.github.orionlibs.orion_calendar.datetime.tasks;

import io.github.orionlibs.orion_calendar.datetime.DateTime;

public class EqualsForDateTimeTask
{
    public static boolean run(DateTime dateTime, Object object)
    {
        if(object == null || dateTime == null || object.getClass() != dateTime.getClass())
        {
            return false;
        }
        else
        {
            DateTime otherDate = (DateTime)object;
            if(doDatesAndTimesMatch(dateTime, otherDate)
                            && (areZonesNull(dateTime, otherDate) || doZonesMatch(dateTime, otherDate)))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }


    private static boolean areZonesNull(DateTime dateTime, DateTime otherDate)
    {
        return dateTime.getZoneID() == null && otherDate.getZoneID() == null;
    }


    private static boolean doDatesAndTimesMatch(DateTime dateTime, DateTime otherDate)
    {
        return dateTime.getDate().equals(otherDate.getDate()) && dateTime.getTime().equals(otherDate.getTime());
    }


    private static boolean doZonesMatch(DateTime dateTime, DateTime otherDate)
    {
        return dateTime.getZoneID().equals(otherDate.getZoneID());
    }
}