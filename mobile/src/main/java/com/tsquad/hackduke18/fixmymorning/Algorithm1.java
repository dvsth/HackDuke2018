










import java.util.ArrayList;
import java.util.List;
public class Algorithm1 {
    public static int NUM_CHECKS = 10;
    
    
    private double endtime;
    private String dayofweek;

    public Algorithm1()
    {
    }
    /*
    public static void main(String[] args)
    {
        
        //Task[] data = new Task[]{new Task("a", 5, 0, 0,5,"tuesday"), new Task("b", 4, 0, 0,5,"tuesday"), new Task("c", 3, 0, 0,5,"tuesday"), new Task("d", 2, 0, 0,5,"tuesday"), new Task("e", 1, 0, 0,5,"tuesday"), new Task("10", 5, 0, 0,5,"tuesday")};
        //System.out.println(executeSelection(data, 11));
    }
    */

    public static Plan executeSelection(Task[] data, double duration) {
        Task[] tasks = data;
        List<Task> negs = new ArrayList<Task>();
        List<Task> nonnegs = new ArrayList<Task>();
        for (Task t : tasks)
            if (t.getPriority()==6)
                nonnegs.add(t);
            else
                negs.add(t);

        double sumLower = 0;
        for (Task t : nonnegs)
        {
            sumLower += t.getLower();
            /*
            if(t.getUpper() != t.getLower())
                negs.add(new Task(t.getName(), t.getUpper() - t.getLower(), 0, )
            */
        }

        if (sumLower>duration){
            System.out.println("Error: Umm, there is not enough time to do everything you must. Try going back to sleep, tomorrow's always a new day.");
            System.exit(0);
        }
        
        
        sortDensity(negs);
        List<Task> baseCase = new ArrayList<Task>();
        baseCase.addAll(nonnegs);
        /*
        for(int i = 0; i < nonnegs.size(); i++)
            baseCase.add(nonnegs.get(i));
        */  
        List<Task> adding = new ArrayList<Task>();
        adding.addAll(negs);
        double spaceLeft = duration - sumLower;
        
        //add as many as possible to the base case
        int i = 0;
        while(i < adding.size())
        {
            if(spaceLeft - adding.get(i).getUpper() >= 0)
            {
                spaceLeft -= adding.get(i).getUpper();
                baseCase.add(adding.remove(i));
            }
            else
                i++;
        }
                
        //top off the continuous
        for(int j = 0; j < adding.size(); j++)
        {
            if (adding.get(j).isContinuous())
                {
                    
                    baseCase.add(new Task(adding.get(j).getName(), spaceLeft, 0, adding.get(j).getOrder(), adding.get(j).getPriority(), adding.get(j).getDate()));
                    break;
                }                
        }
        Plan BestPlan = new Plan(baseCase, duration);
        //System.out.println("\n\n\n" + BestPlan.toString() + "\n\n\n");

        List<Plan> listPlan = FindSubsetsThatSumToATarget.getSums(negs, NUM_CHECKS, duration);
        for(Plan p : listPlan)
            for(int k = 0; k < negs.size(); k++)
            {
                if (negs.get(k).isContinuous() && !p.contains(negs.get(k)))
                {
                    p.add(new Task(negs.get(k).getName(), p.getSpaceLeft(), 0, negs.get(k).getOrder(), negs.get(k).getPriority(), negs.get(k).getDate()));
                    if(BestPlan.getSatisfaction() < p.getSatisfaction())
                        BestPlan = p;
                    break;
                }
            }



        return BestPlan;
        
    }
    public static void sortDensity(List<Task> group)
    {
        for(int i = 1; i < group.size(); i++)
        {
            int j = i;
            while(j > 0 && group.get(j-1).getDensity() < group.get(i).getDensity())
            {
                Task temp = group.get(j);
                group.set(j , group.get(j - 1));
                group.set(j - 1, temp);
            }
            
        }
        System.out.println(group);
    }
}












