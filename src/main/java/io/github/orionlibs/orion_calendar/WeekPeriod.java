package io.github.orionlibs.orion_calendar;

import io.github.orionlibs.orion_calendar.date.Date;
import io.github.orionlibs.orion_calendar.datetime.DateTime;
import io.github.orionlibs.orion_calendar.time.Time;
import java.util.Objects;
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
public class WeekPeriod
{
    private String startDate;
    private String endDate;
    private String startDatePrintable;
    private String endDatePrintable;
    private Date startOfWeek;
    private Date endOfWeek;
    private DateTime startDateOfWeekAfterMidnight;
    private DateTime endDateOfWeekBeforeMidnight;


    public static WeekPeriod of(String startDateOfWeek)
    {
        DateTime startDateOfWeekAfterMidnight = DateTime.of(startDateOfWeek, "00:00:00");
        DateTime endDateOfWeekBeforeMidnight = CalendarService.addDaysToDatetime(startDateOfWeekAfterMidnight, 6);
        endDateOfWeekBeforeMidnight = DateTime.of(endDateOfWeekBeforeMidnight.getDate(), Time.of("23:59:59"));
        return WeekPeriod.builder()
                        .startOfWeek(startDateOfWeekAfterMidnight.getDate())
                        .endOfWeek(endDateOfWeekBeforeMidnight.getDate())
                        .startDate(startDateOfWeek)
                        .endDate(endDateOfWeekBeforeMidnight.getDate().getDateStringSplitByHyphensYearFirst())
                        .startDatePrintable(startDateOfWeekAfterMidnight.getDate().getDateStringSplitByHyphens())
                        .endDatePrintable(endDateOfWeekBeforeMidnight.getDate().getDateStringSplitByHyphens())
                        .startDateOfWeekAfterMidnight(startDateOfWeekAfterMidnight)
                        .endDateOfWeekBeforeMidnight(endDateOfWeekBeforeMidnight)
                        .build();
    }


    @Override
    public boolean equals(Object other)
    {

        if(this == other)
        {
            return true;
        }
        else if(other instanceof WeekPeriod)
        {
            WeekPeriod otherTemp = (WeekPeriod)other;
            return Objects.equals(startDate, otherTemp.getStartDate()) && Objects.equals(endDate, otherTemp.getEndDate());
        }
        else
        {
            return false;
        }

    }


    @Override
    public int hashCode()
    {
        return Objects.hash(startDate, endDate);
    }
}