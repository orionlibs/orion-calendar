package io.github.orionlibs.orion_calendar.time;

import io.github.orionlibs.orion_calendar.CalendarRules;
import io.github.orionlibs.orion_calendar.CalendarService;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Time
{
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;
    private ZoneId zoneID;


    public Time(int hours, int minutes, int seconds, int milliseconds)
    {
        TimeRules.isValid(hours, minutes, seconds, milliseconds);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }


    public Time(int hours, int minutes, int seconds, int milliseconds, ZoneId zoneID)
    {
        TimeRules.isValid(hours, minutes, seconds, milliseconds);
        CalendarRules.isValid(zoneID);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.zoneID = zoneID;
    }


    public Time(int hours, int minutes, int seconds)
    {
        TimeRules.isValid(hours, minutes, seconds);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    public Time(int hours, int minutes, int seconds, ZoneId zoneID)
    {
        TimeRules.isValid(hours, minutes, seconds);
        CalendarRules.isValid(zoneID);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.zoneID = zoneID;
    }


    public Time(LocalTime localTime)
    {
        TimeRules.isValid(localTime);
        this.hours = localTime.getHour();
        this.minutes = localTime.getMinute();
        this.seconds = localTime.getSecond();
        this.milliseconds = localTime.getNano() / 1_000_000;
    }


    public Time(LocalTime localTime, ZoneId zoneID)
    {
        TimeRules.isValid(localTime);
        CalendarRules.isValid(zoneID);
        this.hours = localTime.getHour();
        this.minutes = localTime.getMinute();
        this.seconds = localTime.getSecond();
        this.milliseconds = localTime.getNano() / 1_000_000;
        this.zoneID = zoneID;
    }


    public Time(String timeString) throws InvalidTimeException
    {
        TimeRules.isValid(timeString);
        buildTime(timeString);
    }


    public Time(String timeString, ZoneId zoneID) throws InvalidTimeException
    {
        TimeRules.isValid(timeString);
        CalendarRules.isValid(zoneID);
        buildTime(timeString);
        this.zoneID = zoneID;
    }


    public static Time of(int hours, int minutes, int seconds, int milliseconds)
    {
        return new Time(hours, minutes, seconds, milliseconds);
    }


    public static Time of(int hours, int minutes, int seconds, int milliseconds, ZoneId zoneID)
    {
        return new Time(hours, minutes, seconds, milliseconds, zoneID);
    }


    public static Time of(int hours, int minutes, int seconds)
    {
        return new Time(hours, minutes, seconds);
    }


    public static Time of(int hours, int minutes, int seconds, ZoneId zoneID)
    {
        return new Time(hours, minutes, seconds, zoneID);
    }


    public static Time of(LocalTime localTime)
    {
        return new Time(localTime);
    }


    public static Time of(LocalTime localTime, ZoneId zoneID)
    {
        return new Time(localTime, zoneID);
    }


    public static Time of(String timeString) throws InvalidTimeException
    {
        return new Time(timeString);
    }


    public static Time of(String timeString, ZoneId zoneID) throws InvalidTimeException
    {
        return new Time(timeString, zoneID);
    }


    private void buildTime(String timeString) throws InvalidTimeException
    {
        TimeRules.isValid(timeString);
        String[] timeTokens = timeString.split(":");
        this.hours = Integer.parseInt(timeTokens[0]);
        this.minutes = Integer.parseInt(timeTokens[1]);

        if(timeTokens.length > 2 && timeTokens[2] != null)
        {

            if(timeTokens[2].indexOf(".") >= 0)
            {
                this.seconds = Integer.parseInt(timeTokens[2].substring(0, timeTokens[2].indexOf(".")));
                this.milliseconds = Integer.parseInt(timeTokens[2].substring(timeTokens[2].indexOf(".") + 1));
            }
            else
            {
                this.seconds = Integer.parseInt(timeTokens[2]);
            }

        }

    }


    public String getTimeString()
    {
        String timeString = formatHoursString() + ":" + formatMinutesString() + ":" + formatSecondsString();

        if(milliseconds > 0)
        {
            timeString += "." + milliseconds;

            if(milliseconds < 10)
            {
                timeString += "00";
            }
            else if(milliseconds < 100)
            {
                timeString += "0";
            }

        }

        return timeString;
    }


    public String getTimeStringWithoutMilliseconds()
    {
        return formatHoursString() + ":" + formatMinutesString() + ":" + formatSecondsString();
    }


    public String getTimeStringWithoutSeconds()
    {
        return formatHoursString() + ":" + formatMinutesString();
    }


    public String getTimeStringInISOFormat()
    {
        return LocalTime.of(hours, minutes, seconds, (milliseconds * 1_000_000)).format(DateTimeFormatter.ISO_LOCAL_TIME);
    }


    private String formatHoursString()
    {
        return TimeInternalService.formatHoursString(this);
    }


    private String formatMinutesString()
    {
        return TimeInternalService.formatMinutesString(this);
    }


    private String formatSecondsString()
    {
        return TimeInternalService.formatSecondsString(this);
    }


    public LocalTime toLocalTime()
    {
        return LocalTime.of(hours, minutes, seconds, (milliseconds * 1_000_000));
    }


    public LocalTime toLocalTime(boolean applyDaylightSavings)
    {
        int hoursAdjustedToDaylightSavings = hours;

        if(applyDaylightSavings)
        {
            hoursAdjustedToDaylightSavings += CalendarService.getDaylightSavingsHoursToAdd();
        }

        return LocalTime.of(hoursAdjustedToDaylightSavings, minutes, seconds, (milliseconds * 1_000_000));
    }


    public String toString()
    {
        return getTimeString();
    }


    @Override
    public int hashCode()
    {
        return TimeInternalService.hashCode(this);
    }


    @Override
    public boolean equals(Object other)
    {
        return TimeInternalService.equals(this, other);
    }


    public int getHours()
    {
        return this.hours;
    }


    public int getMinutes()
    {
        return this.minutes;
    }


    public int getSeconds()
    {
        return this.seconds;
    }


    public int getMilliseconds()
    {
        return this.milliseconds;
    }


    public ZoneId getZoneID()
    {
        return this.zoneID;
    }
}