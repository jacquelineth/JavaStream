 package com.thierry.stream.dataProcessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;



class Person {
    String firstName;
    String lastName;
    int age;

    Person() {
    }

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age=age;
    }
    public int getAge() {
        return age;
    }
    public boolean isAdult() {
        return age >= 18;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String toString() {
        return firstName + " " + lastName + " " + age;
    }
}



/**
 * Created by Thierry on 6/24/2017.
 * PluralSight course : From Collections to Streams in Java 8 Using Lambda Expressions and Streams to Increase Productivity and Performance
  Module 3: Processing Data with Java 8 Streams
*/

 public class collectionFramework {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two", "three", "four", "five");
        list.forEach(System.out::println);
        list.forEach(s -> System.out.println(s));
        list.forEach(s -> {
            System.out.println(s);
        });
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        list.forEach((String s) -> System.out.println(s));
        list.forEach((String s) -> {
            System.out.println(s);
        });
        list.forEach(s -> System.out.println(s));
        list.forEach(s -> {
            System.out.println(s);
        });
        list.forEach(System.out::println);

        System.out.println("====================================");
        //Iterable.forEach(Consumer<? super T> action)
        List<Person> people = new ArrayList<>();
        people.add(new Person("Pablo", "Amoral", 20));
        people.add(new Person("Tomas", "DUBROCA", 50));
        people.add(new Person("Thierry", "DUBROCA", 50));
        people.add(new Person("Javier", "Garcia", 27));
        people.add(new Person("Pedro", "Alonso", 17));

        List<String> names = new ArrayList<>();
        names.addAll(people.stream().map(Person::getFirstName).collect(java.util.stream.Collectors.toList()));
        //
        //names.replaceAll(name -> name.toUpperCase());
        names.replaceAll(String::toUpperCase);
        names.forEach(System.out::println);

        people.removeIf(person -> person.getAge() < 18);// set a predicate to automatically remove the person if the predicate is true
        people.forEach(System.out::println);

        System.out.println("===========Sort People=========================");
        
        people.sort(Comparator.comparing(Person::getFirstName)
                .thenComparing(Person::getAge));
        people.forEach(System.out::println);

        System.out.println("==========Filter on name :T==========================");

        names.stream().filter(name -> name.startsWith("T")).forEach(System.out::println);

        System.out.println("============Sort Name on people's Age========================");
        /*  Open issue ; What to do when the name is not found 
        How to return a default value when the filter returns an empty list
        Function<String, Integer> nameToAge = name -> people.stream().filter(person -> person.getFirstName().equals(name)).;
        System.out.println("Thierry "+nameToAge.apply("Toto"));

        */
        //names.sort(Comparator.comparing(nameToAge));
        //Map
        
    }
}