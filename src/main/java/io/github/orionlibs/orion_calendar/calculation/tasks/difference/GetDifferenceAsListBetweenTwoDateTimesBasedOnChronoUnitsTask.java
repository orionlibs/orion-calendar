package io.github.orionlibs.orion_calendar.calculation.tasks.difference;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_assert.InvalidArgumentException;
import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.conversion.OrionDuration;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import java.time.Duration;
import java.util.List;

public class GetDifferenceAsListBetweenTwoDateTimesBasedOnChronoUnitsTask
{
    private long numberOfYears = 0L;
    private long numberOfMonths = 0L;
    private long numberOfWeeks = 0L;
    private long numberOfDays = 0L;
    private long numberOfHours = 0L;
    private long numberOfMinutes = 0L;
    private long numberOfSeconds = 0L;
    private List<String> unitsToUseTemp;


    public Long[] run(String units, DateTime startDateTime, DateTime endDateTime) throws InvalidArgumentException
    {
        Assert.notEmpty(units, "No units provided.");
        String[] unitsToUse =
        {"y", "M", "W", "d", "h", "m", "s"};
        this.unitsToUseTemp = GetChronoUnitsToUseTask.run(units, unitsToUse);
        DateTime startDateTimeCopy = startDateTime.getCopy();
        Duration durationInUnits = Duration.between(startDateTimeCopy.toZonedDateTime(), endDateTime.toZonedDateTime());
        OrionDuration duration = new OrionDuration(Math.abs(durationInUnits.getSeconds()));

        if(unitsToUseTemp.contains("y"))
        {
            numberOfYears = duration.getYears();

            if(unitsToUseTemp.contains("M"))
            {
                numberOfMonths = duration.getMonths();

                if(unitsToUseTemp.contains("W"))
                {
                    numberOfWeeks = duration.getWeeks();

                    if(unitsToUseTemp.contains("d"))
                    {
                        numberOfDays = duration.getDays();

                        if(unitsToUseTemp.contains("h"))
                        {
                            numberOfHours = duration.getHours();

                            if(unitsToUseTemp.contains("m"))
                            {
                                numberOfMinutes = duration.getMinutes();

                                if(unitsToUseTemp.contains("s"))
                                {
                                    numberOfSeconds = duration.getSeconds();
                                }

                            }
                            else if(unitsToUseTemp.contains("s"))
                            {
                                numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                            }

                        }
                        else if(unitsToUseTemp.contains("m"))
                        {
                            numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                            if(unitsToUseTemp.contains("s"))
                            {
                                numberOfSeconds = duration.getSeconds();
                            }

                        }
                        else if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                        }

                    }
                    else if(unitsToUseTemp.contains("h"))
                    {
                        numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay);

                        if(unitsToUseTemp.contains("m"))
                        {
                            numberOfMinutes = duration.getMinutes();

                            if(unitsToUseTemp.contains("s"))
                            {
                                numberOfSeconds = duration.getSeconds();
                            }

                        }
                        else if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                        }

                    }
                    else if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay);

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay);
                    }

                }
                else if(unitsToUseTemp.contains("d"))
                {
                    numberOfDays = duration.getDays() + (duration.getWeeks() * Calendar.daysInAWeek);

                    if(unitsToUseTemp.contains("h"))
                    {
                        numberOfHours = duration.getHours();

                        if(unitsToUseTemp.contains("m"))
                        {
                            numberOfMinutes = duration.getMinutes();

                            if(unitsToUseTemp.contains("s"))
                            {
                                numberOfSeconds = duration.getSeconds();
                            }

                        }
                        else if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                        }

                    }
                    else if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                    }

                }
                else if(unitsToUseTemp.contains("h"))
                {
                    numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek);

                    if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes();

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                    }

                }
                else if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.minutesInADay);

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay)
                                    + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.secondsInADay);
                }

            }
            else if(unitsToUseTemp.contains("W"))
            {
                numberOfWeeks = duration.getWeeks() + (duration.getMonths() * Calendar.daysInAMonth / Calendar.daysInAWeek);

                if(unitsToUseTemp.contains("d"))
                {
                    numberOfDays = duration.getDays();

                    if(unitsToUseTemp.contains("h"))
                    {
                        numberOfHours = duration.getHours();

                        if(unitsToUseTemp.contains("m"))
                        {
                            numberOfMinutes = duration.getMinutes();

                            if(unitsToUseTemp.contains("s"))
                            {
                                numberOfSeconds = duration.getSeconds();
                            }

                        }
                        else if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                        }

                    }
                    else if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                    }

                }
                else if(unitsToUseTemp.contains("h"))
                {
                    numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay);

                    if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes();

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                    }

                }
                else if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay);

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay);
                }

            }
            else if(unitsToUseTemp.contains("d"))
            {
                numberOfDays = duration.getDays() + (duration.getWeeks() * Calendar.daysInAWeek) + (duration.getMonths() * Calendar.daysInAMonth);

                if(unitsToUseTemp.contains("h"))
                {
                    numberOfHours = duration.getHours();

                    if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes();

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                    }

                }
                else if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                }

            }
            else if(unitsToUseTemp.contains("h"))
            {
                numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek) + (duration.getMonths() * Calendar.daysInAMonth * Calendar.hoursInADay);

                if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes();

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                }

            }
            else if(unitsToUseTemp.contains("m"))
            {
                numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.minutesInADay)
                                + (duration.getMonths() * Calendar.daysInAMonth * Calendar.minutesInADay);

                if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds();
                }

            }
            else if(unitsToUseTemp.contains("s"))
            {
                numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay)
                                + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.secondsInADay) + (duration.getMonths() * Calendar.daysInAMonth * Calendar.secondsInADay);
            }

        }
        else if(unitsToUseTemp.contains("M"))
        {
            numberOfMonths = duration.getMonths() + (duration.getYears() * Calendar.monthsInAYear);

            if(unitsToUseTemp.contains("W"))
            {
                numberOfWeeks = duration.getWeeks();

                if(unitsToUseTemp.contains("d"))
                {
                    numberOfDays = duration.getDays();

                    if(unitsToUseTemp.contains("h"))
                    {
                        numberOfHours = duration.getHours();

                        if(unitsToUseTemp.contains("m"))
                        {
                            numberOfMinutes = duration.getMinutes();

                            if(unitsToUseTemp.contains("s"))
                            {
                                numberOfSeconds = duration.getSeconds();
                            }

                        }
                        else if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                        }

                    }
                    else if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                    }

                }
                else if(unitsToUseTemp.contains("h"))
                {
                    numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay);

                    if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes();

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                    }

                }
                else if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay);

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay);
                }

            }
            else if(unitsToUseTemp.contains("d"))
            {
                numberOfDays = duration.getDays() + (duration.getWeeks() * Calendar.daysInAWeek);

                if(unitsToUseTemp.contains("h"))
                {
                    numberOfHours = duration.getHours();

                    if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes();

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                    }

                }
                else if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                }

            }
            else if(unitsToUseTemp.contains("h"))
            {
                numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek);

                if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes();

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                }

            }
            else if(unitsToUseTemp.contains("m"))
            {
                numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.minutesInADay);

                if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds();
                }

            }
            else if(unitsToUseTemp.contains("s"))
            {
                numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay)
                                + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.secondsInADay);
            }

        }
        else if(unitsToUseTemp.contains("W"))
        {
            numberOfWeeks = duration.getWeeks() + (duration.getMonths() * Calendar.daysInAMonth / Calendar.daysInAWeek) + (duration.getYears() * Calendar.daysInAYear / Calendar.daysInAWeek);

            if(unitsToUseTemp.contains("d"))
            {
                numberOfDays = duration.getDays();

                if(unitsToUseTemp.contains("h"))
                {
                    numberOfHours = duration.getHours();

                    if(unitsToUseTemp.contains("m"))
                    {
                        numberOfMinutes = duration.getMinutes();

                        if(unitsToUseTemp.contains("s"))
                        {
                            numberOfSeconds = duration.getSeconds();
                        }

                    }
                    else if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                    }

                }
                else if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
                }

            }
            else if(unitsToUseTemp.contains("h"))
            {
                numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay);

                if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes();

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                }

            }
            else if(unitsToUseTemp.contains("m"))
            {
                numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay);

                if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds();
                }

            }
            else if(unitsToUseTemp.contains("s"))
            {
                numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay);
            }

        }
        else if(unitsToUseTemp.contains("d"))
        {
            numberOfDays = duration.getDays() + (duration.getWeeks() * Calendar.daysInAWeek) + (duration.getMonths() * Calendar.daysInAMonth) + (duration.getYears() * Calendar.daysInAYear);

            if(unitsToUseTemp.contains("h"))
            {
                numberOfHours = duration.getHours();

                if(unitsToUseTemp.contains("m"))
                {
                    numberOfMinutes = duration.getMinutes();

                    if(unitsToUseTemp.contains("s"))
                    {
                        numberOfSeconds = duration.getSeconds();
                    }

                }
                else if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
                }

            }
            else if(unitsToUseTemp.contains("m"))
            {
                numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour);

                if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds();
                }

            }
            else if(unitsToUseTemp.contains("s"))
            {
                numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour);
            }

        }
        else if(unitsToUseTemp.contains("h"))
        {
            numberOfHours = duration.getHours() + (duration.getDays() * Calendar.hoursInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek) + (duration.getMonths() * Calendar.daysInAMonth * Calendar.hoursInADay)
                            + (duration.getYears() * Calendar.daysInAYear * Calendar.hoursInADay);

            if(unitsToUseTemp.contains("m"))
            {
                numberOfMinutes = duration.getMinutes();

                if(unitsToUseTemp.contains("s"))
                {
                    numberOfSeconds = duration.getSeconds();
                }

            }
            else if(unitsToUseTemp.contains("s"))
            {
                numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute);
            }

        }
        else if(unitsToUseTemp.contains("m"))
        {
            numberOfMinutes = duration.getMinutes() + (duration.getHours() * Calendar.minutesInAnHour) + (duration.getDays() * Calendar.minutesInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.minutesInADay)
                            + (duration.getMonths() * Calendar.daysInAMonth * Calendar.minutesInADay) + (duration.getYears() * Calendar.daysInAYear * Calendar.minutesInADay);

            if(unitsToUseTemp.contains("s"))
            {
                numberOfSeconds = duration.getSeconds();
            }

        }
        else if(unitsToUseTemp.contains("s"))
        {
            numberOfSeconds = duration.getSeconds() + (duration.getMinutes() * Calendar.secondsInAMinute) + (duration.getHours() * Calendar.secondsInAnHour) + (duration.getDays() * Calendar.secondsInADay) + (duration.getWeeks() * Calendar.hoursInADay * Calendar.daysInAWeek * Calendar.secondsInADay)
                            + (duration.getMonths() * Calendar.daysInAMonth * Calendar.secondsInADay) + (duration.getYears() * Calendar.daysInAYear * Calendar.secondsInADay);
        }

        DateTimeDifference dateTimeDifference = DateTimeDifference.builder()
                        .numberOfYears(numberOfYears)
                        .numberOfMonths(numberOfMonths)
                        .numberOfWeeks(numberOfWeeks)
                        .numberOfDays(numberOfDays)
                        .numberOfHours(numberOfHours)
                        .numberOfMinutes(numberOfMinutes)
                        .numberOfSeconds(numberOfSeconds)
                        .unitsToUse(unitsToUseTemp)
                        .build();
        return BuildDifferenceListBasedOnChronoUnitsTask.run(dateTimeDifference);
    }
}
