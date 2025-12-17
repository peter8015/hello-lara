package com.lara.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghaibing
 * @date 2025-07-03
 */
public class Solution {

    // problem: Give an array of char, please return the char with max frequency
    //  char[] input = {'a', 'b', 'c', 'a', 'b', 'a', 'd'};

    // 1. static functions
    // 2. interface vs abstract method
    // 3. int vs Integer
    public static void main(String[] args) {
        char[] input = {'a', 'b', 'c', 'a', 'b', 'a', 'd'};

//        System.out.println(new Solution().getCharWithMaxFrequency(input));
        System.out.println(new Solution().getMaxFrequencyChar(input));
    }



    public char getCharWithMaxFrequency(char[] input) {
        Map<Character, Integer> maps = new HashMap<>();

        for(char c : input) {
            maps.put(c, maps.getOrDefault(c, 0) + 1);
        }

        char maxC = 0;
        int maxF = 0;

        for(Map.Entry<Character, Integer> entry : maps.entrySet()) {
            if(entry.getValue() > maxF) {
                maxC = entry.getKey();
                maxF = entry.getValue();
            }
        }

        return maxC;
    }


    public char getMaxFrequencyChar(char[] input) {
        char c = 0;
        int max = 0;

        Map<Character, Integer> store = new HashMap<>();

        for(char c1 : input) {
            store.put(c1, store.getOrDefault(c1, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : store.entrySet()) {


            if(entry.getValue() > max) {
                c = entry.getKey();
                max = entry.getValue();
            }

        }
        return c;
    }
}
