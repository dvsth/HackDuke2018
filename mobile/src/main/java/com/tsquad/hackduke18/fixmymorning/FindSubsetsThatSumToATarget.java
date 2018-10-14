 import java.util.HashSet;
 import java.util.*;
 /**
  * Created by anirudh on 12/5/15.
 */
 public class FindSubsetsThatSumToATarget {

    /**
     * The collection for storing the unique sets that sum to a target.
     */
    private HashSet<List<Task>> allSubsets = new HashSet<>();
    public FindSubsetsThatSumToATarget(List<Task> n, int target, int index)
    {
        allSubsets = new HashSet<List<Task>>();
        FindTargetSumSubsets(n, target, new ArrayList<Task>(), index);
    }

    /**
     * The method for finding the subsets that sum to a target.
     *
     * @param input  The input array to be processed for subset with particular sum
     * @param target The target sum we are looking for
     * @param ramp   The Temporary String to be beefed up during recursive iterations(By default value an empty String)
     * @param index  The index used to traverse the array during recursive calls
     */
    private void FindTargetSumSubsets(List<Task> input, int target, List<Task> ramp, int index) {

        if(index > (input.size() - 1)) {
            if(getSum(ramp) <= target) {
                allSubsets.add(ramp);
            }
            return;
        }
        List<Task> adding = new ArrayList<Task>();
        for(Task t : ramp)
            adding.add(t);
        adding.add(input.get(index));
        //First recursive call going ahead selecting the int at the currenct index value
        FindTargetSumSubsets(input, target, adding, index + 1);
        //Second recursive call going ahead WITHOUT selecting the int at the currenct index value
        FindTargetSumSubsets(input, target, ramp, index + 1);
    }

    /**
     * A helper Method for calculating the sum from a string of integers
     *
     * @param intString the string subset
     * @return the sum of the string subset
     */
    private static int getSum(List<Task> intString) {
        int sum = 0;
        //System.out.println(intString);
        for (int i = 0; i < intString.size(); i++) {
            sum += intString.get(i).getUpper();
        }
        return sum; 
    }
    public HashSet<List<Task>> getSubsets()
    {
        return allSubsets;
    }

    /**
     * Cracking it up here : )
     *
     * @param args command line arguments.
     */
    public static List<Plan> getSums(List<Task> n, int val, double duration) {
        /*
        List<Task>  n =  new ArrayList<Task>();
        n.add(new Task("a",1,2,4,1,"m"));
        n.add(new Task("a",2,2,4,1,"m"));
        n.add(new Task("a",3,2,4,1,"m"));
        n.add(new Task("a",4,2,4,1,"m"));
        n.add(new Task("a",5,2,4,1,"m"));
        n.add(new Task("a",6,2,4,1,"m"));
        */
        FindSubsetsThatSumToATarget returning = new FindSubsetsThatSumToATarget(n, val, 0);
        List<Plan> checking= new ArrayList<Plan>();
        for (List<Task> str: returning.getSubsets()) {
             checking.add(new Plan(str, duration));
        }
        
        return checking;
    }
}