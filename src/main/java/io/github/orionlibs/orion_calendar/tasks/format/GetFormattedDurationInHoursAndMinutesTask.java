package io.github.orionlibs.orion_calendar.tasks.format;

public class GetFormattedDurationInHoursAndMinutesTask
{
    public static String run(int numberOfMinutes)
    {
        String formattedDuration = "";
        if(numberOfMinutes < 60)
        {
            formattedDuration = Integer.toString(numberOfMinutes) + " minutes";
        }
        else if(numberOfMinutes < 1440)
        {
            int numberOfHours = numberOfMinutes / 60;
            int numberOfMinutesTemp = numberOfMinutes - (numberOfHours * 60);
            formattedDuration = Integer.toString(numberOfHours) + " hours";
            if(numberOfMinutesTemp != 0)
            {
                formattedDuration += " + " + Integer.toString(numberOfMinutesTemp) + " minutes";
            }
        }
        else
        {
            long numberOfDays = numberOfMinutes / 1440;
            long numberOfMinutesLeft = numberOfMinutes - (numberOfDays * 1440);
            long numberOfHours = numberOfMinutesLeft / 60;
            numberOfMinutesLeft -= numberOfHours * 60;
            formattedDuration = Long.toString(numberOfDays) + " days";
            if(numberOfHours != 0)
            {
                formattedDuration += " + " + Long.toString(numberOfHours) + " hours";
            }
            if(numberOfMinutesLeft != 0)
            {
                formattedDuration += " + " + Long.toString(numberOfMinutesLeft) + " minutes";
            }
        }
        return formattedDuration;
    }
}