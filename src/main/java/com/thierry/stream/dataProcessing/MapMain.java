package com.thierry.stream.dataProcessing;

import java.util.*;
import java.util.stream.Collectors;

public class MapMain {
    

    public static void main(String[] args ) {
        List<Person> people = Arrays.asList(
            new Person("Ted", "Neward", 42),
            new Person("Charlotte", "Neward", 39),
            new Person("Michael", "Neward", 19),
            new Person("Matthew", "Neward", 13),
            new Person("Neal", "Ford", 45),
            new Person("Candy", "Ford", 39),
            new Person("Jeff", "Brown", 43),
            new Person("Betsy", "Brown", 39)
        );

        // Map
        List<String> names = people.stream()
            .map(Person::getFirstName)
            .collect(Collectors.toList());
        System.out.println(names);

        // On Map
        // void forEach(BiConsumer<? super k, ? super V> consumer);
        Map<City, List<Person>> map = new HashMap<City,List<Person>>();
        map.put(new City("Salt Lake City"), Arrays.asList(
            new Person("Ted", "Neward", 42),
            new Person("Charlotte", "Neward", 39),
            new Person("Michael", "Neward", 19),
            new Person("Matthew", "Neward", 13)
        ));
        map.put(new City("Atlanta"), Arrays.asList(
            new Person("Neal", "Ford", 45),
            new Person("Candy", "Ford", 39)
        ));
        map.put(new City("San Francisco"), Arrays.asList(
            new Person("Jeff", "Brown", 43),
            new Person("Betsy", "Brown", 39)
        ));

        // void forEach(BiConsumer<? super k, ? super V> consumer);
        map.forEach((city, peopleInCity) -> {
            System.out.println(city + " : " + peopleInCity.size());
        });
    }
}
