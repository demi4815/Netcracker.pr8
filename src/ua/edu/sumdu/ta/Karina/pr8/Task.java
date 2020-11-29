package ua.edu.sumdu.ta.Karina.pr8;

/**
 * The first part of this project will be creating a class that describes the "task" data type, which
 * contains information about the essence of the task, its status (active / inactive), notification time, interval
 * the time after which you need to repeat the notification about it. Any time
 * task alert is measured in seconds relative to some start, for example, when it was started
 * task manager.
 * @version 1.0
 */

public class Task
{

    public String title;
    public boolean active;
    public int time, start, end, repeat;


    public Task () { }

    /**
     * Task class constructor for a one-time tasks
     * A new task is considered inactive when created
     * @param title - task title
     * @param time - task alert time
     */
    public Task(String title, int time)
    {
        try
        {
            check(time);

            setTitle(title);
            setTime(time);
            setActive(false);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Incorrect data for a one-time tasks");
            throw e;
        }
    }

    /**
     * Task class constructor for a repeated tasks
     * A new task is considered inactive when created
     * @param title - task title
     * @param start - start time of task notification
     * @param end - time to stop task alert
     * @param repeat - time interval after which it is necessary to repeat the task notification
     */
    public Task(String title, int start, int end, int repeat)
    {
        try
        {
            check(start, end, repeat);

            setTitle(title);

            if(repeat == 0)
            {
                setTime(start);
            }
            else
            {
                setTime(start, end, repeat);
            }

            setActive(false);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Incorrect data for a repeated tasks");
            throw e;
        }

    }

    /**
     * Method for data validation for a one-time task
     * @param time - parameter to check
     * @return true if the data is correct
     */
    public void check(int time)
    {
        if(time < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method for data validation for a repeated task
     * @param start - parameter to check
     * @param end - parameter to check
     * @param repeat - parameter to check
     * @return true if the data is correct
     */
    public void check(int start, int end, int repeat)
    {
        if (start > end || start < 0 || end < 0 || repeat < 0)
        {
            throw new IllegalArgumentException();
        }
    }



    /**
     * Method to get the title of the task
     * @return title of the task
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Method for setting the title of the task
     * @param title - title of the task
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Method for checking the status of the task (the task will be considered active if notification about it should
     * occur at a specified time, otherwise - we will consider the task inactive)
     * @return boolean activity indicator
     */
    public boolean isActive()
    {
        return active;
    }

    /**
     * Method for setting the activity indicator
     * @param active - boolean activity indicator
     */
    public void setActive(boolean active)
    {
        this.active = active;
    }

    /**
     * Method for setting the time for a one-time task
     * @param time - task alert time
     */
    public void setTime(int time)
    {
        this.time = time;
        start = time;
        end = time;
    }

    /**
     * Method for setting the time for a repeated task
     * @param start - start time of task notification
     * @param end - time to stop task alert
     * @param repeat - time interval after which it is necessary to repeat the task notification
     */
    public void setTime(int start, int end, int repeat)
    {
        if (start == end) setTime(start);
        else
        {
            this.start = start;
            this.end = end;
            this.repeat = repeat;
        }
    }

    /**
     * Method for get the start time of notification for a repeated task
     * or single alert time for a one-time task
     * @return - time of notification
     */
    public int getTime()
    {
        if (isRepeated()) return start;
        else return time;
    }

    /**
     * Method for get the start time of notification for a repeated task
     * or single alert time for a one-time task
     * @return - time of notification
     */
    public int getStartTime()
    {
        if (isRepeated()) return start;
        else return time;
    }

    /**
     * Method for get the end time of notification for a repeated task
     * or single alert time for a one-time task
     * @return - time of notification
     */
    public int getEndTime()
    {
        if (isRepeated()) return end;
        else return time;
    }

    /**
     * Method for getting the time interval after which it is necessary to repeat
     * task alert (for a repeated task) or 0 (for a one-time task)
     * @return time interval
     */
    public int getRepeatInterval()
    {
        if (isRepeated()) return repeat;
        else return 0;
    }

    /**
     * Iinformation about whether the task is repeated
     * @return true if the task is repeated
     */
    public boolean isRepeated()
    {
        return start != end;
    }

    /**
     * Method that returns a description of the given task
     * @return description string
     */
    @Override
    public String toString()
    {
        if(!isActive()) return "Task " + title + " is inactive";
        else
        {
            if(!isRepeated()) return "Task " + title + " at " + time;
            else return "Task " + title + " from " + start + " to " + end + " every " + repeat + " seconds";
        }
    }


    /**
     * A method that returns the time of the next alert after the specified time (not including him)
     * If there are no more notifications after the specified time,
     * or the task is inactive, the result should be -1.
     * @param time - specified time
     * @return the time of the next alert after or -1
     */
    public int nextTimeAfter(int time)
    {
        if (time < 0)
        {
            throw new IllegalArgumentException("Method nextTimeAfter: Invalid time");
        }

        int nt = -1;

        if(isActive())
        {
            if (!isRepeated())
            {
                if (this.time > time) nt = this.time;
            }
            else
            {
                if (start > time) nt = start;

                else if (time < end) {
                    int prev = start;
                    int next = start + repeat;
                    while (next <= end - repeat)
                    {
                        if (time < next)
                        {
                            nt = next;
                            break;
                        }
                        else
                        {
                            prev = next;
                            next += repeat;
                        }
                    }
                }

            }

        }

        return nt;
    }

    @Override
    public Task clone() throws CloneNotSupportedException
    {
        Task task = new Task();

        task.title = this.title;
        task.active = this.active;

        if (this.isRepeated())
        {
            task.start = this.start;
            task.end = this.end;
            task.repeat = this.repeat;
        }
        else
        {
            task.time = this.time;
            task.start = this.time;
            task.end = this.time;
        }

        return task;
    }

    @Override
    public int hashCode()
    {
        int result = title.hashCode();
        int act = active ? 1 : 0;

        result = result * 31 + act;

        if (this.isRepeated())
        {
            result = result * 31 + start;
            result = result * 31 + end;
            result = result * 31 + repeat;
        }
        else
        {
            result = result * 31 + time;
        }

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        Task other = (Task) obj;

        if (title != other.title || active != other.active)
        {
            return false;
        }

        if (this.isRepeated())
        {
            if(start != other.start || end != other.end || repeat != other.repeat)
            {
                return false;
            }
        }
        else
        {
            if(time != other.time)
            {
                return false;
            }
        }

        return true;
    }

}
