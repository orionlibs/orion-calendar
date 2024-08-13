package io.github.orionlibs.orion_calendar.conversion.tasks.sqldate;

import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.date.DateRules;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertSQLDateToDateTimeObjectTask
{
    public static DateTime run(String SQLDate)
    {
        DateRules.isValidSQLDate(SQLDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.parse(SQLDate, formatter);
        Date date = Date.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        return DateTime.of(date);
    }


    public static DateTime run(java.sql.Date SQLDate)
    {
        DateRules.isValidSQLDate(SQLDate);
        Date date = Date.of(SQLDate.getYear() + 1900, SQLDate.getMonth() + 1, SQLDate.getDate());
        return DateTime.of(date);
    }
}