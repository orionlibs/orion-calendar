package io.github.orionlibs.orion_calendar.conversion;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_calendar.Calendar;
import lombok.Getter;

@Getter
public class OrionDuration
{
    private long totalSeconds = 0L;
    private long years = 0L;
    private long months = 0L;
    private long days = 0L;
    private long weeks = 0L;
    private long hours = 0L;
    private long minutes = 0L;
    private long seconds = 0L;


    public OrionDuration(long durationInSeconds)
    {
        setup(durationInSeconds);
    }


    private void setup(long durationInSeconds)
    {
        Assert.isNonNegative(durationInSeconds, "The given durationInSeconds input has to be >= 0");
        totalSeconds = durationInSeconds;
        years = totalSeconds / Calendar.secondsInAYear;
        totalSeconds -= years * Calendar.secondsInAYear;
        months = totalSeconds / Calendar.secondsInAMonth;
        totalSeconds -= months * Calendar.secondsInAMonth;
        days = totalSeconds / Calendar.secondsInADay;
        totalSeconds -= days * Calendar.secondsInADay;
        weeks = days / Calendar.daysInAWeek;
        days -= weeks * Calendar.daysInAWeek;
        hours = totalSeconds / Calendar.secondsInAnHour;
        totalSeconds -= hours * Calendar.secondsInAnHour;
        minutes = totalSeconds / Calendar.secondsInAMinute;
        totalSeconds -= minutes * Calendar.secondsInAMinute;
        seconds = totalSeconds;
    }
}
