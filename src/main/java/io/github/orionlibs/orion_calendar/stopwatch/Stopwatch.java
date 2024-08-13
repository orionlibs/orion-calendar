package io.github.orionlibs.orion_calendar.stopwatch;

import io.github.orionlibs.orion_calendar.stopwatch.tasks.GetTimeLapsedTask;
import io.github.orionlibs.orion_calendar.stopwatch.tasks.GetTimeLapsedWithNanosecondsTask;

public class Stopwatch
{
    private long totalMillisecondsElapsed;
    private long totalSecondsElapsed;
    private long totalMinutesElapsed;
    private long totalHoursElapsed;
    private long initialNanoseconds;
    private long nanosecondsElapsed;


    public Stopwatch()
    {
    }


    public static Stopwatch of()
    {
        return new Stopwatch();
    }


    public void startTimer()
    {
        this.initialNanoseconds = System.nanoTime();
    }


    public void pauseTimer()
    {
        this.nanosecondsElapsed = nanosecondsElapsed + System.nanoTime() - getInitialNanoseconds();
    }


    public void continueTimer()
    {
        this.initialNanoseconds = System.nanoTime();
    }


    public void stopTimer()
    {
        this.nanosecondsElapsed = nanosecondsElapsed + System.nanoTime() - getInitialNanoseconds();
    }


    public String getTimeLapsed()
    {
        return new GetTimeLapsedTask().run(this);
    }


    public String getTimeLapsedWithNanoseconds()
    {
        return GetTimeLapsedWithNanosecondsTask.run(this, getTimeLapsed());
    }


    public void resetTimer()
    {
        this.totalSecondsElapsed = 0L;
        this.totalMinutesElapsed = 0L;
        this.totalHoursElapsed = 0L;
        this.initialNanoseconds = 0L;
        this.nanosecondsElapsed = 0L;
    }


    public long getNanosecondsElapsed()
    {
        return this.nanosecondsElapsed;
    }


    public long getInitialNanoseconds()
    {
        return this.initialNanoseconds;
    }


    public long getTotalSecondsElapsed()
    {
        return this.totalSecondsElapsed;
    }


    public long getTotalMinutesElapsed()
    {
        return this.totalMinutesElapsed;
    }


    public long getTotalHoursElapsed()
    {
        return this.totalHoursElapsed;
    }


    public void setTotalSecondsElapsed(long totalSecondsElapsed)
    {
        this.totalSecondsElapsed = totalSecondsElapsed;
    }


    public void setTotalMinutesElapsed(long totalMinutesElapsed)
    {
        this.totalMinutesElapsed = totalMinutesElapsed;
    }


    public void setTotalHoursElapsed(long totalHoursElapsed)
    {
        this.totalHoursElapsed = totalHoursElapsed;
    }


    public long getTotalMillisecondsElapsed()
    {
        return this.totalMillisecondsElapsed;
    }


    public void setTotalMillisecondsElapsed(long totalMillisecondsElapsed)
    {
        this.totalMillisecondsElapsed = totalMillisecondsElapsed;
    }
}