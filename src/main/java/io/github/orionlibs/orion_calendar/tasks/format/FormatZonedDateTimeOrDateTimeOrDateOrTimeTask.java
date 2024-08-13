package io.github.orionlibs.orion_calendar.tasks.format;

import io.github.orionlibs.orion_calendar.Calendar;
import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.InvalidDateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FormatZonedDateTimeOrDateTimeOrDateOrTimeTask
{
    private List<String> formattersAlreadyTried;


    public DateTime run(String dateTimeString, DateTimeFormatter formatter) throws InvalidDateTimeException
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
            return DateTime.of(ZonedDateTime.parse(dateTimeString, formatter));
        }
        catch(DateTimeParseException e)
        {
            return formatDateTimeOrDateOrTime(dateTimeString, formatter);
        }
    }


    private DateTime formatDateTimeOrDateOrTime(String dateTimeString, DateTimeFormatter formatter) throws InvalidDateTimeException
    {
        try
        {
            return DateTime.of(LocalDateTime.parse(dateTimeString, formatter));
        }
        catch(DateTimeParseException e)
        {
            return formatDateOrTime(dateTimeString);
        }
    }


    private DateTime formatDateOrTime(String dateTimeString) throws InvalidDateTimeException
    {
        try
        {
            formattersAlreadyTried.add(DateTimeFormatter.ISO_DATE.toString());
            return DateTime.of(LocalDate.parse(dateTimeString, DateTimeFormatter.ISO_DATE));
        }
        catch(DateTimeParseException e)
        {
            return formatTime(dateTimeString);
        }
    }


    public static void main(String[] args)
    {
        System.out.println(CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePattern).toString());
    }


    private DateTime formatTime(String dateTimeString) throws InvalidDateTimeException
    {
        try
        {
            formattersAlreadyTried.add(DateTimeFormatter.ISO_TIME.toString());
            return DateTime.of(LocalTime.parse(dateTimeString, DateTimeFormatter.ISO_TIME));
        }
        catch(DateTimeParseException e)
        {
            try
            {
                return run(dateTimeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePattern));
            }
            catch(InvalidDateTimeException e1)
            {
                try
                {
                    return run(dateTimeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternWithSpaceBetweenDateAndTime));
                }
                catch(InvalidDateTimeException e2)
                {
                    try
                    {
                        return run(dateTimeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternYearLast));
                    }
                    catch(InvalidDateTimeException e3)
                    {
                        try
                        {
                            return run(dateTimeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternYearLastWithSpaceBetweenDateAndTime));
                        }
                        catch(InvalidDateTimeException e4)
                        {
                            try
                            {
                                return run(dateTimeString, CalendarService.getDateTimeFormatterToUse(Calendar.fullDatetimePatternYearLastWithCommaBetweenDateAndTime));
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