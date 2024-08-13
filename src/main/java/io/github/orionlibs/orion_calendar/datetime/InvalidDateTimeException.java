package io.github.orionlibs.orion_calendar.datetime;

import io.github.orionlibs.orion_assert.OrionUncheckedException;

public class InvalidDateTimeException extends OrionUncheckedException
{
    private static final String DefaultErrorMessage = "DateTimeString does not include dateTime, date or time information. It needs one of them.";


    public InvalidDateTimeException()
    {
        super(DefaultErrorMessage);
    }


    public InvalidDateTimeException(String message)
    {
        super(message);
    }


    public InvalidDateTimeException(String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments));
    }


    public InvalidDateTimeException(Throwable cause, String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments), cause);
    }


    public InvalidDateTimeException(Throwable cause)
    {
        super(cause, DefaultErrorMessage);
    }
}
