package io.github.orionlibs.orion_calendar.date;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_assert.InvalidArgumentException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;

public class DateRules
{
    public static void isValidSQLDate(String date)
    {
        Assert.notEmpty(date, "SQL date is null/empty.");

        if(date.indexOf("-") == -1)
        {
            throw new InvalidArgumentException("SQL date does not have the '-' separator.");
        }

    }


    public static void isValidSQLDate(java.sql.Date date)
    {
        Assert.notNull(date, "SQL date is null.");
    }


    public static void isValid(int year, int month, int day)
    {
        //NumberRules.haveNaturalNumberValue(year, month, day);
        Arrays.stream(new Integer[]
        {year, month, day}).forEach(x -> hasNaturalNumberValue(x));
    }


    private static void hasNaturalNumberValue(Number x)
    {
        Assert.isFalse(!DoesNumberHaveNaturalNumberValueTask(x), "Cannot perform operation on nonnatural numbers.");
    }


    private static boolean DoesNumberHaveNaturalNumberValueTask(Object x)
    {

        if(x instanceof BigInteger && isPositive((BigInteger)x))
        {
            return true;
        }
        else if(x instanceof BigDecimal)
        {
            BigDecimal temp = (BigDecimal)x;

            if(temp.compareTo(new BigDecimal(temp.toBigInteger())) == 0
                            && isPositive(temp))
            {
                return true;
            }

        }
        else if(x instanceof Number)
        {
            BigDecimal temp = new BigDecimal(((Number)x).toString());

            if(temp.compareTo(new BigDecimal(temp.toBigInteger())) == 0
                            && isPositive(temp))
            {
                return true;
            }

        }

        return false;
    }


    private static boolean isPositive(Number x)
    {

        if(isBigInteger(x))
        {
            return ((BigInteger)x).compareTo(BigInteger.ZERO) > 0;
        }
        else if(isBigDecimal(x))
        {
            return ((BigDecimal)x).compareTo(BigDecimal.ZERO) > 0;
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


    public static void isValid(LocalDate localDate)
    {
        Assert.notNull(localDate, "LocalDate is null.");
    }


    public static void isValid(String dateString) throws InvalidDateException
    {
        Assert.notEmpty(dateString, "dateString is null/empty.");

        if(dateString.indexOf("-") == -1 && dateString.indexOf("/") == -1)
        {
            throw new InvalidDateException();
        }

    }


    public static void isValid(Date date)
    {
        Assert.notNull(date, "Date is null.");
        isValid(date.getYear(), date.getMonth(), date.getDay());
    }
}