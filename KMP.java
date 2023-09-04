package com.example.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class KMP {

    public static int[] buildPrefixArray(String pattern) {
        int[] prefixArray = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixArray[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefixArray[i] = j;
        }
        return prefixArray;
    }

    public static void search(String filePath, String pattern) {
        int[] prefixArray = buildPrefixArray(pattern);
        int j = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            char[] buffer = new char[pattern.length()];
            int bytesRead;

            while ((bytesRead = reader.read(buffer, 0, pattern.length())) != -1) {
                String chunk = new String(buffer, 0, bytesRead);
                for (int i = 0; i < chunk.length(); i++) {
                    while (j > 0 && chunk.charAt(i) != pattern.charAt(j)) {
                        j = prefixArray[j - 1];
                    }
                    if (chunk.charAt(i) == pattern.charAt(j)) {
                        j++;
                    }
                    if (j == pattern.length()) {
                        if((i-j+1) == 0) {
                            System.out.println(chunk);
                        }
                        j = prefixArray[j - 1];

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        String pattern = "x74u";
        search("/Users/liuxuanyu/Desktop/CIS 35A/3/demo/src/main/java/com/example/demo/service/dic.txt", pattern);
    }
}

