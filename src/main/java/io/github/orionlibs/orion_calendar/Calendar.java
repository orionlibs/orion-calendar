package io.github.orionlibs.orion_calendar;

import java.util.HashMap;
import java.util.Map;

public class Calendar
{
    public static final int hoursInADay = 24;
    public static final int secondsInAMinute = 60;
    public static final int minutesInAnHour = 60;
    public static final int secondsInADay = hoursInADay * minutesInAnHour * secondsInAMinute;
    public static final int secondsInAnHour = secondsInAMinute * minutesInAnHour;
    public static final int nanosecondsInASecond = 1_000_000_000;
    public static final int nanosecondsInAMillisecond = 1_000_000;
    public static final int daysInAYear = 365;
    public static final int daysInAMonth = 30;
    public static final int daysInAWeek = 7;
    public static final int secondsInAMonth = daysInAMonth * hoursInADay * secondsInAnHour;
    public static final int secondsIn5Minutes = 5 * secondsInAMinute;
    public static final int monthsInAYear = 12;
    public static final int minutesInADay = hoursInADay * minutesInAnHour;
    public static final int minutesInAMonth = daysInAMonth * minutesInADay;
    public static final int hoursInAMonth = daysInAMonth * hoursInADay;
    public static final int millisecondsInASecond = 1_000;
    public static final int millisecondsInAMinute = millisecondsInASecond * secondsInAMinute;
    public static final int millisecondsInAnHour = millisecondsInAMinute * minutesInAnHour;
    public static final int millisecondsInADay = millisecondsInAnHour * hoursInADay;
    public static final int millisecondsInAMonth = millisecondsInADay * daysInAMonth;
    public static final int secondsInAYear = daysInAYear * secondsInADay;
    public static Map<Integer, String> dayNumberToNameMapper;
    public static Map<Integer, String> monthNumberToAbbreviatedNameMapper;
    public static Map<String, Integer> monthAbbreviatedNameToNumberMapper;
    public static Map<Integer, String> monthNumberToFullNameMapper;
    public static Map<String, Integer> monthFullNameToNumberMapper;
    public static final String fullDatetimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String fullDatetimePatternWithoutSeconds = "yyyy-MM-dd'T'HH:mm";
    public static final String fullDatetimePatternWithSpaceBetweenDateAndTime = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String fullDatetimePatternYearLast = "dd-MM-yyyy'T'HH:mm:ss.SSS";
    public static final String fullDatetimePatternYearLastWithSpaceBetweenDateAndTime = "dd-MM-yyyy HH:mm:ss.SSS";
    public static final String fullDatetimePatternYearLastWithCommaBetweenDateAndTime = "dd/MM/yyyy, HH:mm";

    static
    {
        initialiseDayNumberToNameMapper();
        initialiseMonthNumberToAbbreviatedNameMapper();
        initialiseMonthAbbreviatedNameToNumberMapper();
        initialiseMonthNumberToFullNameMapper();
        initialiseMonthFullNameToNumberMapper();
    }

    private static void initialiseMonthFullNameToNumberMapper()
    {
        monthFullNameToNumberMapper = new HashMap<>(12);
        monthFullNameToNumberMapper.put("January", 1);
        monthFullNameToNumberMapper.put("February", 2);
        monthFullNameToNumberMapper.put("March", 3);
        monthFullNameToNumberMapper.put("April", 4);
        monthFullNameToNumberMapper.put("May", 5);
        monthFullNameToNumberMapper.put("June", 6);
        monthFullNameToNumberMapper.put("July", 7);
        monthFullNameToNumberMapper.put("August", 8);
        monthFullNameToNumberMapper.put("September", 9);
        monthFullNameToNumberMapper.put("October", 10);
        monthFullNameToNumberMapper.put("November", 11);
        monthFullNameToNumberMapper.put("December", 12);
    }


    private static void initialiseMonthNumberToFullNameMapper()
    {
        monthNumberToFullNameMapper = new HashMap<>(12);
        monthNumberToFullNameMapper.put(1, "January");
        monthNumberToFullNameMapper.put(2, "February");
        monthNumberToFullNameMapper.put(3, "March");
        monthNumberToFullNameMapper.put(4, "April");
        monthNumberToFullNameMapper.put(5, "May");
        monthNumberToFullNameMapper.put(6, "June");
        monthNumberToFullNameMapper.put(7, "July");
        monthNumberToFullNameMapper.put(8, "August");
        monthNumberToFullNameMapper.put(9, "September");
        monthNumberToFullNameMapper.put(10, "October");
        monthNumberToFullNameMapper.put(11, "November");
        monthNumberToFullNameMapper.put(12, "December");
    }


    private static void initialiseMonthAbbreviatedNameToNumberMapper()
    {
        monthAbbreviatedNameToNumberMapper = new HashMap<>(12);
        monthAbbreviatedNameToNumberMapper.put("Jan", 1);
        monthAbbreviatedNameToNumberMapper.put("Feb", 2);
        monthAbbreviatedNameToNumberMapper.put("Mar", 3);
        monthAbbreviatedNameToNumberMapper.put("Apr", 4);
        monthAbbreviatedNameToNumberMapper.put("May", 5);
        monthAbbreviatedNameToNumberMapper.put("Jun", 6);
        monthAbbreviatedNameToNumberMapper.put("Jul", 7);
        monthAbbreviatedNameToNumberMapper.put("Aug", 8);
        monthAbbreviatedNameToNumberMapper.put("Sep", 9);
        monthAbbreviatedNameToNumberMapper.put("Oct", 10);
        monthAbbreviatedNameToNumberMapper.put("Nov", 11);
        monthAbbreviatedNameToNumberMapper.put("Dec", 12);
    }


    private static void initialiseMonthNumberToAbbreviatedNameMapper()
    {
        monthNumberToAbbreviatedNameMapper = new HashMap<>(12);
        monthNumberToAbbreviatedNameMapper.put(1, "Jan");
        monthNumberToAbbreviatedNameMapper.put(2, "Feb");
        monthNumberToAbbreviatedNameMapper.put(3, "Mar");
        monthNumberToAbbreviatedNameMapper.put(4, "Apr");
        monthNumberToAbbreviatedNameMapper.put(5, "May");
        monthNumberToAbbreviatedNameMapper.put(6, "Jun");
        monthNumberToAbbreviatedNameMapper.put(7, "Jul");
        monthNumberToAbbreviatedNameMapper.put(8, "Aug");
        monthNumberToAbbreviatedNameMapper.put(9, "Sep");
        monthNumberToAbbreviatedNameMapper.put(10, "Oct");
        monthNumberToAbbreviatedNameMapper.put(11, "Nov");
        monthNumberToAbbreviatedNameMapper.put(12, "Dec");
    }


    private static void initialiseDayNumberToNameMapper()
    {
        dayNumberToNameMapper = new HashMap<>(7);
        dayNumberToNameMapper.put(1, "Sunday");
        dayNumberToNameMapper.put(2, "Monday");
        dayNumberToNameMapper.put(3, "Tuesday");
        dayNumberToNameMapper.put(4, "Wednesday");
        dayNumberToNameMapper.put(5, "Thursday");
        dayNumberToNameMapper.put(6, "Friday");
        dayNumberToNameMapper.put(7, "Saturday");
    }
}