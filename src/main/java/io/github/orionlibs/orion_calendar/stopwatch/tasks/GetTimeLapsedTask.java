package io.github.orionlibs.orion_calendar.stopwatch.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.stopwatch.Stopwatch;
import io.github.orionlibs.orion_string.StringsService;

public class GetTimeLapsedTask
{
    public String run(Stopwatch stopwatch)
    {
        Assert.notNull(stopwatch, "The stopwatch input cannot be null.");
        long totalMillisecondsElapsed = stopwatch.getNanosecondsElapsed() / Calendar.nanosecondsInAMillisecond;
        long totalSecondsElapsed = stopwatch.getNanosecondsElapsed() / Calendar.nanosecondsInASecond;
        long totalMinutesElapsed = totalSecondsElapsed / Calendar.secondsInAMinute;
        long totalHoursElapsed = totalMinutesElapsed / Calendar.secondsInAMinute;
        totalSecondsElapsed = totalSecondsElapsed - (totalMinutesElapsed * Calendar.secondsInAMinute);
        stopwatch.setTotalMillisecondsElapsed(totalMillisecondsElapsed);
        stopwatch.setTotalSecondsElapsed(totalSecondsElapsed);
        stopwatch.setTotalMinutesElapsed(totalMinutesElapsed);
        stopwatch.setTotalHoursElapsed(totalHoursElapsed);
        String secondsElapsed = StringsService.formatWith2Characters(totalSecondsElapsed);
        String minutesElapsed = StringsService.formatWith2Characters(totalMinutesElapsed);
        String hoursElapsed = StringsService.formatWith2Characters(totalHoursElapsed);
        return hoursElapsed + ":" + minutesElapsed + ":" + secondsElapsed;
    }
}