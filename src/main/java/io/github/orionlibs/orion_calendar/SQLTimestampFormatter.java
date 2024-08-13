package io.github.orionlibs.orion_calendar;

import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_string.StringsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SQLTimestampFormatter
{
    private SQLTimestamp timestamp;


    public void setupFormattedDateTime()
    {

        if(!timestamp.getHaveFormattedValuesBeenInitialised())
        {
            int offsetHours = CalendarService.getDaylightSavingsHoursToAdd();
            DateTime dateTimeAdjustedForDaylightSavings = null;

            if(offsetHours != 0)
            {
                dateTimeAdjustedForDaylightSavings = DateTime.of(CalendarService.addHoursToDatetime(timestamp, offsetHours));
                timestamp.setFormattedDate(dateTimeAdjustedForDaylightSavings.printDateAsIsInInternationalFormat());
                timestamp.setFormattedDateWithSlashes(dateTimeAdjustedForDaylightSavings.printDateAsIsInInternationalFormatWithSlashes());
                timestamp.setFormattedDateWithSlashesYearFirst(dateTimeAdjustedForDaylightSavings.printDateAsIsInInternationalFormatWithSlashesYearFirst());
                timestamp.setFormattedDateWithHyphens(dateTimeAdjustedForDaylightSavings.printDateAsIsInInternationalFormat());
                timestamp.setFormattedDateWithHyphensYearFirst(dateTimeAdjustedForDaylightSavings.printDateAsIsInSQLFormat());
                timestamp.setFormattedDateWithHyphensWithoutYear(dateTimeAdjustedForDaylightSavings.getDate().getDateStringSplitByHyphensWithoutYear());
                timestamp.setFormattedTimeWithoutSeconds(dateTimeAdjustedForDaylightSavings.printTimeAsIsWithoutSeconds());
                timestamp.setFormattedTime(dateTimeAdjustedForDaylightSavings.printTimeAsIs());
            }
            else
            {
                setupFormattedDateTimeUTC();
            }

        }

    }


    public void setupFormattedDateTimeUTC()
    {

        if(!timestamp.getHaveFormattedValuesBeenInitialised())
        {
            int years = timestamp.getYears();
            buildFormattedDate(years);
            buildFormattedDateWithHyphensYearFirst(years);
            buildFormattedDateWithSlashesYearFirst(years);
            buildFormattedDateWithSlashes(years);
            buildFormattedTimeWithoutSeconds();
            buildFormattedTime();
            buildFormattedDateWithHyphens(years);
            buildFormattedDateWithHyphensWithoutYear();
        }

    }


    private void buildFormattedDateWithHyphensWithoutYear()
    {
        StringBuilder sb = new StringBuilder(formatDayWith2Characters());
        sb.append("-");
        sb.append(formatMonthWith2Characters());
        timestamp.setFormattedDateWithHyphensWithoutYear(sb.toString());
    }


    private void buildFormattedDateWithHyphens(int years)
    {
        StringBuilder sb = new StringBuilder(formatDayWith2Characters());
        sb.append("-");
        sb.append(formatMonthWith2Characters());
        sb.append("-");
        sb.append(years);
        timestamp.setFormattedDateWithHyphens(sb.toString());
    }


    private void buildFormattedTime()
    {
        StringBuilder sb = new StringBuilder(formatHoursString());
        sb.append(":");
        sb.append(formatMinutesString());
        sb.append(":");
        sb.append(formatSecondsString());
        timestamp.setFormattedTime(sb.toString());
    }


    private void buildFormattedTimeWithoutSeconds()
    {
        StringBuilder sb = new StringBuilder(formatHoursString());
        sb.append(":");
        sb.append(formatMinutesString());
        timestamp.setFormattedTimeWithoutSeconds(sb.toString());
    }


    private void buildFormattedDateWithSlashes(int years)
    {
        StringBuilder sb = new StringBuilder(formatDayWith2Characters());
        sb.append("/");
        sb.append(formatMonthWith2Characters());
        sb.append("/");
        sb.append(years);
        timestamp.setFormattedDateWithSlashes(sb.toString());
    }


    private void buildFormattedDateWithSlashesYearFirst(int years)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(years);
        sb.append("/");
        sb.append(formatMonthWith2Characters());
        sb.append("/");
        sb.append(formatDayWith2Characters());
        timestamp.setFormattedDateWithSlashesYearFirst(sb.toString());
    }


    private void buildFormattedDateWithHyphensYearFirst(int years)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(years);
        sb.append("-");
        sb.append(formatMonthWith2Characters());
        sb.append("-");
        sb.append(formatDayWith2Characters());
        timestamp.setFormattedDateWithHyphensYearFirst(sb.toString());
    }


    private void buildFormattedDate(int years)
    {
        StringBuilder sb = new StringBuilder(formatDayWith2Characters());
        sb.append("-");
        sb.append(formatMonthWith2Characters());
        sb.append("-");
        sb.append(years);
        timestamp.setFormattedDate(sb.toString());
    }


    @SuppressWarnings("deprecation")
    private String formatMonthWith2Characters()
    {
        return StringsService.formatWith2Characters(timestamp.getMonth() + 1);
    }


    @SuppressWarnings("deprecation")
    private String formatDayWith2Characters()
    {
        return StringsService.formatWith2Characters(timestamp.getDate());
    }


    @SuppressWarnings("deprecation")
    public String formatSecondsString()
    {
        return StringsService.formatWith2Characters(timestamp.getSeconds());
    }


    @SuppressWarnings("deprecation")
    private String formatHoursString()
    {
        return StringsService.formatWith2Characters(timestamp.getHours());
    }


    @SuppressWarnings("deprecation")
    private String formatMinutesString()
    {
        return StringsService.formatWith2Characters(timestamp.getMinutes());
    }
}