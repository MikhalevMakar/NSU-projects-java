package org.ru.nsu.mikhalev.task2.InputProcessing;


import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Map;
import  org.ru.nsu.mikhalev.task2.CheckerDouble.CheckerDouble;
public class TerminalCalculator {

    private Set<Double> set = new LinkedHashSet<>();
    public TerminalCalculator() throws Exception {


        System.out.println("Input param stack calculator:");
        String line = "";

        Scanner scanner = new Scanner(System.in);
        Map<StringBuilder, String> hashMap = new HashMap<>();

        while(scanner.hasNext()) {
            line = scanner.nextLine();
            if(line.toLowerCase().equals("exit")) {
                break;
            }

            System.out.println(Double.parseDouble(line));
            CheckerDouble.IsNumberFormat(line);
            set.add(Double.parseDouble(line));
        }

        System.out.println(set);
        System.out.println("Finish");
    }
}
