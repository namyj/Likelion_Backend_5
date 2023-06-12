package com.example.demo.git;

import java.util.Arrays;

public class JavaGit {
    public static void main(String[] args) {
        if(args.length > 0) {
            String command = args[0];
            if("add".equals(command)) {
                System.out.println("add changes to git:");
                for (String filename: Arrays.copyOfRange(args, 1, args.length)) {
                    System.out.println(filename);
                }
            } else if("commit".equals(command)) {
                if (args.length >= 2 && "-m".equals(args[1])) {
                    System.out.printf("commit with message:\"%s\"\n", args[2]);
                } else {
                    System.out.println("no message specified");
                }
            }
        } else {
            System.out.println("usage: add, commit");
        }
    }
}
