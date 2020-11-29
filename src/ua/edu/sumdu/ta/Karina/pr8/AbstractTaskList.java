package ua.edu.sumdu.ta.Karina.pr8;

public abstract class AbstractTaskList
{
    final int value = 10; // the number of elements by which the array will expand

    static int numberOfLists = 0; // actual number of created task lists
    static String startOfTitle = "[EDUCTR][TA]"; //Title of each task from the list

    protected Task[] mTask; //array for tasks
    protected int mLength; //array length
    protected int count; //the number of tasks in the array

    public abstract void add(Task task);
    public abstract void add(int index, Task task);
    public abstract void remove(Task task);
    public abstract Task getTask(int index);
    public abstract Task[] incoming(int from, int to);

    public int size()
    {
        return count;
    }

    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException
    {
        int k = 0;
        AbstractTaskList list;

        if(this instanceof ArrayTaskList)
        {
            list = new ArrayTaskList();
        }
        else
        {
            list = new LinkedTaskList();
        }

        while (k < this.size())
        {
            list.add(this.getTask(k));
            k++;
        }

        return list;
    }

    @Override
    public String toString()
    {
        int k = 0;
        String string = "[";

        while (k < this.size() - 1)
        {
            string = string + this.getTask(k).toString() + ", ";
            k++;
        }

        string = string + this.getTask(k).toString() + "]";

        return string;
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

        AbstractTaskList other = (AbstractTaskList) obj;

        if (this.size() != other.size())
        {
            return false;
        }

        int k = 0;
        while (k < this.size())
        {
            if(!this.getTask(k).equals(other.getTask(k)))
            {
                return false;
            }
            k++;
        }

        return true;
    }

}
