package io.github.orionlibs.orion_calendar;

public class FormattedSQLTimestamp
{
    public static void setupFormattedDateTime(SQLTimestamp timestamp)
    {
        SQLTimestampFormatter formatter = SQLTimestampFormatter.builder()
                        .timestamp(timestamp)
                        .build();
        formatter.setupFormattedDateTime();
    }


    public static void setupFormattedDateTimeUTC(SQLTimestamp timestamp)
    {
        SQLTimestampFormatter formatter = SQLTimestampFormatter.builder()
                        .timestamp(timestamp)
                        .build();
        formatter.setupFormattedDateTimeUTC();
    }
}