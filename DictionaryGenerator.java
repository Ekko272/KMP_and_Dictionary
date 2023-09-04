package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryGenerator {

    public static List<String> generatePermutations(String input) throws IOException {
        List<String> permutations = new ArrayList<>();
        String filePath = "/Users/liuxuanyu/Desktop/CIS 35A/3/demo/src/main/java/com/example/demo/service/dic.txt";
        generatePermutationsHelper("", input, permutations);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for(int i = 0; i < permutations.size(); i++){
            bufferedWriter.write(permutations.get(i));
        }
        return permutations;
    }

    private static void generatePermutationsHelper(String prefix, String remaining, List<String> permutations) {
        int n = remaining.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutationsHelper(prefix + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1), permutations);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String userInfo = "1774xuan";
        List<String> permutations = generatePermutations(userInfo);

        System.out.println("Generated dictionary:");
        System.out.println(permutations.size());
//        for (String permutation : permutations) {
//            System.out.println(permutation);
//        }
    }
}
