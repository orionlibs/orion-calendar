package io.github.orionlibs.orion_calendar.tasks;

import io.github.orionlibs.orion_calendar.datetime.DateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortDateTimesTask
{
    public static List<DateTime> run(List<DateTime> dateTimes)
    {
        List<ZonedDateTime> dateTimesAsZonedDateTimes = new ArrayList<>(dateTimes.size());
        dateTimes.forEach(dateTime -> dateTimesAsZonedDateTimes.add(dateTime.toZonedDateTime()));
        Collections.sort(dateTimesAsZonedDateTimes);
        List<DateTime> sortedDateTimes = new ArrayList<>(dateTimes.size());
        dateTimesAsZonedDateTimes.forEach(dateTime -> sortedDateTimes.add(DateTime.of(dateTime)));
        return sortedDateTimes;
    }
}