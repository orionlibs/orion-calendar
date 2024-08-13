package io.github.orionlibs.orion_calendar.time;

import io.github.orionlibs.orion_assert.Assert;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Arrays;

public class TimeRules
{
    public static void isValid(int hours, int minutes, int seconds, int milliseconds)
    {
        //NumberRules.haveNonnegativeIntegerNumberValue(hours, minutes, seconds, milliseconds);
        Arrays.stream(new Integer[]
                        {hours, minutes, seconds, milliseconds}).forEach(x -> hasNonnegativeIntegerNumberValue(x));
    }


    public static void isValid(int hours, int minutes, int seconds)
    {
        //NumberRules.haveNonnegativeIntegerNumberValue(hours, minutes, seconds);
        Arrays.stream(new Integer[]
                        {hours, minutes, seconds}).forEach(x -> hasNonnegativeIntegerNumberValue(x));
    }


    public static void isValid(int hours, int minutes, int seconds, long epochMilliseconds)
    {
        //NumberRules.haveNonnegativeIntegerNumberValue(hours, minutes, seconds, epochMilliseconds);
        Arrays.stream(new Long[]
                        {(long)hours, (long)minutes, (long)seconds, epochMilliseconds}).forEach(x -> hasNonnegativeIntegerNumberValue(x));
    }


    public static void isValid(int hours, int minutes, int seconds, int milliseconds, long epochMilliseconds)
    {
        //NumberRules.haveNonnegativeIntegerNumberValue(hours, minutes, seconds, milliseconds, epochMilliseconds);
        Arrays.stream(new Long[]
                        {(long)hours, (long)minutes, (long)seconds, (long)milliseconds, epochMilliseconds}).forEach(x -> hasNonnegativeIntegerNumberValue(x));
    }


    private static void hasNonnegativeIntegerNumberValue(Number x)
    {
        Assert.isFalse(isNegative(x), "Cannot perform operation on negative or nonnatural numbers.");
        Assert.isFalse(!hasIntegerValue(x), "Cannot perform operation on negative or nonnatural numbers.");
    }


    private static boolean isNegative(Number x)
    {
        if(isBigInteger(x))
        {
            return ((BigInteger)x).compareTo(BigInteger.ZERO) < 0;
        }
        else if(isBigDecimal(x))
        {
            return ((BigDecimal)x).compareTo(BigDecimal.ZERO) < 0;
        }
        return false;
    }


    private static boolean hasIntegerValue(Object x)
    {
        if(x instanceof BigInteger)
        {
            return true;
        }
        else if(x instanceof BigDecimal)
        {
            BigDecimal temp = (BigDecimal)x;
            if(temp.compareTo(new BigDecimal(temp.toBigInteger())) == 0)
            {
                return true;
            }
        }
        else if(x instanceof Number)
        {
            BigDecimal temp = new BigDecimal(((Number)x).toString());
            if(temp.compareTo(new BigDecimal(temp.toBigInteger())) == 0)
            {
                return true;
            }
        }
        return false;
    }


    private static boolean isBigInteger(Object x)
    {
        return x instanceof BigInteger;
    }


    private static boolean isBigDecimal(Object x)
    {
        return x instanceof BigDecimal;
    }


    public static void isValid(LocalTime localTime)
    {
        Assert.notNull(localTime, "LocalTime is null.");
    }


    public static void isValid(String timeString) throws InvalidTimeException
    {
        Assert.notEmpty(timeString, "timeString is null/empty.");
        if(timeString.indexOf(":") == -1)
        {
            throw new InvalidTimeException();
        }
    }


    public static void isValid(String timeString, boolean includesMilliseconds) throws InvalidTimeException
    {
        isValid(timeString);
        if(includesMilliseconds && timeString.indexOf(".") == -1)
        {
            throw new InvalidTimeException("timeString is supposed to include milliseconds, but it doesn't.");
        }
    }


    public static void isValid(Time time)
    {
        Assert.notNull(time, "Time is null.");
        isValid(time.getHours(), time.getMinutes(), time.getSeconds());
    }
}