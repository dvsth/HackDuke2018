package com.tsquad.hackduke18.fixmymorning;

public class Day {
    private int id;
    private String day;
    private double start;
    private double end;


    public Day(int id, String Day, double  Start, double End) {
        id= id;
        day = Day;
        start = Start;
        end = End;

    }

    public int getId() {return id;}

    public String getDay() {
        return day;
    }

    public double getStart() {
        return start;
    }

    public double getend() {
        return end;
    }




}