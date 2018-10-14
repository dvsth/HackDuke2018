package com.tsquad.hackduke18.fixmymorning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Algorithm {

    private double endtime;
    private String dayofweek;
    private List<DBHandler.Task> finalUnorderedTasks;
    private double totalDuration;

    public Algorithm() {


    }

    public List<DBHandler.Task> executeSelection(List<DBHandler.Task> data, double duration) {

        List<DBHandler.Task> negs = new ArrayList<>();
        List<DBHandler.Task> nonnegs = new ArrayList<>();

        finalUnorderedTasks = new ArrayList<>();

        for (DBHandler.Task t : data)
            if (t.getPriority() == 6)
                nonnegs.add(t);
            else
                negs.add(t);

        //Test
        for (DBHandler.Task t : negs
                ) {
            System.out.println("TESTDEV6: negs " + t.getDescription() + " | " + t.getDuration());
        }
        for (DBHandler.Task t : nonnegs
                ) {
            System.out.println("TESTDEV6: nonnegs " + t.getDescription() + " | " + t.getDuration());
        }
        for (DBHandler.Task t : data
                ) {
            System.out.println("TESTDEV6: main " + t.getDescription() + " | " + t.getDuration());
        }

        double sumNNSLower = 0;
        for (DBHandler.Task t : nonnegs)
            sumNNSLower += t.getMin_time();

        if (sumNNSLower > duration) {
            System.out.println("Error: Umm, there is not enough time to do everything you must. Try going back to sleep, tomorrow's always a new day.");
            System.exit(0);
        }

        finalUnorderedTasks.addAll(nonnegs);

        double sumNSLower = 0;
        for (DBHandler.Task t : negs) {
            sumNSLower += t.getMin_time();
            System.out.println("TESTDEV: iteratedNS" + sumNSLower);

        }
        if (sumNSLower <= (duration - sumNNSLower)) {
            finalUnorderedTasks.addAll(negs);
            return finalUnorderedTasks;
        }

        //start removing low priority items
        while (sumNSLower > (duration - sumNNSLower)) {
            //TEST
            System.out.println("TESTDEV: Sum NNS " + sumNNSLower + "SUM NS " + sumNSLower + " Duration " + duration);

            //goodbye unlucky task of low priority
            try {
                Collections.sort(negs);
                if (negs.isEmpty())
                    System.out.println("TESTDEV: negs is empty");

                else if (!negs.isEmpty())
                    negs.remove(0);

            } catch (Exception e) {
                System.out.println("TESTDEV: " + e);
                System.exit(0);
            }

            //recalculate sum of L's for negs
            sumNSLower = 0;
            for (DBHandler.Task t : negs)
                sumNSLower += t.getMin_time();
        }

        finalUnorderedTasks.addAll(negs);
        double timeLeft = duration - (sumNNSLower + sumNSLower);
        totalDuration = duration;
        List<DBHandler.Task> tFeeder = new ArrayList<>(finalUnorderedTasks);

        //proportionalIncrease(tFeeder, timeLeft);

        if (finalUnorderedTasks.isEmpty())
            System.out.println("TESTDEV5: EMPTY!!!");
        for(DBHandler.Task t : finalUnorderedTasks)
            System.out.println("TESTDEV5: " + t.toString() + t.getDescription() + " | " + t.getDuration());

        return finalUnorderedTasks;

    }


    private double tLeft() {
        double usedUpTime = 0;
        for (DBHandler.Task t : finalUnorderedTasks)
            usedUpTime += t.getDuration();
        return totalDuration - usedUpTime;
    }
/*
    private boolean proportionalIncrease(List<DBHandler.Task> tListRaw, double tLeft) {

        // 1 remove time fixed elements
        // 2 divide remaining time in proportion

        // goto 1
        // if time is less than 3, add it to first element and output

        while(tLeft>3) {

            for (Iterator<DBHandler.Task> iterator = tListRaw.iterator(); iterator.hasNext(); ) {
                DBHandler.Task t = iterator.next();
                if (t.getMax_time() == t.getMin_time()) {
                    iterator.remove();
                }
            }

            double totalDuration = 0;
            for (DBHandler.Task t : tListRaw) {
                totalDuration += t.getDuration();
            }

            for (DBHandler.Task t : tListRaw) {
                double thisDuration = t.getDuration();
                double b = t.getMax_time();
                double fractionalMultiplier = 1 + (thisDuration / totalDuration);
                double tDurationNew = thisDuration * fractionalMultiplier;
                if (tDurationNew > b)
                    tDurationNew = b;

                t.setDuration(tDurationNew);

            }
            tLeft = tLeft();
        }



        return true;
    }*/

}