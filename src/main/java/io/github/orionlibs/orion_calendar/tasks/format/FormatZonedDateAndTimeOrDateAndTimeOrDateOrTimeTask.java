package io.github.orionlibs.orion_calendar.tasks.format;

import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.InvalidDateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FormatZonedDateAndTimeOrDateAndTimeOrDateOrTimeTask
{
    private List<String> formattersAlreadyTried;


    public DateTime run(String dateString, String timeString, DateTimeFormatter formatter) throws InvalidDateTimeException
    {
        return run(dateString, timeString, formatter, ZoneId.of("UTC"));
    }


    public DateTime run(String dateString, String timeString, DateTimeFormatter formatter, ZoneId zoneID) throws InvalidDateTimeException
    {
        if(formattersAlreadyTried != null && !formattersAlreadyTried.isEmpty())
        {
            if(formattersAlreadyTried.contains(formatter.toString()))
            {
                return null;
            }
            else
            {
                formattersAlreadyTried.add(formatter.toString());
            }
        }
        else
        {
            this.formattersAlreadyTried = new ArrayList<>();
            formattersAlreadyTried.add(formatter.toString());
        }
        try
        {
            return DateTime.of(ZonedDateTime.parse(dateString + "T" + timeString, formatter));
        }
        catch(DateTimeParseException e)
        {
            return formatDateAndTimeOrDateOrTime(dateString, timeString, formatter, zoneID);
        }
    }


    private DateTime formatDateAndTimeOrDateOrTime(String dateString, String timeString, DateTimeFormatter formatter, ZoneId zoneID) throws InvalidDateTimeException
    {
        try
        {
            return DateTime.of(LocalDateTime.parse(dateString + "T" + timeString, formatter));
        }
        catch(DateTimeParseException e)
        {
            return formatDateOrTime(dateString, timeString, zoneID);
        }
    }


    private DateTime formatDateOrTime(String dateString, String timeString, ZoneId zoneID) throws InvalidDateTimeException
    {
        try
        {
            formattersAlreadyTried.add(DateTimeFormatter.ISO_DATE.toString());
            formattersAlreadyTried.add(DateTimeFormatter.ISO_TIME.toString());
            return DateTime.of(LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE), LocalTime.parse(timeString, DateTimeFormatter.ISO_TIME), zoneID);
        }
        catch(DateTimeParseException e)
        {
            return formatTime(dateString, timeString, zoneID);
        }
    }


    private DateTime formatTime(String dateString, String timeString, ZoneId zoneID) throws InvalidDateTimeException
    {
        try
        {
            formattersAlreadyTried.add(DateTimeFormatter.ISO_TIME.toString());
            return DateTime.of(LocalTime.parse(timeString, DateTimeFormatter.ISO_TIME));
        }
        catch(DateTimeParseException e)
        {
            try
            {
                return run(dateString, timeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePattern), zoneID);
            }
            catch(InvalidDateTimeException e1)
            {
                try
                {
                    return run(dateString, timeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternWithSpaceBetweenDateAndTime), zoneID);
                }
                catch(InvalidDateTimeException e2)
                {
                    try
                    {
                        return run(dateString, timeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternYearLast), zoneID);
                    }
                    catch(InvalidDateTimeException e3)
                    {
                        try
                        {
                            return run(dateString, timeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternYearLastWithSpaceBetweenDateAndTime), zoneID);
                        }
                        catch(InvalidDateTimeException e4)
                        {
                            try
                            {
                                return run(dateString, timeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternWithoutSeconds), zoneID);
                            }
                            catch(InvalidDateTimeException e5)
                            {
                                throw new InvalidDateTimeException();
                            }
                        }
                    }
                }
            }
        }
    }
}