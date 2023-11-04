package com.cli.brecktraffic;

public class Timer {
    private static int time = 0;
    public static void increment() {
        time++;
    }

    public static int getTime() {
        return time;
    }
}
