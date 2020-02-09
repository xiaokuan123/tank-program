package com.example;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Dir[] dirs = Dir.values();//[random.nextInt(4)] ;//
        System.out.println(Arrays.toString(dirs));
        for(int i=0;i<200;i++){
            int num = new Random().nextInt(4);
            System.out.println(num);
        }

    }
}
