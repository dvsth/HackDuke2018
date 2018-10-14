
import java.util.*;
public class Plan
{
    private List<Task> order;
    private double satisfaction;
    private double spaceLeft;
    
    public Plan(List<Task> list, double t)
    {
        order = new ArrayList<Task>(list);
        satisfaction = 0;
        spaceLeft = t;
        for(int i = 0; i < order.size(); i++)
        {
            satisfaction += order.get(i).getPriority();
            spaceLeft -= order.get(i).getUpper();
        }
    }
    public void add(Task t)
    {
        order.add(t);
        satisfaction += t.getPriority();
        spaceLeft -= t.getUpper();
    }
    public boolean contains(Task t)
    {
        String category = t.getName();
        for(Task tsk : order)
            if(tsk.getName().equals(category))
                return true;
        return false;
    }
    public double getSatisfaction()
    {
        return satisfaction;
    }
    public double getSpaceLeft()
    {
        return spaceLeft;
    }
    public List<Task> getOrder()
    {
        return order;
    }
    @Override
    public String toString()
    {
        String returning = new String();
        for(Task t : order)
            returning += t.toString() + "\n";
        returning += "\n" + satisfaction + "\t" + spaceLeft;
        return returning;
    }

}
    









