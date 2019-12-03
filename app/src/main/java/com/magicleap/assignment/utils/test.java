package com.magicleap.assignment.utils;

public class test {

    public static void printIntInBinary(int value) {

        String output = "";

        while (value >= 2) {
            output =  value % 2 + output;
            value = value / 2;
        }
        output = value + output;
        System.out.println(output);

    }
}
