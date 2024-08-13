package io.github.orionlibs.orion_calendar;

import io.github.orionlibs.orion_calendar.calculation.tasks.AddDaysToDatetimeTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.AddWorkingDaysToDatetimeTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.AddYearsToDatetimeTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.GetAllWeeksOfTheLastNWeeksAsPairsOfStartAndEndDateTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.GetAllWeeksOfTheLastNWeeksAsPairsOfStartAndEndDateWithFirstDayOfWeekBeingMondayTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.GetAllWeeksOfTheLastNWeeksAsPairsOfStartAndEndDateWithFirstDayOfWeekBeingSundayTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.GetDaylightSavingsHoursToAddTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.GetNumberOfMonthDaysTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.difference.GetDifferenceAsListBetweenTwoDateTimesBasedOnChronoUnitsTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.difference.GetDifferenceBetweenTwoDateTimesInDaysTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.difference.GetDifferenceBetweenTwoDateTimesInMillisecondsTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.difference.GetDifferenceBetweenTwoDatesInDaysTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.difference.GetDifferenceBetweenTwoDatesInMillisecondsTask;
import io.github.orionlibs.orion_calendar.calculation.tasks.difference.GetDifferenceBetweenTwoTimesInMillisecondsTask;
import io.github.orionlibs.orion_calendar.conversion.OrionDuration;
import io.github.orionlibs.orion_calendar.conversion.tasks.ConvertDateToLongFormatTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.ConvertDateToLongFormatWithoutYearTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.ConvertDateToLongFormatYearFirstTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.ConvertMillisecondsToTemporalUnitTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.sqldate.ConvertSQLDateToDateObjectTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.sqldate.ConvertSQLDateToDateTimeObjectTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.sqldate.ConvertSQLTimestampToDateTimeObjectAndAdjustForDaylightSavingsTask;
import io.github.orionlibs.orion_calendar.conversion.tasks.sqldate.ConvertSQLTimestampToDateTimeObjectTask;
import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;
import io.github.orionlibs.orion_calendar.date.InvalidDateException;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.datetime.DateTimeRules;
import io.github.orionlibs.orion_calendar.datetime.InvalidDateTimeException;
import io.github.orionlibs.orion_calendar.tasks.GetDateFormatterToUseTask;
import io.github.orionlibs.orion_calendar.tasks.GetDateTimeFormatterToUseTask;
import io.github.orionlibs.orion_calendar.tasks.GetTimeFormatterToUseTask;
import io.github.orionlibs.orion_calendar.tasks.GetTimeZoneOffsetInHoursFromUTCTask;
import io.github.orionlibs.orion_calendar.tasks.GetTimeZoneOffsetInMinutesFromUTCTask;
import io.github.orionlibs.orion_calendar.tasks.GetTimeZoneOffsetStringFromUTCTask;
import io.github.orionlibs.orion_calendar.tasks.IsDateWithinRangeTask;
import io.github.orionlibs.orion_calendar.tasks.IsTimeWithinRangeIgnoringSecondsAndMillisecondsTask;
import io.github.orionlibs.orion_calendar.tasks.IsTimeWithinRangeTask;
import io.github.orionlibs.orion_calendar.tasks.SortDateTimesTask;
import io.github.orionlibs.orion_calendar.tasks.SortDatesTask;
import io.github.orionlibs.orion_calendar.tasks.TokeniseDateStringTask;
import io.github.orionlibs.orion_calendar.tasks.format.FormatDifferenceBetweenTwoDateTimesBasedOnUnitsTask;
import io.github.orionlibs.orion_calendar.tasks.format.FormatDurationTask;
import io.github.orionlibs.orion_calendar.tasks.format.FormatZonedDateAndTimeOrDateAndTimeOrDateOrTimeTask;
import io.github.orionlibs.orion_calendar.tasks.format.FormatZonedDateTimeOrDateTimeOrDateOrTimeTask;
import io.github.orionlibs.orion_calendar.tasks.format.GetDurationInSecondsRoundedUpToTheClosestNMinuteMarkTask;
import io.github.orionlibs.orion_calendar.tasks.format.GetFormattedDateTimeTask;
import io.github.orionlibs.orion_calendar.tasks.format.GetFormattedDurationInHoursAndMinutesTask;
import io.github.orionlibs.orion_calendar.tasks.format.PrintDateAsTodayOrTomorrowOrFullDateTask;
import io.github.orionlibs.orion_calendar.tasks.format.PrintDateAsTodayOrTomorrowOrFullDateTimeTask;
import io.github.orionlibs.orion_calendar.time.InvalidTimeException;
import io.github.orionlibs.orion_calendar.time.Time;
import io.github.orionlibs.orion_calendar.time.TimeRules;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class CalendarService
{
    //formatting methods
    public static FormattedDateTime getFormattedDateTime(DateTime datetime) throws InvalidDateException
    {
        return GetFormattedDateTimeTask.run(datetime);
    }


    public static String getFormattedCurrentTime()
    {
        return getCurrentDatetime().printTime();
    }


    public static String getFormattedCurrentTimeInUTC()
    {
        return getCurrentDatetime().printTimeAsIs();
    }


    public static String getFormattedDurationInHoursAndMinutes(int numberOfMinutes)
    {
        return GetFormattedDurationInHoursAndMinutesTask.run(numberOfMinutes);
    }


    public static String printDateAsTodayOrTomorrowOrFullDate(Date date)
    {
        return PrintDateAsTodayOrTomorrowOrFullDateTask.run(date);
    }


    public static String printDateAsTodayOrTomorrowOrFullDateTime(DateTime datetime) throws InvalidDateException
    {
        return PrintDateAsTodayOrTomorrowOrFullDateTimeTask.run(datetime, true);
    }


    public static String printDateAsTodayOrTomorrowOrFullDateTimeWithoutYear(DateTime datetime) throws InvalidDateException
    {
        return PrintDateAsTodayOrTomorrowOrFullDateTimeTask.run(datetime, false);
    }


    public static DateTimeFormatter getDateTimeFormatterToUse(String format)
    {
        return GetDateTimeFormatterToUseTask.run(format);
    }


    public static DateTimeFormatter getDateFormatterToUse(String dateString, String format) throws InvalidDateException
    {
        return GetDateFormatterToUseTask.run(dateString, format);
    }


    public static DateTimeFormatter getTimeFormatterToUse(String format)
    {
        return GetTimeFormatterToUseTask.run(format);
    }


    public static DateTime formatZonedDateTimeOrDateTimeOrDateOrTime(String dateTimeString, DateTimeFormatter formatter) throws InvalidDateTimeException
    {
        return new FormatZonedDateTimeOrDateTimeOrDateOrTimeTask().run(dateTimeString, formatter);
    }


    public static DateTime formatZonedDateAndTimeOrDateAndTimeOrDateOrTime(String dateString, String timeString, DateTimeFormatter formatter) throws InvalidDateTimeException
    {
        return new FormatZonedDateAndTimeOrDateAndTimeOrDateOrTimeTask().run(dateString, timeString, formatter);
    }


    public static DateTime formatZonedDateAndTimeOrDateAndTimeOrDateOrTime(String dateString, String timeString, DateTimeFormatter formatter, ZoneId zoneID) throws InvalidDateTimeException
    {
        return new FormatZonedDateAndTimeOrDateAndTimeOrDateOrTimeTask().run(dateString, timeString, formatter, zoneID);
    }


    public static FormattedDateTime formatDuration(OrionDuration duration)
    {
        return FormatDurationTask.run(duration);
    }


    public static FormattedDateTime formatDuration(long durationInSeconds)
    {
        return FormatDurationTask.run(getDurationBreakdown(durationInSeconds));
    }


    public static long getDurationInSecondsRoundedUpToTheClosestNMinuteMark(int numberOfMinutes, long durationInSeconds)
    {
        return GetDurationInSecondsRoundedUpToTheClosestNMinuteMarkTask.run(numberOfMinutes, durationInSeconds);
    }
    //conversion methods


    public static long convertDateTimeToEpochSeconds(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        return dateTime.toLocalDateTime().toEpochSecond(dateTime.getZoneOffset());
    }


    public static long convertDateTimeToEpochMilliseconds(DateTime dateTime)
    {
        return convertDateTimeToEpochSeconds(dateTime) * 1000;
    }


    public static DateTime convertEpochSecondsToDateTime(long epochSeconds)
    {
        return DateTime.of(LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.ofTotalSeconds(0)));
    }


    public static DateTime convertEpochMillisecondsToDateTime(long epochMilliseconds)
    {
        return convertEpochSecondsToDateTime(epochMilliseconds / 1000);
    }


    public static DateTime convertEpochSecondsToDateTime(long epochSeconds, ZoneOffset zoneOffset)
    {
        return DateTime.of(LocalDateTime.ofEpochSecond(epochSeconds, 0, zoneOffset));
    }


    public static DateTime convertEpochMillisecondsToDateTime(long epochMilliseconds, ZoneOffset zoneOffset)
    {
        return convertEpochSecondsToDateTime(epochMilliseconds / 1000, zoneOffset);
    }


    public static Timestamp convertEpochSecondsToTimestamp(long epochSeconds)
    {
        return new Timestamp(epochSeconds * 1000);
    }


    public static Timestamp convertEpochMillisecondsToTimestamp(long epochMilliseconds)
    {
        return convertEpochSecondsToTimestamp(epochMilliseconds / 1000);
    }


    public static SQLTimestamp convertEpochSecondsToSQLTimestamp(long epochSeconds)
    {
        return new SQLTimestamp(epochSeconds * 1000);
    }


    public static SQLTimestamp convertEpochMillisecondsToSQLTimestamp(long epochMilliseconds)
    {
        return convertEpochSecondsToSQLTimestamp(epochMilliseconds / 1000);
    }


    public static LocalDate convertInstantToLocalDate(Instant date, ZoneId zone)
    {
        CalendarRules.isValid(date);
        CalendarRules.isValid(zone);
        return Instant.ofEpochMilli(date.toEpochMilli()).atZone(zone).toLocalDate();
    }


    public static Timestamp convertInstantToTimestamp(Instant date)
    {
        CalendarRules.isValid(date);
        return new Timestamp(date.toEpochMilli());
    }


    public static Instant convertTimestampToInstant(Timestamp date)
    {
        CalendarRules.isValid(date);
        return date.toInstant();
    }


    public static SQLTimestamp convertInstantToSQLTimestamp(Instant date)
    {
        CalendarRules.isValid(date);
        return new SQLTimestamp(date.toEpochMilli());
    }


    /**
     * @param SQLDate = has the form yyyy-mm-dd
     * @return
     */
    public static DateTime convertSQLDateStringToDateTimeObject(String SQLDate)
    {
        return ConvertSQLDateToDateTimeObjectTask.run(SQLDate);
    }


    /**
     * @param SQLDate = has the form yyyy-mm-dd
     * @return
     */
    public static Date convertSQLDateStringToDateObject(String SQLDate)
    {
        return ConvertSQLDateToDateObjectTask.run(SQLDate);
    }


    /**
     * @param SQLDate = has the form yyyy-mm-dd
     * @return
     */
    public static Date convertSQLDateToDateObject(java.sql.Date SQLDate)
    {
        return ConvertSQLDateToDateObjectTask.run(SQLDate);
    }


    /**
     * @param SQLDate = has the form yyyy-mm-dd
     * @return
     */
    public static DateTime convertSQLDateToDateTimeObject(java.sql.Date SQLDate)
    {
        return ConvertSQLDateToDateTimeObjectTask.run(SQLDate);
    }


    public static DateTime convertTimestampToDateTimeObject(Timestamp SQLDate)
    {
        return ConvertSQLTimestampToDateTimeObjectTask.run(SQLDate);
    }


    public static DateTime convertTimestampToDateTimeObjectAndAdjustForDaylightSavings(Timestamp SQLDate)
    {
        return ConvertSQLTimestampToDateTimeObjectAndAdjustForDaylightSavingsTask.run(SQLDate);
    }


    public static Date convertTimestampToDateObject(Timestamp SQLDate)
    {
        return convertTimestampToDateTimeObject(SQLDate).getDate();
    }


    public static Time convertTimestampToTimeObject(Timestamp SQLDate)
    {
        return convertTimestampToDateTimeObject(SQLDate).getTime();
    }


    public static String convertTimestampToDateString(Timestamp SQLDate)
    {
        return convertTimestampToDateObject(SQLDate).getDateStringSplitByHyphensYearFirst();
    }


    public static String convertTimestampToTimeString(Timestamp SQLDate)
    {
        return convertTimestampToTimeObject(SQLDate).getTimeStringWithoutMilliseconds();
    }


    public static String convertTimestampToDateTimeStringWith0Time(Timestamp SQLDate)
    {
        return convertTimestampToDateObject(SQLDate).getDateStringSplitByHyphensYearFirst() + " 00:00:00";
    }


    public static DateTime convertUTCDateTimeToAnotherZone(DateTime datetime)
    {
        return addHoursToDatetime(datetime, getTimeZoneOffsetInHoursFromUTC(datetime));
    }


    public static int getDaysAsSeconds(int numberOfDays)
    {
        return numberOfDays * Calendar.secondsInADay;
    }


    public static long getHoursAsSeconds(long numberOfHours)
    {
        return numberOfHours * Calendar.secondsInAnHour;
    }


    public static long getMinutesAsSeconds(long numberOfMinutes)
    {
        return numberOfMinutes * Calendar.secondsInAMinute;
    }


    public static double getMillisecondsAsHours(long milliseconds)
    {
        return milliseconds / (Calendar.millisecondsInAnHour * 1.0);
    }


    public static double getMillisecondsAsMinutes(long milliseconds)
    {
        return milliseconds / (Calendar.millisecondsInAMinute * 1.0);
    }


    public static DateTime convertDateObjectToDateTimeObject(Date date)
    {
        return DateTime.of(date);
    }


    public static DateTime convertDateTimeStringToDateTimeObject(String dateTimeString) throws InvalidDateTimeException
    {
        return convertDateTimeStringToDateTimeObject(dateTimeString, null);
    }


    public static DateTime convertDateTimeStringToDateTimeObject(String dateTimeString, String format) throws InvalidDateTimeException
    {
        DateTimeRules.isValidDateTime(dateTimeString);
        DateTimeFormatter formatter = getDateTimeFormatterToUse(format);
        return formatZonedDateTimeOrDateTimeOrDateOrTime(dateTimeString, formatter);
    }


    public static DateTime convertDateAndTimeStringToDateTimeObject(String dateString, String timeString) throws InvalidDateTimeException
    {
        DateTimeRules.isValidDateAndTime(dateString, timeString);
        DateTimeFormatter formatter = getDateTimeFormatterToUse(null);
        return formatZonedDateAndTimeOrDateAndTimeOrDateOrTime(dateString, timeString, formatter);
    }


    public static SQLTimestamp convertDateAndTimeStringToSQLTimestampObjectAndAdjustForDaylightSavingsForUTC(String dateString, String timeString) throws InvalidDateTimeException
    {
        return convertDateTimeObjectToSQLTimestamp(convertDateAndTimeStringToDateTimeObjectAndAdjustForDaylightSavingsForUTC(dateString, timeString));
    }


    public static DateTime convertDateAndTimeStringToDateTimeObjectAndAdjustForDaylightSavingsForUTC(String dateString, String timeString) throws InvalidDateTimeException
    {
        int timezoneOffsetInHours = getDaylightSavingsHoursToAdd();
        DateTime dateTimeTemp = convertDateAndTimeStringToDateTimeObject(dateString, timeString);
        return addHoursToDatetime(dateTimeTemp, -1 * timezoneOffsetInHours);
    }


    public static DateTime convertDateAndTimeStringToDateTimeObjectAddingUTCOffset(String dateString, String timeString) throws InvalidDateTimeException
    {
        DateTimeRules.isValidDateAndTime(dateString, timeString);
        DateTimeFormatter formatter = getDateTimeFormatterToUse(null);
        return formatZonedDateAndTimeOrDateAndTimeOrDateOrTime(dateString, timeString + getTimeZoneOffsetStringFromUTC(), formatter);
    }


    public static DateTime convertDateAndTimeStringToDateTimeObject(String dateString, String timeString, String format) throws InvalidDateTimeException
    {
        DateTimeRules.isValidDateAndTime(dateString, timeString);
        DateTimeFormatter formatter = getDateTimeFormatterToUse(format);
        return formatZonedDateAndTimeOrDateAndTimeOrDateOrTime(dateString, timeString, formatter);
    }


    public static Date convertDateStringToDateObject(String dateString) throws InvalidDateException
    {
        return convertDateStringToDateObject(dateString, null);
    }


    public static Date convertDateStringWithoutDelimitersToDateObject(String dateString) throws InvalidDateException
    {
        return convertDateStringWithoutDelimitersToDateObject(dateString, null);
    }


    public static DateTime convertDateStringToDateTimeObject(String dateString) throws InvalidDateException
    {
        return convertDateStringToDateTimeObject(dateString, null);
    }


    public static DateRangeWithSQLTimestamp convert2DateStringsToDateRange(String startDate, String endDate) throws InvalidDateException
    {
        return DateRangeWithSQLTimestamp.builder()
                        .startDate(convertDateTimeObjectsToSQLTimestamp(Date.of(startDate), Time.of(0, 0, 0)))
                        .endDate(convertDateTimeObjectsToSQLTimestamp(Date.of(endDate), Time.of(23, 59, 59, 999)))
                        .build();
    }


    public static DateRangeWithSQLTimestamp convert2DatetimesToDateRange(DateTime startDate, DateTime endDate)
    {
        return DateRangeWithSQLTimestamp.builder()
                        .startDate(startDate.toOrionSQLTimestamp())
                        .endDate(endDate.toOrionSQLTimestamp())
                        .build();
    }


    public static Timestamp convertDateStringToTimestampObject(String dateString) throws InvalidDateException
    {
        return new Timestamp(convertDateStringToDateTimeObject(dateString).toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateStringToSQLTimestampObject(String dateString) throws InvalidDateException
    {
        return new SQLTimestamp(convertDateStringToDateTimeObject(dateString).toInstant().toEpochMilli());
    }


    public static Timestamp convertDateAndTimeStringToTimestampObject(String dateString, String timeString) throws InvalidDateTimeException
    {
        return new Timestamp(convertDateTimeStringToDateTimeObject(dateString, timeString).toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateAndTimeStringToSQLTimestampObject(String dateString, String timeString) throws InvalidDateTimeException
    {
        return new SQLTimestamp(convertDateAndTimeStringToDateTimeObject(dateString, timeString).toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateAndTimeStringToSQLTimestampObjectAddingUTCOffset(String dateString, String timeString) throws InvalidDateTimeException
    {
        return new SQLTimestamp(convertDateAndTimeStringToDateTimeObjectAddingUTCOffset(dateString, timeString).toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateTimeToSQLTimestampObjectAddingUTCOffset(DateTime datetime) throws InvalidDateTimeException
    {
        String date = datetime.getDate().getDateStringSplitByHyphensYearFirst();
        String time = datetime.getTime().getTimeStringWithoutMilliseconds();
        return new SQLTimestamp(convertDateAndTimeStringToDateTimeObjectAddingUTCOffset(date, time).toInstant().toEpochMilli());
    }


    public static Timestamp convertDateTimeObjectToTimestamp(DateTime datetime)
    {
        return new Timestamp(datetime.toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateTimeObjectToSQLTimestamp(DateTime datetime)
    {
        return new SQLTimestamp(datetime.toInstant().toEpochMilli());
    }


    public static Timestamp convertDateTimeObjectsToTimestamp(Date date, Time time)
    {
        return new Timestamp(DateTime.of(date, time).toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateTimeObjectsToSQLTimestamp(Date date, Time time)
    {
        return new SQLTimestamp(DateTime.of(date, time).toInstant().toEpochMilli());
    }


    public static SQLTimestamp convertDateObjectToSQLTimestamp(Date date)
    {
        return new SQLTimestamp(convertDateObjectToDateTimeObject(date).toInstant().toEpochMilli());
    }


    public static Date convertDateStringToDateObject(String dateString, String format) throws InvalidDateException
    {
        DateRules.isValid(dateString);
        return Date.of(LocalDate.parse(dateString, getDateFormatterToUse(dateString, format)));
    }


    public static Date convertDateStringWithoutDelimitersToDateObject(String dateString, String format) throws InvalidDateException
    {
        String newDateString = dateString.substring(0, 4) + "-" + dateString.substring(4, 6) + "-" + dateString.substring(6, 8);
        return Date.of(LocalDate.parse(newDateString, getDateFormatterToUse(newDateString, format)));
    }


    public static DateTime convertDateStringToDateTimeObject(String dateString, String format) throws InvalidDateException
    {
        return DateTime.of(convertDateStringToDateObject(dateString, format));
    }


    public static Time convertTimeStringToTimeObject(String timeString) throws InvalidTimeException
    {
        return convertTimeStringToTimeObject(timeString, null);
    }


    public static DateTime convertTimeStringToDateTimeObject(String timeString) throws InvalidTimeException
    {
        return DateTime.of(getCurrentDate(), convertTimeStringToTimeObject(timeString, null));
    }


    public static DateTime convertTimeStringToDateTimeObjectAdjustingForDaylightSavings(String timeString) throws InvalidTimeException
    {
        return DateTime.of(getCurrentDateAdjustingForDaylightSavings(), convertTimeStringToTimeObject(timeString, null));
    }


    public static Time convertTimeStringToTimeObject(String timeString, String format) throws InvalidTimeException
    {
        TimeRules.isValid(timeString);
        return Time.of(LocalTime.parse(timeString, getTimeFormatterToUse(format)));
    }


    public static long convertMillisecondsToTemporalUnit(long milliseconds, ChronoUnit unit)
    {
        return ConvertMillisecondsToTemporalUnitTask.run(milliseconds, unit);
    }


    public static DateTime convertTimeObjectToDateTimeObject(Time time)
    {
        return DateTime.of(getCurrentDate(), time);
    }


    public static Date convertZonedDateTimeToDate(ZonedDateTime zonedDateTime)
    {
        DateTimeRules.isValid(zonedDateTime);
        return Date.of(zonedDateTime.getYear(), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth());
    }


    public static DateTime convertZonedDatetimeToDatetime(ZonedDateTime zonedDateTime)
    {
        return DateTime.of(zonedDateTime);
    }


    public static ZonedDateTime convertDatetimeToZonedDatetime(DateTime dateTime)
    {
        DateTimeRules.isValid(dateTime);
        Date date = dateTime.getDate();
        Time time = dateTime.getTime();
        LocalDateTime dateTimeAsLocal = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), time.getHours(), time.getMinutes(), time.getSeconds());
        return ZonedDateTime.of(dateTimeAsLocal, dateTime.getZoneID());
    }


    public static ZonedDateTime convertTimestampToZonedDatetime(Timestamp dateTime)
    {
        DateTimeRules.isValid(dateTime);
        return convertDatetimeToZonedDatetime(convertTimestampToDateTimeObject(dateTime));
    }


    public static String convertDateToLongFormatYearFirst(DateTokens dateTokens) throws InvalidDateException
    {
        return ConvertDateToLongFormatYearFirstTask.run(dateTokens);
    }


    public static String convertDateToLongFormat(DateTokens dateTokens) throws InvalidDateException
    {
        return ConvertDateToLongFormatTask.run(dateTokens);
    }


    public static String convertDateToLongFormatWithoutYear(DateTokens dateTokens) throws InvalidDateException
    {
        return ConvertDateToLongFormatWithoutYearTask.run(dateTokens);
    }


    public static String convertDateToLongFormatYearFirst(Date date) throws InvalidDateException
    {
        DateRules.isValid(date);
        return convertDateToLongFormatYearFirst(date.getAsTokens());
    }


    public static String convertDateToLongFormatWithoutYear(Date date) throws InvalidDateException
    {
        DateRules.isValid(date);
        return convertDateToLongFormatWithoutYear(date.getAsTokens());
    }
    //extraction methods


    /**
     * @param date = with format dd-mm-yyyy or dd/mm/yyyy
     * @return year
     * @throws InvalidDateException
     */
    public static int extractYearFromDateString(String date) throws InvalidDateException
    {
        return convertDateStringToDateObject(date).getYear();
    }


    /**
     * @param date = has the form yyyy-mm-dd
     * @return
     */
    public static int extractYearFromSQLDateString(String SQLDate)
    {
        return convertSQLDateStringToDateObject(SQLDate).getYear();
    }


    /**
     * @param date = with format dd-mm-yyyy or dd/mm/yyyy
     * @return year
     * @throws InvalidDateException
     */
    public static int extractMonthFromDateString(String date) throws InvalidDateException
    {
        return convertDateStringToDateObject(date).getMonth();
    }


    /**
     * @param date = has the form yyyy-mm-dd
     * @return
     */
    public static int extractMonthFromSQLDateString(String SQLDate)
    {
        return convertSQLDateStringToDateObject(SQLDate).getMonth();
    }


    /**
     * @param date = with format dd-mm-yyyy or dd/mm/yyyy
     * @return year
     * @throws InvalidDateException
     */
    public static int extractDayFromDateString(String date) throws InvalidDateException
    {
        return convertDateStringToDateObject(date).getDay();
    }


    /**
     * @param date = has the form yyyy-mm-dd
     * @return
     */
    public static int extractDayFromSQLDateString(String SQLDate)
    {
        return convertSQLDateStringToDateObject(SQLDate).getDay();
    }


    public static int getDayOfWeekAsIntegerFromDateTime(DateTime dateTime)
    {
        return convertDatetimeToZonedDatetime(dateTime).toLocalDate().getDayOfWeek().getValue();
    }


    public static int getCurrentDayOfWeekAsInteger()
    {
        return convertDatetimeToZonedDatetime(getCurrentDatetime()).toLocalDate().getDayOfWeek().getValue();
    }


    public static int getCurrentDayOfWeekAsIntegerAdjustingForDaylightSavings()
    {
        return convertDatetimeToZonedDatetime(getCurrentDateTimeAdjustingForDaylightSavings()).toLocalDate().getDayOfWeek().getValue();
    }


    public static DayOfWeek getDayOfWeekFromDate(Date date)
    {
        return date.toLocalDate().getDayOfWeek();
    }


    public static int getDayValueFromDateTime(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        DateRules.isValid(dateTime.getDate());
        return dateTime.getDate().getDay();
    }


    public static int getHoursFromDateTime(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        TimeRules.isValid(dateTime.getTime());
        return dateTime.getTime().getHours();
    }


    public static int getMinutesFromDateTime(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        TimeRules.isValid(dateTime.getTime());
        return dateTime.getTime().getMinutes();
    }


    public static int getMonthValueFromDateTime(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        DateRules.isValid(dateTime.getDate());
        return dateTime.getDate().getMonth();
    }


    public static int getSecondsFromDateTime(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        TimeRules.isValid(dateTime.getTime());
        return dateTime.getTime().getSeconds();
    }


    public static String getTimeZoneOffsetStringFromUTC()
    {
        return GetTimeZoneOffsetStringFromUTCTask.run(getCurrentDatetimeAtLocalZone());
    }


    public static int getTimeZoneOffsetInHoursFromUTC()
    {
        return GetTimeZoneOffsetInHoursFromUTCTask.run(getCurrentDatetimeAtLocalZone());
    }


    public static int getTimeZoneOffsetInMinutesFromUTC()
    {
        return GetTimeZoneOffsetInMinutesFromUTCTask.run(getCurrentDatetimeAtLocalZone());
    }


    public static String getTimeZoneOffsetStringFromUTC(DateTime dateTime)
    {
        return GetTimeZoneOffsetStringFromUTCTask.run(dateTime);
    }


    public static int getTimeZoneOffsetInHoursFromUTC(DateTime dateTime)
    {
        return GetTimeZoneOffsetInHoursFromUTCTask.run(dateTime);
    }


    public static int getTimeZoneOffsetInMinutesFromUTC(DateTime dateTime)
    {
        return GetTimeZoneOffsetInMinutesFromUTCTask.run(dateTime);
    }


    public static int getYearFromDateTime(DateTime dateTime)
    {
        DateTimeRules.isValidIgnoringZoneID(dateTime);
        DateRules.isValid(dateTime.getDate());
        return dateTime.getDate().getYear();
    }
    //number of month days methods


    /**
     * @param SQLDate = has the form yyyy-mm-dd
     * @return
     * @throws InvalidDateException
     */
    public static int getNumberOfMonthDaysFromSQLDate(String SQLDate) throws InvalidDateException
    {
        DateRules.isValid(SQLDate);
        return getNumberOfMonthDays(convertSQLDateStringToDateTimeObject(SQLDate));
    }


    /**
     * @param date = has the form dd-mm-yyyy or dd/mm/yyyy
     * @return
     * @throws InvalidDateException
     * @throws InvalidTimeException
     */
    public static int getNumberOfMonthDays(String date) throws InvalidDateException, InvalidTimeException
    {
        DateRules.isValid(date);
        return getNumberOfMonthDays(DateTime.of(date, "00:00:00"));
    }


    public static int getNumberOfMonthDays(Date date)
    {
        return GetNumberOfMonthDaysTask.run(date);
    }


    public static int getNumberOfMonthDays(DateTime date)
    {
        return GetNumberOfMonthDaysTask.run(date);
    }
    //age calculation methods


    public static int calculateAgeInYears(String birthdate)
    {
        return getCurrentDatetime().getDate().getYear() - extractYearFromSQLDateString(birthdate);
    }


    public static int calculateAgeInYears(Date birthdate)
    {
        DateRules.isValid(birthdate);
        return calculateAgeInYears(birthdate.getDateStringSplitByHyphensYearFirst());
    }


    public static int calculateAgeInYears(DateTime birthdate)
    {
        DateTimeRules.isValidIgnoringZoneID(birthdate);
        return calculateAgeInYears(birthdate.getDate().getDateStringSplitByHyphensYearFirst());
    }
    //current datetime methods


    public static int getCurrentYear()
    {
        return getCurrentDatetime().getDate().getYear();
    }


    public static int getCurrentMonth()
    {
        return getCurrentDatetime().getDate().getMonth();
    }


    public static String getCurrentMonthName()
    {
        return Calendar.monthNumberToFullNameMapper.get(getCurrentMonth());
    }


    public static String getMonthName(int month)
    {
        return Calendar.monthNumberToFullNameMapper.get(month);
    }


    public static int getCurrentDay()
    {
        return getCurrentDatetime().getDate().getDay();
    }


    public static String getCurrentDayName()
    {
        return Calendar.dayNumberToNameMapper.get(getCurrentDay());
    }


    public static ZonedDateTime getCurrentZonedDatetime()
    {
        return getCurrentZonedDatetime(ZoneId.of("UTC"));
    }


    public static ZonedDateTime getCurrentZonedDatetime(ZoneId zoneID)
    {
        return ZonedDateTime.now(zoneID);
    }


    public static DateTime getCurrentDatetime()
    {
        return DateTime.of(getCurrentZonedDatetime());
    }


    public static Time getCurrentTime()
    {
        return getCurrentDatetime().getTime();
    }


    public static DateTime getCurrentDatetimeAtZone(ZoneId zoneID)
    {
        return DateTime.of(getCurrentZonedDatetime(zoneID));
    }


    public static DateTime getCurrentDatetimeAtLocalZone()
    {
        return DateTime.of(getCurrentZonedDatetime(ZoneId.systemDefault()));
    }


    public static Date getCurrentDate()
    {
        return getCurrentDatetime().getDate();
    }


    public static Date getCurrentDateAdjustingForDaylightSavings()
    {
        return getCurrentDateTimeAdjustingForDaylightSavings().getDate();
    }


    public static DateTime getCurrentDateTimeAdjustingForDaylightSavings()
    {
        int timezoneOffsetInHours = getDaylightSavingsHoursToAdd();
        return addHoursToDatetime(getCurrentDatetime(), timezoneOffsetInHours);
    }


    public static Timestamp getCurrentDatetimeAsTimestamp()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setNanos((int)(System.nanoTime() % 1000000000));
        return timestamp;
        //return new Timestamp(getCurrentInstant().toEpochMilli());
    }


    public static SQLTimestamp getCurrentDatetimeAsSQLTimestamp()
    {
        return new SQLTimestamp(getCurrentDatetimeAsTimestamp());
    }


    public static Instant getCurrentInstant()
    {
        return getCurrentZonedDatetime().toInstant();
    }
    //calculation methods


    public static int getDaylightSavingsHoursToAdd()
    {

        //System.out.println(TimeZone.getTimeZone("GB").inDaylightTime(new java.util.Date()));
        //System.out.println(TimeZone.getTimeZone("GB").inDaylightTime(new java.util.Date(2021, 9, 30)));
        if(ZoneId.of("GB").getRules().isDaylightSavings(Instant.now()))
        {
            return TimeZone.getTimeZone("GB").getDSTSavings() / Calendar.millisecondsInAnHour;
        }
        else
        {
            return 0;
        }

    }


    public static int getDaylightSavingsMinutesToAdd()
    {
        return getTimeZoneOffsetInMinutesFromUTC();
    }


    public static int getDaylightSavingsHoursToAdd(ZoneId zoneID)
    {
        return GetDaylightSavingsHoursToAddTask.run(zoneID);
    }


    public static int getDaylightSavingsMinutesToAdd(ZoneId zoneID)
    {

        if(zoneID != null)
        {
            return getTimeZoneOffsetInMinutesFromUTC(getCurrentDatetimeAtZone(zoneID));
        }
        else
        {
            return getTimeZoneOffsetInMinutesFromUTC(getCurrentDatetimeAtLocalZone());
        }

    }


    public static DateTime addDaysToCurrentDatetime(long numberOfDays)
    {
        return addDaysToCurrentDatetime(numberOfDays, ZoneId.of("UTC"));
    }


    public static DateTime addDaysToCurrentDatetimeAdjustingForDaylightSavings(long numberOfDays)
    {
        return addDaysToCurrentDatetime(numberOfDays, ZoneId.of("GB"));
    }


    public static DateTime addDaysToCurrentDatetime(long numberOfDays, ZoneId zoneID)
    {
        return addDaysToDatetime(getCurrentDatetime(), numberOfDays, zoneID);
    }


    public static DateTime addHoursToCurrentDatetime(long numberOfHours)
    {
        return addHoursToDatetime(getCurrentDatetime(), numberOfHours);
    }


    public static DateTime addMinutesToCurrentDatetime(long numberOfMinutes)
    {
        return addMinutesToCurrentDatetime(numberOfMinutes, ZoneId.of("UTC"));
    }


    public static SQLTimestamp addMinutesToCurrentSQLDatetime(long numberOfMinutes)
    {
        return addMinutesToDatetime(getCurrentDatetimeAsSQLTimestamp(), numberOfMinutes, ZoneId.of("UTC"));
    }


    public static DateTime addMinutesToCurrentDatetime(long numberOfMinutes, ZoneId zoneID)
    {
        return addMinutesToDatetime(getCurrentDatetime(), numberOfMinutes, zoneID);
    }


    public static DateTime addSecondsToCurrentDatetime(long numberOfSeconds)
    {
        return addSecondsToCurrentDatetime(numberOfSeconds, ZoneId.of("UTC"));
    }


    public static DateTime addSecondsToCurrentDatetime(long numberOfSeconds, ZoneId zoneID)
    {
        return addSecondsToDatetime(getCurrentDatetime(), numberOfSeconds, zoneID);
    }


    public static DateTime addDaysToDatetime(DateTime datetime, long numberOfDays)
    {
        return addDaysToDatetime(datetime, numberOfDays, ZoneId.of("UTC"));
    }


    public static DateTime addWorkingDaysToDatetime(DateTime datetime, long numberOfDays)
    {
        return addWorkingDaysToDatetime(datetime, numberOfDays, ZoneId.of("UTC"));
    }


    public static DateTime addWorkingDaysToDatetime(SQLTimestamp datetime, long numberOfDays)
    {
        return addWorkingDaysToDatetime(DateTime.of(datetime), numberOfDays, ZoneId.of("UTC"));
    }


    public static DateTime addDaysToDatetime(DateTime datetime, long numberOfDays, ZoneId zone)
    {
        return AddDaysToDatetimeTask.run(datetime, numberOfDays, zone);
    }


    public static DateTime addWorkingDaysToDatetime(DateTime datetime, long numberOfDays, ZoneId zone)
    {
        return AddWorkingDaysToDatetimeTask.run(datetime, numberOfDays, zone);
    }


    public static DateTime addHoursToDatetime(DateTime datetime, long numberOfHours)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().plusSeconds(getHoursAsSeconds(numberOfHours)), ZoneId.of("UTC"));
        return DateTime.of(zonedDatetime);
    }


    public static SQLTimestamp addHoursToDatetime(Timestamp datetime, long numberOfHours)
    {
        DateTimeRules.isValid(datetime);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().plusSeconds(getHoursAsSeconds(numberOfHours)), ZoneId.of("UTC"));
        return SQLTimestamp.of(zonedDatetime);
    }


    public static DateTime addMinutesToDatetime(DateTime datetime, long numberOfMinutes)
    {
        return addMinutesToDatetime(datetime, numberOfMinutes, ZoneId.of("UTC"));
    }


    public static SQLTimestamp addMinutesToDatetime(SQLTimestamp datetime, long numberOfMinutes)
    {
        return addMinutesToDatetime(datetime, numberOfMinutes, ZoneId.of("UTC"));
    }


    public static DateTime addMinutesToDatetime(DateTime datetime, long numberOfMinutes, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().plusSeconds(getMinutesAsSeconds(numberOfMinutes)), zone);
        return DateTime.of(zonedDatetime);
    }


    public static SQLTimestamp addMinutesToDatetime(SQLTimestamp datetime, long numberOfMinutes, ZoneId zone)
    {
        DateTimeRules.isValid(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().plusSeconds(getMinutesAsSeconds(numberOfMinutes)), ZoneId.of("UTC"));
        return SQLTimestamp.of(zonedDatetime);
    }


    public static DateTime addSecondsToDatetime(DateTime datetime, long numberOfSeconds)
    {
        return addSecondsToDatetime(datetime, numberOfSeconds, ZoneId.of("UTC"));
    }


    public static DateTime addSecondsToDatetime(DateTime datetime, long numberOfSeconds, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().plusSeconds(numberOfSeconds), zone);
        return DateTime.of(zonedDatetime);
    }


    public static DateTime addMillisecondsToDatetime(DateTime datetime, long numberOfMilliseconds)
    {
        return addMillisecondsToDatetime(datetime, numberOfMilliseconds, ZoneId.of("UTC"));
    }


    public static DateTime addMillisecondsToDatetime(DateTime datetime, long numberOfMilliseconds, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().plusMillis(numberOfMilliseconds), zone);
        return DateTime.of(zonedDatetime);
    }


    public static DateTime addMonthsToDatetime(DateTime datetime, int numberOfMonths)
    {
        return addMonthsToDatetime(datetime, numberOfMonths, ZoneId.of("UTC"));
    }


    public static DateTime addMonthsToDatetime(DateTime datetime, int numberOfMonths, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = datetime.toLocalDate().plusMonths(numberOfMonths).atStartOfDay(zone);
        return DateTime.of(zonedDatetime);
    }


    public static DateTime addWeeksToDatetime(DateTime datetime, int numberOfWeeks)
    {
        return addWeeksToDatetime(datetime, numberOfWeeks, ZoneId.of("UTC"));
    }


    public static DateTime addWeeksToDatetime(DateTime datetime, int numberOfWeeks, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = datetime.toLocalDate().plusWeeks(numberOfWeeks).atStartOfDay(zone);
        return DateTime.of(zonedDatetime);
    }


    public static DateTime addYearsToDatetime(DateTime datetime, int numberOfYears)
    {
        return addYearsToDatetime(datetime, numberOfYears, ZoneId.of("UTC"));
    }


    public static DateTime addYearsToDatetime(DateTime datetime, int numberOfYears, ZoneId zone)
    {
        return AddYearsToDatetimeTask.run(datetime, numberOfYears, zone);
    }


    public static DateTime subtractMillisecondsFromDatetime(DateTime datetime, long numberOfMilliseconds)
    {
        return subtractMillisecondsFromDatetime(datetime, numberOfMilliseconds, ZoneId.of("UTC"));
    }


    public static DateTime subtractMillisecondsFromDatetime(DateTime datetime, long numberOfMilliseconds, ZoneId zone)
    {
        DateTimeRules.isValidIgnoringZoneID(datetime);
        CalendarRules.isValid(zone);
        ZonedDateTime zonedDatetime = ZonedDateTime.ofInstant(datetime.toInstant().minusMillis(numberOfMilliseconds), zone);
        return DateTime.of(zonedDatetime);
    }
    //datetime difference calculation methods


    public static String formatDifferenceBetweenTwoDateTimesBasedOnUnits(Long[] durationAsList, String units)
    {
        return new FormatDifferenceBetweenTwoDateTimesBasedOnUnitsTask().run(durationAsList, units);
    }


    public static Long[] getDifferenceAsListBetweenTwoDateTimesBasedOnUnits(String units, DateTime startDateTime, DateTime endDateTime)
    {
        return new GetDifferenceAsListBetweenTwoDateTimesBasedOnChronoUnitsTask().run(units, startDateTime, endDateTime);
    }


    public static long getDifferenceBetweenTwoDateTimesInMilliseconds(DateTime dateTime1, DateTime dateTime2)
    {
        return GetDifferenceBetweenTwoDateTimesInMillisecondsTask.run(dateTime1, dateTime2);
    }


    public static long getDifferenceBetweenTwoDateTimesInSeconds(DateTime dateTime1, DateTime dateTime2)
    {
        return GetDifferenceBetweenTwoDateTimesInMillisecondsTask.run(dateTime1, dateTime2) / 1000L;
    }


    public static long getDifferenceBetweenTwoDateTimesInMilliseconds(SQLTimestamp dateTime1, SQLTimestamp dateTime2)
    {
        return GetDifferenceBetweenTwoDateTimesInMillisecondsTask.run(convertTimestampToDateTimeObject(dateTime1), convertTimestampToDateTimeObject(dateTime2));
    }


    public static long getDifferenceBetweenTwoDateTimesInMilliseconds(String dateTimeString1, String dateTimeString2) throws InvalidDateTimeException
    {
        DateTime dateTime1 = convertDateTimeStringToDateTimeObject(dateTimeString1);
        DateTime dateTime2 = convertDateTimeStringToDateTimeObject(dateTimeString2);
        return GetDifferenceBetweenTwoDateTimesInMillisecondsTask.run(dateTime1, dateTime2);
    }


    public static long getAbsoluteDifferenceBetweenTwoDateTimesInMilliseconds(DateTime dateTime1, DateTime dateTime2)
    {
        return Math.abs(getDifferenceBetweenTwoDateTimesInMilliseconds(dateTime1, dateTime2));
    }


    public static long getAbsoluteDifferenceBetweenTwoDateTimesInSeconds(DateTime dateTime1, DateTime dateTime2)
    {
        return Math.abs(getDifferenceBetweenTwoDateTimesInSeconds(dateTime1, dateTime2));
    }


    public static long getAbsoluteDifferenceBetweenTwoDateTimesInMilliseconds(SQLTimestamp dateTime1, SQLTimestamp dateTime2)
    {
        return Math.abs(getDifferenceBetweenTwoDateTimesInMilliseconds(dateTime1, dateTime2));
    }


    public static long getAbsoluteDifferenceBetweenTwoDateTimesInSeconds(SQLTimestamp dateTime1, SQLTimestamp dateTime2)
    {
        return Math.abs(getDifferenceBetweenTwoDateTimesInMilliseconds(dateTime1, dateTime2)) / Calendar.millisecondsInASecond;
    }


    public static long getAbsoluteDifferenceBetweenTwoDateTimesInMinutes(SQLTimestamp dateTime1, SQLTimestamp dateTime2)
    {
        return Math.abs(getDifferenceBetweenTwoDateTimesInMilliseconds(dateTime1, dateTime2)) / Calendar.millisecondsInAMinute;
    }


    public static long getAbsoluteDifferenceBetweenTwoDateTimesInMilliseconds(String dateTimeString1, String dateTimeString2) throws InvalidDateTimeException
    {
        return Math.abs(getDifferenceBetweenTwoDateTimesInMilliseconds(dateTimeString1, dateTimeString2));
    }


    public static long getDifferenceBetweenTwoDatesInMilliseconds(Date date1, Date date2)
    {
        return GetDifferenceBetweenTwoDatesInMillisecondsTask.run(date1, date2);
    }


    public static long getDifferenceBetweenTwoDatesInDays(Date date1, Date date2)
    {
        return GetDifferenceBetweenTwoDatesInDaysTask.run(date1, date2);
    }


    public static long getDifferenceBetweenTwoDateTimesInDays(DateTime date1, DateTime date2)
    {
        return GetDifferenceBetweenTwoDateTimesInDaysTask.run(date1, date2);
    }


    public static long getDifferenceBetweenTwoDatesInMilliseconds(String dateString1, String dateString2) throws InvalidDateException
    {
        Date date1 = convertDateStringToDateObject(dateString1);
        Date date2 = convertDateStringToDateObject(dateString2);
        return GetDifferenceBetweenTwoDatesInMillisecondsTask.run(date1, date2);
    }


    public static long getDifferenceBetweenTwoTimesInMilliseconds(Time time1, Time time2)
    {
        return GetDifferenceBetweenTwoTimesInMillisecondsTask.run(time1, time2);
    }


    public static long getDifferenceBetweenTwoTimesInMilliseconds(String timeString1, String timeString2) throws InvalidTimeException
    {
        Time time1 = convertTimeStringToTimeObject(timeString1);
        Time time2 = convertTimeStringToTimeObject(timeString2);
        return GetDifferenceBetweenTwoTimesInMillisecondsTask.run(time1, time2);
    }
    //datetime range calculation methods


    public static boolean isWeekendDay(ZonedDateTime dateTime)
    {
        return dateTime.getDayOfWeek() == DayOfWeek.SATURDAY || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY;
    }


    public static boolean isWeekday(ZonedDateTime dateTime)
    {
        return !isWeekendDay(dateTime);
    }


    public static boolean isDateWithinRange(DateTime dateTimeToCheck, DateTime dateTime1, DateTime dateTime2)
    {
        return IsDateWithinRangeTask.run(dateTimeToCheck, dateTime1, dateTime2);
    }


    public static boolean isDateWithinRange(Timestamp dateTimeToCheck, Timestamp dateTime1, Timestamp dateTime2)
    {
        return isDateWithinRange(convertTimestampToDateTimeObject(dateTimeToCheck), convertTimestampToDateTimeObject(dateTime1), convertTimestampToDateTimeObject(dateTime2));
    }


    public static boolean isDateWithinRange(DateTime dateTimeToCheck, Timestamp dateTime1, Timestamp dateTime2)
    {
        return isDateWithinRange(dateTimeToCheck, convertTimestampToDateTimeObject(dateTime1), convertTimestampToDateTimeObject(dateTime2));
    }


    public static boolean isDateTimeEqualsAnother(DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateTimeAfterAnother(DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isAfter(zonedDatetime2);
    }


    public static boolean isDateTimeAfterAnother(Timestamp dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertTimestampToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isAfter(zonedDatetime2);
    }


    public static boolean isDateAfterAnother(Timestamp dateTime1, DateTime dateTime2)
    {
        LocalDate localDate1 = convertTimestampToDateObject(dateTime1).toLocalDate();
        LocalDate localDate2 = dateTime2.toLocalDate();
        return localDate1.isAfter(localDate2);
    }


    public static boolean isDateTimeAfterAnother(Timestamp dateTime1, Timestamp dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertTimestampToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertTimestampToZonedDatetime(dateTime2);
        return zonedDatetime1.isAfter(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeAnother(DateTime dateTime1, Timestamp dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertTimestampToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeAnother(Timestamp dateTime1, Timestamp dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertTimestampToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertTimestampToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2);
    }


    public static boolean isDateTimeAfterOrEqualsAnother(DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isAfter(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeAnotherOrEquals(DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeAnotherOrEquals(Date date1, Date date2)
    {
        ZonedDateTime zonedDatetime1 = convertDateObjectToDateTimeObject(date1).toZonedDateTime();
        ZonedDateTime zonedDatetime2 = convertDateObjectToDateTimeObject(date2).toZonedDateTime();
        return zonedDatetime1.isBefore(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateBeforeAnotherOrEquals(Timestamp date1, Date date2)
    {
        LocalDate localDate1 = date1.toLocalDateTime().toLocalDate();
        LocalDate localDate2 = convertDateObjectToDateTimeObject(date2).toLocalDate();
        return localDate1.isBefore(localDate2) || localDate1.isEqual(localDate2);
    }


    public static boolean isDateEquals(Date date1, Date date2)
    {
        return date1.getDateStringSplitByHyphensYearFirst().equals(date2.getDateStringSplitByHyphensYearFirst());
    }


    public static boolean isDateEquals(Timestamp dateTime1, Timestamp dateTime2)
    {
        return dateTime1.toLocalDateTime().toLocalDate().toString().equals(dateTime2.toLocalDateTime().toLocalDate().toString());
    }


    public static boolean isDateEquals(Timestamp dateTime1, Date date2)
    {
        return dateTime1.toLocalDateTime().toLocalDate().toString().equals(date2.getDateStringSplitByHyphensYearFirst());
    }


    public static boolean isDateEquals(Date date1, Timestamp dateTime2)
    {
        return date1.getDateStringSplitByHyphensYearFirst().equals(dateTime2.toLocalDateTime().toLocalDate().toString());
    }


    public static boolean isDateTimeBeforeAnother(Date date1, Date date2)
    {
        ZonedDateTime zonedDatetime1 = convertDateObjectToDateTimeObject(date1).toZonedDateTime();
        ZonedDateTime zonedDatetime2 = convertDateObjectToDateTimeObject(date2).toZonedDateTime();
        return zonedDatetime1.isBefore(zonedDatetime2);
    }


    public static boolean hasExpired(Timestamp dateTime)
    {
        return isDateTimeBeforeAnother(dateTime, getCurrentDatetime());
    }


    public static boolean hasNotExpired(Timestamp dateTime)
    {
        return !hasExpired(dateTime);
    }


    public static boolean hasExpired(DateTime dateTime)
    {
        return isDateTimeBeforeAnother(dateTime, getCurrentDatetime());
    }


    public static boolean isDateTimeBeforeAnother(Timestamp dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertTimestampToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeAnother(DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2);
    }


    public static boolean isDateBeforeAnother(Timestamp dateTime1, DateTime dateTime2)
    {
        LocalDate localDate1 = convertTimestampToDateObject(dateTime1).toLocalDate();
        LocalDate localDate2 = dateTime2.toLocalDate();
        return localDate1.isBefore(localDate2);
    }


    public static boolean isDateTimeBeforeOrEqualsAnother(DateTime dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeOrEqualsAnother(Timestamp dateTime1, Timestamp dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertTimestampToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertTimestampToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeOrEqualsAnother(Timestamp dateTime1, DateTime dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertTimestampToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertDatetimeToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isDateTimeBeforeOrEqualsAnother(DateTime dateTime1, Timestamp dateTime2)
    {
        ZonedDateTime zonedDatetime1 = convertDatetimeToZonedDatetime(dateTime1);
        ZonedDateTime zonedDatetime2 = convertTimestampToZonedDatetime(dateTime2);
        return zonedDatetime1.isBefore(zonedDatetime2) || zonedDatetime1.isEqual(zonedDatetime2);
    }


    public static boolean isTimeBeforeOrEqualsAnother(Time time1, Time time2)
    {
        LocalTime localTime1 = time1.toLocalTime();
        LocalTime localTime2 = time2.toLocalTime();
        return localTime1.isBefore(localTime2) || localTime1.equals(localTime2);
    }


    public static boolean isTimeWithinRange(Time timeToCheck, Time time1, Time time2)
    {
        return IsTimeWithinRangeTask.run(timeToCheck, time1, time2);
    }


    public static boolean isTimeWithinRangeIgnoringSecondsAndMilliseconds(Time timeToCheck, Time time1, Time time2)
    {
        return IsTimeWithinRangeIgnoringSecondsAndMillisecondsTask.run(timeToCheck, time1, time2);
    }


    public static Period getPeriodBetween(DateTime dateTime1, DateTime dateTime2)
    {
        DateTimeRules.isValid(dateTime1);
        DateTimeRules.isValid(dateTime2);
        List<DateTime> sortedDateTimes = sortDateTimes(Arrays.asList(dateTime1, dateTime2));
        return Period.between(sortedDateTimes.get(0).toLocalDate(), sortedDateTimes.get(sortedDateTimes.size() - 1).toLocalDate());
    }


    public static Period getPeriod(List<DateTime> datetimes)
    {
        DateTimeRules.areValid(datetimes);
        return Period.between(datetimes.get(0).toLocalDate(), datetimes.get(datetimes.size() - 1).toLocalDate());
    }


    public static OrionDuration getDurationBreakdown(long seconds)
    {
        return new OrionDuration(Math.abs(seconds));
    }
    //percentage of a month calculation methods


    /**
     * @param dateString = has the form dd-mm-yyyy or dd/mm/yyyy
     * @return
     * @throws InvalidDateException
     */
    public static double getPercentageOfMonthFromADateUntilTheEndOfMonth(String dateString) throws InvalidDateException
    {
        return getPercentageOfMonthFromADateUntilTheEndOfMonth(convertDateStringToDateObject(dateString));
    }


    /**
     * @param SQLDate = has the form yyyy-mm-dd
     * @return
     */
    public static double getPercentageOfMonthFromADateUntilTheEndOfMonthFromSQLDate(String SQLDate)
    {
        convertSQLDateStringToDateTimeObject(SQLDate);
        return getPercentageOfMonthFromADateUntilTheEndOfMonth(convertSQLDateStringToDateTimeObject(SQLDate));
    }


    public static double getPercentageOfMonthFromADateUntilTheEndOfMonth(DateTime date)
    {
        int daysInMonth = getNumberOfMonthDays(date);
        return ((daysInMonth - date.getDate().getDay() + 1) / (daysInMonth * 1.0));
    }


    public static double getPercentageOfMonthFromADateUntilTheEndOfMonth(Date date)
    {
        int daysInMonth = getNumberOfMonthDays(date);
        return ((daysInMonth - date.getDay() + 1) / (daysInMonth * 1.0));
    }
    //sorting methods


    public static List<Date> sortDates(List<Date> dates)
    {
        return SortDatesTask.run(dates);
    }


    public static List<Date> sortDatesInReverse(List<Date> dates)
    {
        List<Date> sortedDates = SortDatesTask.run(dates);
        Collections.reverse(sortedDates);
        return sortedDates;
    }


    public static List<DateTime> sortDateTimes(List<DateTime> dateTimes)
    {
        return SortDateTimesTask.run(dateTimes);
    }


    public static List<DateTime> sortDateTimesInReverse(List<DateTime> dateTimes)
    {
        List<DateTime> sortedDatetimes = SortDateTimesTask.run(dateTimes);
        Collections.reverse(sortedDatetimes);
        return sortedDatetimes;
    }
    //misc methods


    public static int getNumberOfDaysBetweenDayAndSunday(DayOfWeek day)
    {
        return 7 - Math.abs(day.getValue() - DayOfWeek.SUNDAY.getValue());
    }


    public static int getNumberOfDaysBetweenDayAndSaturday(DayOfWeek day)
    {
        return 7 - Math.abs(day.getValue() - DayOfWeek.SATURDAY.getValue());
    }


    public static Date getLastSundayDateBasedOnCurrentDate()
    {
        Date currentDate = getCurrentDateAdjustingForDaylightSavings();
        int diff = getNumberOfDaysBetweenDayAndSunday(currentDate.toLocalDate().getDayOfWeek());
        ZonedDateTime zonedDatetime = DateTime.of(currentDate).toZonedDateTime().minusDays(diff);
        return convertZonedDateTimeToDate(zonedDatetime);
    }


    public static Date getLastSaturdayDateBasedOnCurrentDate()
    {
        Date currentDate = getCurrentDateAdjustingForDaylightSavings();
        int diff = getNumberOfDaysBetweenDayAndSaturday(currentDate.toLocalDate().getDayOfWeek());
        ZonedDateTime zonedDatetime = DateTime.of(currentDate).toZonedDateTime().minusDays(diff);
        return convertZonedDateTimeToDate(zonedDatetime);
    }


    public static String[] tokeniseDateString(String date) throws InvalidDateException
    {
        return TokeniseDateStringTask.run(date);
    }


    public static List<WeekPeriod> getAllWeekPeriodsOfTheLastNWeeksAsPairsOfStartAndEndDate(int numberOfWeeksToReturn)
    {
        return GetAllWeeksOfTheLastNWeeksAsPairsOfStartAndEndDateTask.run(numberOfWeeksToReturn);
    }


    public static List<WeekPeriod> getAllWeekPeriodsOfTheLastNWeeksAsPairsOfStartAndEndDateWithFirstDayOfWeekBeingSunday(int numberOfWeeksToReturn)
    {
        return GetAllWeeksOfTheLastNWeeksAsPairsOfStartAndEndDateWithFirstDayOfWeekBeingSundayTask.run(numberOfWeeksToReturn);
    }


    public static WeekPeriods getAllWeekPeriodsOfTheLastNWeeksAsPairsOfStartAndEndDateWithFirstDayOfWeekBeingMonday(int numberOfWeeksToReturn)
    {
        return GetAllWeeksOfTheLastNWeeksAsPairsOfStartAndEndDateWithFirstDayOfWeekBeingMondayTask.run(numberOfWeeksToReturn);
    }


    public static int convertNumberOfWeeksToMonths(int numberOfWeeks)
    {
        return (int)Math.ceil(numberOfWeeks * 7.0d / 30);
    }


    public static WeekPeriod buildWeekPeriodSplitByHyphensYearFirst(String startOfWeek)
    {
        return WeekPeriod.of(startOfWeek);
    }
}