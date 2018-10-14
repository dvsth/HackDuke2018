package com.tsquad.hackduke18.fixmymorning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
public class Algorithm {

    private double endtime;
    private String dayofweek;
    private List<Task> finalUnorderedTasks;
    private double totalDuration;

    public Algorithm() {


    }

    public List<Task> executeSelection(Task[] data, double duration) {
        Task[] tasks = data;

        List<Task> negs = new ArrayList<>();
        List<Task> nonnegs = new ArrayList<>();

        finalUnorderedTasks = new ArrayList<>();

        for (Task t : tasks)
            if (t.getPriority() == 6)
                nonnegs.add(t);
            else
                negs.add(t);

        double sumNNSLower = 0;
        for (Task t : nonnegs)
            sumNNSLower += t.getLower();

        if (sumNNSLower > duration) {
            System.out.println("Error: Umm, there is not enough time to do everything you must. Try going back to sleep, tomorrow's always a new day.");
            System.exit(0);
        }

        finalUnorderedTasks.addAll(nonnegs);

        double sumNSLower = 0;
        for (Task t : negs)
            sumNSLower += t.getLower();
        if (sumNSLower <= (duration - sumNNSLower)) {
            finalUnorderedTasks.addAll(negs);
            return finalUnorderedTasks;
        }

        //start removing low priority items
        while (sumNSLower > (duration - sumNNSLower)) {

            //goodbye unlucky task of low priority
            try {
                Collections.sort(negs);
                negs.remove(0);
            } catch (Exception e) {
                System.out.println(e);
            }

            //recalculate sum of L's for negs
            for (Task t : negs)
                sumNSLower += t.getLower();
        }

        finalUnorderedTasks.addAll(negs);
        double timeLeft = duration - (sumNNSLower + sumNSLower);
        totalDuration = duration;
        List<Task> tFeeder = new ArrayList<>(finalUnorderedTasks);

        proportionalIncrease(tFeeder, timeLeft);

        return finalUnorderedTasks;

    }


    private double tLeft() {
        double usedUpTime = 0;
        for (Task t : finalUnorderedTasks)
            usedUpTime+=t.getDuration();
        return totalDuration - usedUpTime;
    }

    private boolean proportionalIncrease(List<Task> tListRaw, double tLeft) {

        // 1 remove time fixed elements
        // 2 divide remaining time in proportion

        // goto 1
        // if time is less than 3, add it to first element and output
        if (tLeft <= 3)
            return true;

        for (Iterator<Task> iterator = tListRaw.iterator(); iterator.hasNext(); ) {
            Task t = iterator.next();
            if (t.getUpper() == t.getLower()) {
                iterator.remove();
            }
        }

        double totalDuration = 0;
        for (Task t : tListRaw) {
            totalDuration += t.getDuration();
        }

        for (Task t : tListRaw) {
            double thisDuration = t.getDuration();
            double b = t.getUpper();
            double fractionalMultiplier = 1 + (thisDuration / totalDuration);
            double tDurationNew = thisDuration * fractionalMultiplier;
            if (tDurationNew > b)
                tDurationNew = b;

            t.setDuration(tDurationNew);

        }

            proportionalIncrease(tListRaw, tLeft());

            return true;
    }

}
*/