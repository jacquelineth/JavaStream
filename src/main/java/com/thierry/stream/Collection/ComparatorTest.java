package    com.thierry.stream.Collection;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


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




@FunctionalInterface
interface MComparator<T> extends Comparator<T>{
    public int compare(T t1, T t2);
    //public static Comparator<Person> comparing(Function<Person, Comparable> f) {
    /**
     * @param <U>
     * @param f
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <U> Comparator<U> comparing(Function<U, Comparable<U>> f) {
       // return (p1, p2) -> f.apply(p1) - f.apply(p2); when defined as int

       return (p1, p2) -> f.apply(p1).compareTo( (U)(f.apply((U)p2)));
    }

    public default MComparator<T> thenComparing(MComparator<T> cmp) {
        return (p1, p2) -> compare(p1, p2) == 0 ? cmp.compare(p1, p2) : compare(p1, p2);
    }
}

@FunctionalInterface
interface MPredicate<T> extends Predicate<T> {
    public boolean test(T t);
    public default MPredicate<T> and(MPredicate<T> p) {
        return (t) -> test(t) && p.test(t);
    }
    public default MPredicate<T> or(MPredicate<T> p) {
        return (t) -> test(t) || p.test(t);
    }
    public static <U> MPredicate<U> isEqual(U u) {
        return (t) -> t.equals(u);
    }
    /* 
    public static MPredicate<String> isEqualTo(String string) {
        return s ->s.equals(string);
    } */ 
    // or
    public static <U> MPredicate<U> isEqualTo(U u) {
        return s ->s.equals(u);
    }

}


public class ComparatorTest implements java.util.Comparator<String> {

    public int compare(String s1, String s2) {
     //   return s1.compareTo(s2);
     return Integer.compare(s1.length(), s2.length());
    }

    public static void main(String[] args) {
        Runnable r= () -> System.out.println("Hello World");
        r.run();

        java.util.Comparator<String> comparator = (String s1, String s2) -> {
            return Integer.compare(s1.length(), s2.length());
        };
        System.out.println(comparator.compare("Hello", "World"));

        Comparator<String> comparator2 = (String s1, String s2) -> {
            System.out.println("I am comparing " + s1 + " to " + s2);
            return Integer.compare(s1.length(), s2.length());
        };

        System.out.println(comparator2.compare("Hello", "World"));
        Runnable r2 = new Runnable() {
            public void run() {
                int i=0;
                while(i++<10)
                    System.out.println("Hello World 2");
            }
        };
        r2.run();
/*         Runnable r2b = () -> {
            int i=0;
            while(i++<10)
                System.out.println("Hello World 2b");
        };

        r2b.run(); */

        Function<Person, Integer> f = p -> p.getAge();
        Function<Person, Integer> f2 = Person::getAge;
        System.out.println(f2.apply(new Person("Thierry", "Bouche", 50)));

        
        Person person = (new Person("Thierry", "Bouche", 50));
        System.out.println(f.apply(person));
        
        
        BinaryOperator<Integer> sum = (i1, i2) -> i1 + i2;
        // BinaryOperator<Integer> sum2 = (i1, i2) -> Integer.sum(i1, i2);
        // BinaryOperator<Integer> sum3 = Integer::sum;

        System.out.println(sum.apply(1, 2));


       /*  Comparator<Person> cmpAge = (p1, p2) -> p1.getAge() - p2.getAge();
        Comparator<Person> cmpFirstName = (p1, p2) -> p1.firstName.compareTo( p2.firstName);
        Comparator<Person> cmpLastName = (p1, p2) -> p1.lastName.compareTo( p2.lastName);
 */
        Function<Person, String> f3 = p3 -> p3.firstName;
        Function<Person, String> f4 = p4 -> p4.lastName;

        java.util.Comparator<Person> cmpPerson_ = Comparator.comparing(f)
            .thenComparing(f3)
            .thenComparing(f4);
        System.out.println(cmpPerson_.compare(new Person("Thierry", "Bouche", 50), new Person("Thierry", "Bouche", 50)));

/*         java.util.Comparator<Person> cmpPerson = Comparator.comparing(Person::getAge)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getLastName);            
         */

    /*   Chapter 2 */
        // with JDK7
        Predicate<String> p= new Predicate<String>() {
            public boolean test(String s) {
                return s.isEmpty();
            }
        
        };
        System.out.println(p.test("Hello"));
        // with JDK8 Lambda
/*         Predicate<String> p2 = (String s) -> s.isEmpty();
        Predicate<String> p3 = (s) -> s.isEmpty();
 */
        Predicate<Person> isAnAdult = p5 -> p5.isAdult();
        System.out.println(isAnAdult.test(person));

        //Consumer
        Consumer<String> logger = s -> System.err.println(s);
        logger.accept("Hello logger");
    
        //Supplier
        //Supplier<Person> personSupplier = () -> new Person();
        Supplier<Person> personSupplier = Person::new;
        Person personSup = personSupplier.get();
        System.out.println(personSup);
        //Function
        //Function<String, Integer> f5 = s -> s.length();
        //Function<Person, Integer> ageMapper = person -> person.getAge();
        Function<Person, Integer> ageMapper = Person::getAge;
        System.out.println(ageMapper.apply(person));

        //BinaryOperator
        BinaryOperator<Integer> sum4 = (i1, i2) -> i1 + i2;
        System.out.println(sum4.apply(1, 2));
        //UnaryOperator
        //UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println(toUpperCase.apply("hello"));

        //Method Reference
        //Function<Person, Integer> ageMapper2 = person -> person.getAge();
        Function<Person, Integer> ageMapper2 = Person::getAge;
        System.out.println(ageMapper2.apply(person));

        //Predicate
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println(isEmpty.test("Hello"));
        Predicate<Person> isAgeGT20 = person20 -> person20.getAge() > 20;
        System.out.println(isAgeGT20.test(person));

        IntPredicate isAgeGT21 = age -> age > 21;
        System.out.println(isAgeGT21.test(22));

        //BiPredicate
        final BiPredicate<String, Integer> isLongerThan = (s, i) -> s.length() > i;   
        System.out.println(isLongerThan.test("Hello", 3));
        
        //My own Predicate
        MPredicate<String> mp1 = s -> s.length() < 20;
        MPredicate<String> mp2 = s -> s.length() > 5;
        MPredicate<String> mp3 = mp1.and(mp2);
        System.out.println(mp3.test("Hello"));
        Predicate<String> p5= MPredicate.isEqualTo("Yes");
        System.out.println("P5 for YES"+p5.test("Yes"));
        System.out.println("P5 for NO"+p5.test("No"));

        Predicate<Integer> p6= MPredicate.isEqualTo(1);
        System.out.println("P6 for 1"+p6.test(1));
    }


}
