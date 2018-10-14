public class Task1 {
    private String name;
    private double upper;
    private double lower;
    private int priority;
    private String date;
    private double order;
    private boolean continuous;
    public Task(String Name, double Upper, double Lower, double ORDER, int PRIORITY, String DATE) {
        name = new String(Name);
        upper = Upper;
        lower = Lower;
        order = ORDER;
        priority = PRIORITY;
        date = DATE;
        if (upper == lower)
            continuous = false;
        else
            continuous = true;
    }
    @Override
    public String toString()
    {
        String returning = new String();
        returning += "" + getDensity() + "\t" + name + "\t" + upper + "\t" + lower + "\t" + order + "\t" + priority + "\t" + date;
        return returning;
    }
    
    public String getName() {
        return name;
    }
    public boolean isContinuous()
    {
        return continuous;
    }
    public double getUpper() {
        return upper;
    }
    public double getDensity()
    {
        return priority/upper;
    }
    public double getLower() {
        return lower;
    }

    public double getOrder() {
        return order;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }
}

