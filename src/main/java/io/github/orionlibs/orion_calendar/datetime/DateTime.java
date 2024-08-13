package io.github.orionlibs.orion_calendar.datetime;

import io.github.orionlibs.orion_calendar.CalendarRules;
import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.SQLTimestamp;
import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;
import io.github.orionlibs.orion_calendar.date.InvalidDateException;
import io.github.orionlibs.orion_calendar.time.InvalidTimeException;
import io.github.orionlibs.orion_calendar.time.Time;
import io.github.orionlibs.orion_calendar.time.TimeRules;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTime
{
    private static final ZoneId DefaultZone = ZoneId.of("UTC");
    private Date date;
    private Time time;
    private ZoneId zoneID;


    public DateTime() throws InvalidDateException, InvalidTimeException
    {
        this("", "", DefaultZone);
    }


    public DateTime(String dateString, String timeString) throws InvalidDateException, InvalidTimeException
    {
        this(dateString, timeString, DefaultZone);
    }


    public DateTime(String dateString, String timeString, ZoneId zoneID) throws InvalidDateException, InvalidTimeException
    {
        CalendarRules.isValid(zoneID);
        this.date = Date.of(dateString, zoneID);
        this.time = Time.of(timeString, zoneID);
        this.zoneID = zoneID;
    }


    public DateTime(Date date)
    {
        this(date, Time.of(0, 0, 0, DefaultZone), DefaultZone);
    }


    public DateTime(Date date, ZoneId zoneID)
    {
        this(date, Time.of(0, 0, 0, zoneID), zoneID);
    }


    public DateTime(Time time)
    {
        TimeRules.isValid(time);
        this.time = time;
        this.zoneID = DefaultZone;
    }


    public DateTime(Time time, ZoneId zoneID)
    {
        TimeRules.isValid(time);
        CalendarRules.isValid(zoneID);
        this.time = time;
        this.zoneID = zoneID;
    }


    public DateTime(Date date, Time time)
    {
        this(date, time, DefaultZone);
    }


    public DateTime(Date date, Time time, ZoneId zoneID)
    {
        DateTimeRules.isValid(date, time, zoneID);
        this.date = date;
        this.time = time;
        this.zoneID = zoneID;
    }


    public DateTime(LocalDateTime localDateTime)
    {
        DateTimeRules.isValid(localDateTime);
        this.date = Date.of(localDateTime.toLocalDate());
        this.time = Time.of(localDateTime.toLocalTime());
        this.zoneID = DefaultZone;
    }


    public DateTime(LocalDateTime localDateTime, ZoneId zoneID)
    {
        DateTimeRules.isValid(localDateTime);
        CalendarRules.isValid(zoneID);
        this.date = Date.of(localDateTime.toLocalDate(), zoneID);
        this.time = Time.of(localDateTime.toLocalTime(), zoneID);
        this.zoneID = zoneID;
    }


    public DateTime(ZonedDateTime zonedDateTime)
    {
        DateTimeRules.isValid(zonedDateTime);
        this.date = Date.of(zonedDateTime.toLocalDate(), zonedDateTime.getZone());
        this.time = Time.of(zonedDateTime.toLocalTime(), zonedDateTime.getZone());
        this.zoneID = zonedDateTime.getZone();
    }


    public DateTime(LocalDate localDate)
    {
        DateRules.isValid(localDate);
        this.date = Date.of(localDate);
        this.time = Time.of(0, 0, 0);
        this.zoneID = DefaultZone;
    }


    public DateTime(LocalDate localDate, ZoneId zoneID)
    {
        DateRules.isValid(localDate);
        CalendarRules.isValid(zoneID);
        this.date = Date.of(localDate, zoneID);
        this.time = Time.of(0, 0, 0, zoneID);
        this.zoneID = zoneID;
    }


    public DateTime(LocalDate localDate, LocalTime localTime, ZoneId zoneID)
    {
        DateRules.isValid(localDate);
        TimeRules.isValid(localTime);
        CalendarRules.isValid(zoneID);
        this.date = Date.of(localDate, zoneID);
        this.time = Time.of(localTime, zoneID);
        this.zoneID = zoneID;
    }


    public DateTime(LocalTime localTime)
    {
        TimeRules.isValid(localTime);
        this.time = Time.of(localTime);
        this.zoneID = DefaultZone;
    }


    public DateTime(LocalTime localTime, ZoneId zoneID)
    {
        TimeRules.isValid(localTime);
        CalendarRules.isValid(zoneID);
        this.time = Time.of(localTime, zoneID);
        this.zoneID = zoneID;
    }


    public DateTime(java.sql.Date SQLDate)
    {
        DateRules.isValidSQLDate(SQLDate);
        this.date = Date.of(SQLDate.toLocalDate());
        this.time = Time.of(0, 0, 0);
        this.zoneID = DefaultZone;
    }


    public DateTime(java.sql.Date SQLDate, ZoneId zoneID)
    {
        DateRules.isValidSQLDate(SQLDate);
        CalendarRules.isValid(zoneID);
        this.date = Date.of(SQLDate.toLocalDate(), zoneID);
        this.time = Time.of(0, 0, 0, zoneID);
        this.zoneID = zoneID;
    }


    public static DateTime of() throws InvalidDateException, InvalidTimeException
    {
        return new DateTime();
    }


    public static DateTime of(String dateString, String timeString) throws InvalidDateException, InvalidTimeException
    {
        return new DateTime(dateString, timeString);
    }


    public static DateTime of(String dateString, String timeString, ZoneId zoneID) throws InvalidDateException, InvalidTimeException
    {
        return new DateTime(dateString, timeString, zoneID);
    }


    public static DateTime of(Date date)
    {
        return new DateTime(date);
    }


    public static DateTime of(Date date, ZoneId zoneID)
    {
        return new DateTime(date, zoneID);
    }


    public static DateTime of(Time time)
    {
        return new DateTime(time);
    }


    public static DateTime of(Time time, ZoneId zoneID)
    {
        return new DateTime(time, zoneID);
    }


    public static DateTime of(Date date, Time time)
    {
        return new DateTime(date, time);
    }


    public static DateTime of(Date date, Time time, ZoneId zoneID)
    {
        return new DateTime(date, time, zoneID);
    }


    public static DateTime of(LocalDateTime localDateTime)
    {
        return new DateTime(localDateTime);
    }


    public static DateTime of(LocalDateTime localDateTime, ZoneId zoneID)
    {
        return new DateTime(localDateTime, zoneID);
    }


    public static DateTime of(ZonedDateTime zonedDateTime)
    {
        return new DateTime(zonedDateTime);
    }


    public static DateTime of(LocalDate localDate)
    {
        return new DateTime(localDate);
    }


    public static DateTime of(LocalDate localDate, ZoneId zoneID)
    {
        return new DateTime(localDate, zoneID);
    }


    public static DateTime of(LocalDate localDate, LocalTime localTime, ZoneId zoneID)
    {
        return new DateTime(localDate, localTime, zoneID);
    }


    public static DateTime of(LocalTime localTime)
    {
        return new DateTime(localTime);
    }


    public static DateTime of(LocalTime localTime, ZoneId zoneID)
    {
        return new DateTime(localTime, zoneID);
    }


    public static DateTime of(java.sql.Date SQLDate)
    {
        return new DateTime(SQLDate);
    }


    public static DateTime of(java.sql.Date SQLDate, ZoneId zoneID)
    {
        return new DateTime(SQLDate, zoneID);
    }


    public static DateTime of(Timestamp SQLDate)
    {
        return new DateTime(SQLDate.toLocalDateTime());
    }


    public static DateTime ofTimestampWith0Time(Timestamp SQLDate)
    {
        DateTime temp = new DateTime(SQLDate.toLocalDateTime());
        return new DateTime(temp.getDate());
    }


    public static DateTime of(Timestamp SQLDate, ZoneId zoneID)
    {
        return new DateTime(SQLDate.toLocalDateTime(), zoneID);
    }


    public ZoneOffset getZoneOffset()
    {
        return zoneID.getRules().getOffset(toInstant());
    }


    public LocalDateTime toLocalDateTime()
    {
        DateTimeRules.isValid(date, time);
        return LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
    }


    public LocalDate toLocalDate()
    {
        DateRules.isValid(date);
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    }


    public ZonedDateTime toZonedDateTime()
    {
        CalendarRules.isValid(zoneID);
        return toZonedDateTime(zoneID);
    }


    public ZonedDateTime toZonedDateTime(ZoneId zone)
    {
        LocalDateTime localDateTime = toLocalDateTime();
        DateTimeRules.isValid(localDateTime, zone);
        return localDateTime.atZone(zone);
    }


    public Instant toInstant()
    {
        return toZonedDateTime().toInstant();
    }


    public Instant toInstant(ZoneId zone)
    {
        return toZonedDateTime(zone).toInstant();
    }


    public Timestamp toSQLTimestamp()
    {
        return new Timestamp(toInstant().toEpochMilli());
    }


    public SQLTimestamp toOrionSQLTimestamp()
    {
        return new SQLTimestamp(toInstant().toEpochMilli());
    }


    public String printAsIsInSQLFormat()
    {
        return printInSQLFormatWithStringBetweenDateAndTime("");
    }


    public String printInSQLFormatWithAtSymbol()
    {
        return printInSQLFormatWithStringBetweenDateAndTime("@");
    }


    public String printInSQLFormatWithAtSymbolWithoutSeconds()
    {
        return printInSQLFormatWithStringBetweenDateAndTimeWithoutSeconds("@");
    }


    public String printInInternationalFormatWithAtSymbolWithoutSeconds() throws InvalidDateException
    {
        return printInInternationalFormatWithStringBetweenDateAndTimeWithoutSeconds("@", false, false);
    }


    public String printLongDateWithoutYearWithAtSymbolWithoutSeconds() throws InvalidDateException
    {
        return printInInternationalFormatWithStringBetweenDateAndTimeWithoutSeconds("@", true, true);
    }


    public String printInSQLFormatWithStringBetweenDateAndTime(String stringBetweenDateAndtime)
    {
        String formattedDateTime = "";

        if(date != null)
        {
            formattedDateTime += date.getDateStringSplitByHyphensYearFirst();
        }

        if(time != null)
        {

            if(date != null)
            {
                formattedDateTime += " ";

                if(stringBetweenDateAndtime != null && !stringBetweenDateAndtime.isEmpty())
                {
                    formattedDateTime += stringBetweenDateAndtime + " ";
                }

            }

            formattedDateTime += time.getTimeStringWithoutMilliseconds();
        }

        return formattedDateTime;
    }


    public String printInSQLFormatWithStringBetweenDateAndTimeWithoutSeconds(String stringBetweenDateAndtime)
    {
        String formattedDateTime = "";

        if(date != null)
        {
            formattedDateTime += date.getDateStringSplitByHyphensYearFirst();
        }

        if(time != null)
        {

            if(date != null)
            {
                formattedDateTime += " ";

                if(stringBetweenDateAndtime != null)
                {
                    formattedDateTime += " " + stringBetweenDateAndtime + " ";
                }

            }

            formattedDateTime += time.getTimeStringWithoutSeconds();
        }

        return formattedDateTime;
    }


    public String printInInternationalFormatWithStringBetweenDateAndTimeWithoutSeconds(String stringBetweenDateAndtime, boolean printDateWithoutYear, boolean printDateInLongFormat) throws InvalidDateException
    {
        String formattedDateTime = "";

        if(date != null)
        {

            if(printDateWithoutYear && printDateInLongFormat)
            {
                formattedDateTime += CalendarService.convertDateToLongFormatWithoutYear(date);
            }
            else if(!printDateWithoutYear && printDateInLongFormat)
            {
                formattedDateTime += CalendarService.convertDateToLongFormat(date.getAsTokens());
            }
            else if(printDateWithoutYear && !printDateInLongFormat)
            {
                formattedDateTime += date.getDateStringSplitByHyphensWithoutYear();
            }
            else
            {
                formattedDateTime += date.getDateStringSplitByHyphens();
            }

        }

        if(time != null)
        {

            if(date != null)
            {
                formattedDateTime += " ";

                if(stringBetweenDateAndtime != null)
                {
                    formattedDateTime += stringBetweenDateAndtime + " ";
                }

            }

            formattedDateTime += time.getTimeStringWithoutSeconds();
        }

        return formattedDateTime;
    }


    public String printInSQLFormat()
    {
        String formattedDateTime = "";
        int offsetMinutes = CalendarService.getDaylightSavingsMinutesToAdd();
        DateTime dateTimeAdjustedForDaylightSavings = null;

        if(date != null)
        {

            if(offsetMinutes != 0)
            {
                dateTimeAdjustedForDaylightSavings = CalendarService.addMinutesToDatetime(this, offsetMinutes);
                formattedDateTime += dateTimeAdjustedForDaylightSavings.getDate().getDateStringSplitByHyphensYearFirst();
            }
            else
            {
                formattedDateTime += date.getDateStringSplitByHyphensYearFirst();
            }

        }

        if(time != null)
        {

            if(date != null)
            {
                formattedDateTime += " ";
            }

            if(offsetMinutes != 0)
            {
                formattedDateTime += dateTimeAdjustedForDaylightSavings.getTime().getTimeString();
            }
            else
            {
                formattedDateTime += time.getTimeString();
            }

        }

        return formattedDateTime;
    }


    public String printDateAsIsInSQLFormat()
    {
        return (date != null) ? date.getDateStringSplitByHyphensYearFirst() : "";
    }


    public String printDateAsIsInInternationalFormat()
    {
        return (date != null) ? date.getDateStringSplitByHyphens() : "";
    }


    public String printDateAsIsInInternationalFormatWithSlashes()
    {
        return (date != null) ? date.getDateStringSplitBySlashes() : "";
    }


    public String printDateAsIsInInternationalFormatWithSlashesYearFirst()
    {
        return (date != null) ? date.getDateStringSplitBySlashesYearFirst() : "";
    }


    public String printDateInSQLFormat()
    {
        String formattedDateTime = "";
        int offsetMinutes = CalendarService.getDaylightSavingsMinutesToAdd();
        DateTime dateTimeAdjustedForDaylightSavings = null;

        if(date != null)
        {

            if(offsetMinutes != 0)
            {
                dateTimeAdjustedForDaylightSavings = CalendarService.addMinutesToDatetime(this, offsetMinutes);
                formattedDateTime += dateTimeAdjustedForDaylightSavings.getDate().getDateStringSplitByHyphensYearFirst();
            }
            else
            {
                formattedDateTime += date.getDateStringSplitByHyphensYearFirst();
            }

        }

        return formattedDateTime;
    }


    public String printTimeAsIs()
    {
        return (time != null) ? time.getTimeString() : "";
    }


    public String printTimeAsIsWithoutSeconds()
    {
        return (time != null) ? time.getTimeStringWithoutSeconds() : "";
    }


    public String printTime()
    {
        String formattedTime = "";
        int offsetHours = CalendarService.getDaylightSavingsHoursToAdd();
        DateTime dateTimeAdjustedForDaylightSavings = null;

        if(time != null)
        {

            if(offsetHours != 0)
            {
                dateTimeAdjustedForDaylightSavings = CalendarService.addHoursToDatetime(this, offsetHours);
                formattedTime += dateTimeAdjustedForDaylightSavings.getTime().getTimeString();
            }
            else
            {
                formattedTime += time.getTimeString();
            }

        }

        return formattedTime;
    }


    public String toString()
    {
        return printAsIsInSQLFormat();
    }


    @Override
    public int hashCode()
    {
        return DateTimeInternalService.hashCode(this);
    }


    @Override
    public boolean equals(Object other)
    {
        return DateTimeInternalService.equals(this, other);
    }


    public Time getTime()
    {
        return this.time;
    }


    public Date getDate()
    {
        return this.date;
    }


    public ZoneId getZoneID()
    {
        return this.zoneID;
    }
}