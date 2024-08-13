package io.github.orionlibs.orion_calendar.tasks.format;

import io.github.orionlibs.orion_calendar.FormattedDateTime;
import io.github.orionlibs.orion_calendar.conversion.OrionDuration;

public class FormatDurationTask
{
    public static FormattedDateTime run(OrionDuration duration)
    {
        String formattedNumberOfYears = formatChronoUnit(duration.getYears(), "year", "years");
        String formattedNumberOfMonths = formatChronoUnit(duration.getMonths(), "month", "months");
        String formattedNumberOfWeeks = formatChronoUnit(duration.getWeeks(), "week", "weeks");
        String formattedNumberOfDays = formatChronoUnit(duration.getDays(), "day", "days");
        String formattedNumberOfHours = formatChronoUnit(duration.getHours(), "hour", "hours");
        String formattedNumberOfMinutes = formatChronoUnit(duration.getMinutes(), "minute", "minutes");
        String formattedNumberOfSeconds = formatChronoUnit(duration.getSeconds(), "second", "seconds");
        String formattedDurationIn1Sentence = "";

        if(duration.getYears() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfYears;

            if(duration.getMonths() > 0
                            || duration.getWeeks() > 0
                            || duration.getDays() > 0
                            || duration.getHours() > 0
                            || duration.getMinutes() > 0
                            || duration.getSeconds() > 0)
            {
                formattedDurationIn1Sentence += ", ";
            }

        }

        if(duration.getMonths() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfMonths;

            if(duration.getWeeks() > 0
                            || duration.getDays() > 0
                            || duration.getHours() > 0
                            || duration.getMinutes() > 0
                            || duration.getSeconds() > 0)
            {
                formattedDurationIn1Sentence += ", ";
            }

        }

        if(duration.getWeeks() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfWeeks;

            if(duration.getDays() > 0
                            || duration.getHours() > 0
                            || duration.getMinutes() > 0
                            || duration.getSeconds() > 0)
            {
                formattedDurationIn1Sentence += ", ";
            }

        }

        if(duration.getDays() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfDays;

            if(duration.getHours() > 0 || duration.getMinutes() > 0 || duration.getSeconds() > 0)
            {
                formattedDurationIn1Sentence += ", ";
            }

        }

        if(duration.getHours() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfHours;

            if(duration.getMinutes() > 0 || duration.getSeconds() > 0)
            {
                formattedDurationIn1Sentence += ", ";
            }

        }

        if(duration.getMinutes() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfMinutes;

            if(duration.getSeconds() > 0)
            {
                formattedDurationIn1Sentence += ", ";
            }

        }

        if(duration.getSeconds() > 0)
        {
            formattedDurationIn1Sentence += formattedNumberOfSeconds;
        }

        return FormattedDateTime.builder()
                        .formattedNumberOfYears(formattedNumberOfYears)
                        .formattedNumberOfMonths(formattedNumberOfMonths)
                        .formattedNumberOfDays(formattedNumberOfDays)
                        .formattedNumberOfHours(formattedNumberOfHours)
                        .formattedNumberOfMinutes(formattedNumberOfMinutes)
                        .formattedNumberOfSeconds(formattedNumberOfSeconds)
                        .formattedDurationIn1Sentence(formattedDurationIn1Sentence)
                        .build();
    }


    private static String formatChronoUnit(long chronoUnit, String chronoUnitNameSingular, String chronoUnitNamePlural)
    {
        String formattedNumberOfYears = "";

        if(chronoUnit > 0L)
        {
            formattedNumberOfYears += chronoUnit;

            if(chronoUnit == 1L)
            {
                formattedNumberOfYears += " " + chronoUnitNameSingular;
            }
            else
            {
                formattedNumberOfYears += " " + chronoUnitNamePlural;
            }

        }

        return formattedNumberOfYears;
    }
}