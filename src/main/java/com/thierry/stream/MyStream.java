package com.thierry.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStream {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("i");
        list.add("j");
        list.add("k");
        list.add("l");
        list.add("m");
        list.add("n");
        list.add("o");
        list.add("p");
        list.add("q");
        list.add("r");
        list.add("s");
        list.add("t");
        list.add("u");
        list.add("v");
        list.add("w");
        list.add("x");
        list.add("y");
        list.add("z");
        
        List<String> result = list.stream().filter(s -> s.compareTo("m") > 0).collect(Collectors.toList());
        System.out.println(result);

        List<String> result2 = list.stream().filter(s -> s.compareTo("") > 0).limit(3).collect(Collectors.toList());
        System.out.println(result2);

        List<String> result3 = list.stream().filter(s -> s.compareTo("m") > 0).skip(3).collect(Collectors.toList());
        System.out.println(result3);

        List<String> result4 = list.stream().filter(s -> s.compareTo("m") > 0).limit(3).collect(Collectors.toList());
        System.out.println(result4);

        List<String> result5 = list.stream().filter(s -> s.compareTo("m") > 0).sorted().collect(Collectors.toList());
        System.out.println(result5);

        List<String> result6 = list.stream().filter(s -> s.compareTo("m") > 0).map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(result6);

        Stream<String> sortedDistinct = getData().stream().distinct().sorted();
        System.out.println(sortedDistinct.collect(Collectors.toList()));
        Stream<String> filtered = getData().stream().filter(s -> Integer.parseInt(s) % 2 == 0);
        System.out.println(filtered.collect(Collectors.toList()));

    }
    
            private static List<String> getData() {
                return Arrays.asList(
                   "2", "2", "2", "4", "4", 
                   "6", "7", "7", "9", "10");
             }
}