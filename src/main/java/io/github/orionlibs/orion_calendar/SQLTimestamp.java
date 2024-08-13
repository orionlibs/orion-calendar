package io.github.orionlibs.orion_calendar;

import io.github.orionlibs.orion_calendar.date.InvalidDateException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class SQLTimestamp extends Timestamp
{
    private boolean haveFormattedValuesBeenInitialised;
    private boolean formatUsingUTC;
    private String formattedDate;
    private String formattedDateWithSlashes;
    private String formattedDateWithSlashesYearFirst;
    private String formattedDateWithHyphens;
    private String formattedDateWithHyphensYearFirst;
    private String formattedDateWithHyphensWithoutYear;
    private String formattedTime;
    private String formattedTimeWithoutSeconds;


    @SuppressWarnings("deprecation")
    public SQLTimestamp(int year, int month, int date, int hour, int minute, int second, int nanoseconds)
    {
        super(year - 1900, month - 1, date, hour, minute, second, nanoseconds);
    }


    public SQLTimestamp(long epochMilliseconds)
    {
        super(epochMilliseconds);
    }


    public SQLTimestamp(Timestamp timestamp)
    {
        super(timestamp.toInstant().toEpochMilli());
        setNanos((timestamp.getNanos()));
    }


    public SQLTimestamp(Timestamp timestamp, boolean formatUsingUTC)
    {
        super(timestamp.toInstant().toEpochMilli());
        setNanos((timestamp.getNanos()));
        this.formatUsingUTC = formatUsingUTC;
    }


    public SQLTimestamp(ZonedDateTime zonedDatetime)
    {
        this(zonedDatetime.toEpochSecond() * 1000);
    }


    public static SQLTimestamp of(int year, int month, int date, int hour, int minute, int second, int nanoseconds)
    {
        return new SQLTimestamp(year, month, date, hour, minute, second, nanoseconds);
    }


    public static SQLTimestamp of(long epochMilliseconds)
    {
        return new SQLTimestamp(epochMilliseconds);
    }


    public static SQLTimestamp of(Timestamp timestamp)
    {
        return new SQLTimestamp(timestamp);
    }


    public static SQLTimestamp ofUsingUTCFormatting(Timestamp timestamp)
    {
        return new SQLTimestamp(timestamp, true);
    }


    public static SQLTimestamp of(ZonedDateTime zonedDatetime)
    {
        return new SQLTimestamp(zonedDatetime);
    }


    public static SQLTimestamp ofEpochSeconds(long epochSeconds)
    {
        return new SQLTimestamp(epochSeconds * 1000);
    }


    public String printTime()
    {
        formatTimestampFields();
        return formattedTime;
    }


    public void formatTimestampFields()
    {
        if(!haveFormattedValuesBeenInitialised)
        {
            if(formatUsingUTC)
            {
                FormattedSQLTimestamp.setupFormattedDateTimeUTC(this);
            }
            else
            {
                FormattedSQLTimestamp.setupFormattedDateTime(this);
            }
            setHaveFormattedValuesBeenInitialised(true);
        }
    }


    public String printTimeWithoutSeconds()
    {
        formatTimestampFields();
        return formattedTimeWithoutSeconds;
    }


    public String printInInternationalFormat()
    {
        formatTimestampFields();
        StringBuilder sb = new StringBuilder(formattedDate);
        sb.append(" @ ");
        sb.append(formattedTime);
        return sb.toString();
    }


    public String printWithoutSecondsInInternationalFormat()
    {
        formatTimestampFields();
        StringBuilder sb = new StringBuilder(formattedDate);
        sb.append(" @ ");
        sb.append(formattedTimeWithoutSeconds);
        return sb.toString();
    }


    public String printWithoutSecondsInInternationalFormatTimeFirst()
    {
        formatTimestampFields();
        StringBuilder sb = new StringBuilder(" @ ");
        sb.append(formattedTimeWithoutSeconds);
        sb.append(" on ");
        sb.append(formattedDate);
        return sb.toString();
    }


    public String printWithoutSecondsInLongFormat() throws InvalidDateException
    {
        formatTimestampFields();
        StringBuilder sb = new StringBuilder(printLongDate());
        sb.append(" @ ");
        sb.append(formattedTimeWithoutSeconds);
        return sb.toString();
    }


    public String printWithoutSecondsInLongFormatTimeFirst() throws InvalidDateException
    {
        formatTimestampFields();
        StringBuilder sb = new StringBuilder(" @ ");
        sb.append(formattedTimeWithoutSeconds);
        sb.append(" on ");
        sb.append(printLongDate());
        return sb.toString();
    }


    public String getDateStringSplitBySlashes()
    {
        formatTimestampFields();
        return formattedDateWithSlashes;
    }


    public String getDateStringSplitBySlashesYearFirst()
    {
        formatTimestampFields();
        return formattedDateWithSlashesYearFirst;
    }


    public String getDateStringSplitByHyphens()
    {
        formatTimestampFields();
        return formattedDateWithHyphens;
    }


    public String getDateStringSplitByHyphensWithoutYear()
    {
        formatTimestampFields();
        return formattedDateWithHyphensWithoutYear;
    }


    public String getDateStringSplitByHyphensYearFirst()
    {
        formatTimestampFields();
        return formattedDateWithHyphensYearFirst;
    }


    public String getDateStringSplitByHyphensYearFirstUTC()
    {
        formatTimestampFields();
        SQLTimestamp temp = SQLTimestamp.ofUsingUTCFormatting(this);
        temp.formatTimestampFields();
        return temp.getFormattedDateWithHyphensYearFirst();
    }


    public String printDateInInternationalFormat()
    {
        formatTimestampFields();
        return formattedDate;
    }


    public DateTokens getAsTokens()
    {
        return DateTokens.builder()
                        .days(getDays())
                        .month(getMonths())
                        .year(getYears())
                        .build();
    }


    public String printLongDate() throws InvalidDateException
    {
        formatTimestampFields();
        return CalendarService.convertDateToLongFormat(getAsTokens());
    }


    public String printLongDateWithoutYear() throws InvalidDateException
    {
        formatTimestampFields();
        return CalendarService.convertDateToLongFormatWithoutYear(getAsTokens());
    }


    public String printLongDateTime() throws InvalidDateException
    {
        formatTimestampFields();
        return CalendarService.convertDateToLongFormat(getAsTokens()) + " @ " + printTime();
    }


    public String printLongDateTimeWithoutSeconds() throws InvalidDateException
    {
        formatTimestampFields();
        return CalendarService.convertDateToLongFormat(getAsTokens()) + " @ " + printTimeWithoutSeconds();
    }


    @SuppressWarnings("deprecation")
    public int getYears()
    {
        return getYear() + 1900;
    }


    @SuppressWarnings("deprecation")
    public int getMonths()
    {
        return getMonth() + 1;
    }


    @SuppressWarnings("deprecation")
    public int getDays()
    {
        return getDate();
    }


    @Override
    public int hashCode()
    {
        return super.hashCode();
    }


    @Override
    public boolean equals(Object other)
    {
        return super.equals(other);
    }


    public boolean getHaveFormattedValuesBeenInitialised()
    {
        return haveFormattedValuesBeenInitialised;
    }


    public void setHaveFormattedValuesBeenInitialised(boolean haveFormattedValuesBeenInitialised)
    {
        this.haveFormattedValuesBeenInitialised = haveFormattedValuesBeenInitialised;
    }


    public void setFormattedDate(String formattedDate)
    {
        this.formattedDate = formattedDate;
    }


    public void setFormattedDateWithSlashes(String formattedDateWithSlashes)
    {
        this.formattedDateWithSlashes = formattedDateWithSlashes;
    }


    public void setFormattedDateWithSlashesYearFirst(String formattedDateWithSlashesYearFirst)
    {
        this.formattedDateWithSlashesYearFirst = formattedDateWithSlashesYearFirst;
    }


    public void setFormattedDateWithHyphens(String formattedDateWithHyphens)
    {
        this.formattedDateWithHyphens = formattedDateWithHyphens;
    }


    public void setFormattedDateWithHyphensYearFirst(String formattedDateWithHyphensYearFirst)
    {
        this.formattedDateWithHyphensYearFirst = formattedDateWithHyphensYearFirst;
    }


    public void setFormattedDateWithHyphensWithoutYear(String formattedDateWithHyphensWithoutYear)
    {
        this.formattedDateWithHyphensWithoutYear = formattedDateWithHyphensWithoutYear;
    }


    public void setFormattedTime(String formattedTime)
    {
        this.formattedTime = formattedTime;
    }


    public void setFormattedTimeWithoutSeconds(String formattedTimeWithoutSeconds)
    {
        this.formattedTimeWithoutSeconds = formattedTimeWithoutSeconds;
    }


    private String getFormattedDateWithHyphensYearFirst()
    {
        return this.formattedDateWithHyphensYearFirst;
    }


    public boolean isFormatUsingUTC()
    {
        return this.formatUsingUTC;
    }
}