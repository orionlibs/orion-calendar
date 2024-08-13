package io.github.orionlibs.orion_calendar.tasks.format;

import io.github.orionlibs.orion_assert.Assert;

public class FormatDifferenceBetweenTwoDateTimesBasedOnUnitsTask
{
    private Long[] durationAsList;
    private String units;


    public String run(Long[] durationAsList, String units)
    {
        this.durationAsList = durationAsList;
        this.units = units;
        String[] unitsToUse = getUnitsToUse();
        Assert.lengthsMatch("The number of duration list tokens mismatch the number of units provided.", durationAsList, unitsToUse);
        String formattedDuration = "";
        for(int i = 0; i < unitsToUse.length; i++)
        {
            if("y".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "year");
            }
            else if("M".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "month");
            }
            else if("W".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "week");
            }
            else if("d".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "day");
            }
            else if("h".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "hour");
            }
            else if("m".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "minute");
            }
            else if("s".equals(unitsToUse[i]))
            {
                formattedDuration += formatDurationString(i, "second");
            }
        }
        return formattedDuration;
    }


    private String[] getUnitsToUse()
    {
        if(units != null && !units.isEmpty())
        {
            return units.split("");
        }
        else
        {
            return new String[]
                            {"y", "M", "W", "d", "h", "m", "s"};
        }
    }


    private String formatDurationString(int i, String unit)
    {
        String formattedDuration = durationAsList[i] + " " + unit;
        if(durationAsList[i] != 1)
        {
            formattedDuration += "s";
        }
        if((i + 1) < durationAsList.length && durationAsList[i + 1] != null)
        {
            if(i == durationAsList.length - 2)
            {
                formattedDuration += " and ";
            }
            else if(i < durationAsList.length - 2)
            {
                formattedDuration += ", ";
            }
        }
        return formattedDuration;
    }
}
