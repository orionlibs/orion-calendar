package io.github.orionlibs.orion_calendar.date;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_calendar.CalendarRules;
import io.github.orionlibs.orion_calendar.CalendarService;
import io.github.orionlibs.orion_calendar.DateTokens;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

public class Date
{
    private int year;
    private int month;
    private int day;
    private ZoneId zoneID;


    public Date()
    {
    }


    public Date(int year, int month, int day)
    {
        DateRules.isValid(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public Date(int year, int month, int day, ZoneId zoneID)
    {
        DateRules.isValid(year, month, day);
        CalendarRules.isValid(zoneID);
        this.year = year;
        this.month = month;
        this.day = day;
        this.zoneID = zoneID;
    }


    public Date(LocalDate localDate)
    {
        DateRules.isValid(localDate);
        this.year = localDate.getYear();
        this.month = localDate.getMonthValue();
        this.day = localDate.getDayOfMonth();
    }


    public Date(LocalDate localDate, ZoneId zoneID)
    {
        DateRules.isValid(localDate);
        CalendarRules.isValid(zoneID);
        this.year = localDate.getYear();
        this.month = localDate.getMonthValue();
        this.day = localDate.getDayOfMonth();
        this.zoneID = zoneID;
    }


    public Date(String dateString) throws InvalidDateException
    {
        DateRules.isValid(dateString);
        String[] dateTokens = CalendarService.tokeniseDateString(dateString);
        Assert.hasLength(dateTokens, 3, "The dateString input needs day, month and year.");
        this.year = Integer.parseInt(dateTokens[0]);
        this.month = Integer.parseInt(dateTokens[1]);
        this.day = Integer.parseInt(dateTokens[2]);
    }


    public Date(String dateString, ZoneId zoneID) throws InvalidDateException
    {
        DateRules.isValid(dateString);
        CalendarRules.isValid(zoneID);
        String[] dateTokens = CalendarService.tokeniseDateString(dateString);
        Assert.hasLength(dateTokens, 3, "The dateString input needs day, month and year.");
        this.year = Integer.parseInt(dateTokens[0]);
        this.month = Integer.parseInt(dateTokens[1]);
        this.day = Integer.parseInt(dateTokens[2]);
        this.zoneID = zoneID;
    }


    public Date(java.sql.Date SQLDate)
    {
        DateRules.isValidSQLDate(SQLDate);
        this.year = SQLDate.getYear() + 1900;
        this.month = SQLDate.getMonth() + 1;
        this.day = SQLDate.getDate();
    }


    public Date(java.sql.Date SQLDate, ZoneId zoneID)
    {
        DateRules.isValidSQLDate(SQLDate);
        CalendarRules.isValid(zoneID);
        this.year = SQLDate.getYear() + 1900;
        this.month = SQLDate.getMonth() + 1;
        this.day = SQLDate.getDate();
        this.zoneID = zoneID;
    }


    public static Date of(int year, int month, int day)
    {
        return new Date(year, month, day);
    }


    public static Date of(int year, int month, int day, ZoneId zoneID)
    {
        return new Date(year, month, day, zoneID);
    }


    public static Date of(LocalDate localDate)
    {
        return new Date(localDate);
    }


    public static Date of(LocalDate localDate, ZoneId zoneID)
    {
        return new Date(localDate, zoneID);
    }


    public static Date of(String dateString) throws InvalidDateException
    {
        return new Date(dateString);
    }


    public static Date of(String dateString, ZoneId zoneID) throws InvalidDateException
    {
        return new Date(dateString, zoneID);
    }


    public static Date of(java.sql.Date SQLDate)
    {
        return new Date(SQLDate);
    }


    public static Date of(java.sql.Date SQLDate, ZoneId zoneID)
    {
        return new Date(SQLDate, zoneID);
    }


    private String formatMonthWith2Characters()
    {
        return DateInternalService.formatMonthWith2Characters(this);
    }


    private String formatDayWith2Characters()
    {
        return DateInternalService.formatDayWith2Characters(this);
    }


    public String getDateStringSplitByHyphens()
    {
        StringBuilder sb = new StringBuilder(getDateStringSplitByHyphensWithoutYear());
        sb.append("-");
        sb.append(year);
        return sb.toString();
    }


    public String getDateStringSplitByHyphensWithoutYear()
    {
        StringBuilder sb = new StringBuilder(formatDayWith2Characters());
        sb.append("-");
        sb.append(formatMonthWith2Characters());
        return sb.toString();
    }


    public String getDateStringSplitByHyphensYearFirst()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(formatMonthWith2Characters());
        sb.append("-");
        sb.append(formatDayWith2Characters());
        return sb.toString();
    }


    public String getDateStringSplitBySlashesYearFirst()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("/");
        sb.append(formatMonthWith2Characters());
        sb.append("/");
        sb.append(formatDayWith2Characters());
        return sb.toString();
    }


    public String getDateStringSplitBySlashes()
    {
        StringBuilder sb = new StringBuilder(formatDayWith2Characters());
        sb.append("/");
        sb.append(formatMonthWith2Characters());
        sb.append("/");
        sb.append(year);
        return sb.toString();
    }


    public String getDateStringYearFirstWithLast2DigitsOfYear()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(year).substring(2));
        sb.append(formatMonthWith2Characters());
        sb.append(formatDayWith2Characters());
        return sb.toString();
    }


    public DateTokens getAsTokens()
    {
        return DateTokens.builder()
                        .days(day)
                        .month(month)
                        .year(year)
                        .build();
    }


    public String getLongDateYearFirstString() throws InvalidDateException
    {
        return CalendarService.convertDateToLongFormatYearFirst(getAsTokens());
    }


    public String getLongDateString() throws InvalidDateException
    {
        return CalendarService.convertDateToLongFormat(getAsTokens());
    }


    public LocalDate toLocalDate()
    {
        return LocalDate.of(year, Month.of(month), day);
    }


    public String toString()
    {
        return getDateStringSplitBySlashes();
    }


    @Override
    public int hashCode()
    {
        return DateInternalService.hashCode(this);
    }


    @Override
    public boolean equals(Object other)
    {
        return DateInternalService.equals(this, other);
    }


    public int getYear()
    {
        return this.year;
    }


    public int getMonth()
    {
        return this.month;
    }


    public int getDay()
    {
        return this.day;
    }


    public ZoneId getZoneID()
    {
        return this.zoneID;
    }
}