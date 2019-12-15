package com.januszyk.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class FileOverrider {
    static void handleInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toLowerCase();
        if (!input.equals("yes")) {
            if (input.equals("no")) {
                System.out.println("Exiting LeetCodePreparer. Process canceled");
                System.exit(0);
            } else {
                throw new IOException("Exiting LeetCodePreparer. Invalid input");
            }
        }
    }
}
