import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {

        Employee employee1 = new Employee("John", "Rambo", 30, List.of("Java", "SQL"));
        Employee employee2 = new Employee("Jack", "Black", 25, List.of("Python", "C++"));
        Employee employee3 = new Employee("Morgan", "Freeman", 50, List.of("Spring Boot", "Java"));
        Employee employee4 = new Employee("Barack", "Obama", 90, List.of("C#", "TDD"));

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);

        // zwróci optionala ale jak lista będzie pusta to suma będzie null
        Integer sumOfAllAges = employeeList.stream()
                .map(employee -> employee.getAge())
                .reduce(Integer::sum)
                //.reduce((age1, age2) -> age1 + age2)
                .get();

        // zwróci sume, jak lista będzie pusta zwróci 0 a nie null
        Integer sumOfAllAges2 = employeeList.stream()
                .map(employee -> employee.getAge())
                .reduce(0, Integer::sum);

        //suma bez mapowania, odmyślna wartość 0
        Integer sumOfAllAges3 = employeeList.stream()
                .reduce(0, (age, employee) -> age + employee.getAge(), Integer::sum);

        System.out.println(sumOfAllAges3);

        System.out.println(sumOfAllAges);

        boolean isEveryNameStartsWithJ = employeeList.stream()
                // wszystkie obiekty w stream muszą spełniać warunek
                // jak wszystkie imiona będą na J zwróci TRUE
                .allMatch(employee -> employee.getName().startsWith("J"));

        boolean isAnyNameStartsWithJ = employeeList.stream()
                //przyjanmniej jeden element w streamie musi spełniać warunek
                //jak którekolwiek imie będzie się zaczynało od J zwróci TRUE
                .anyMatch(employee -> employee.getName().startsWith("J"));

        boolean isNoneNameStartsWithZ = employeeList.stream()
                //zwróci true jeżeli żadne imię nie zaczyna się od Z
                //w liście nie ma imion na "Z" więc zwróci TRUE
                .noneMatch(employee -> employee.getName().startsWith("Z"));

        System.out.println(isEveryNameStartsWithJ);
        System.out.println(isAnyNameStartsWithJ);
        System.out.println(isNoneNameStartsWithZ);

        Employee youngestEmployee = employeeList.stream()
                .min(Comparator.comparing(employee -> employee.getAge())).get();

        Employee oldestEmployee = employeeList.stream()
                .max(Comparator.comparing(employee -> employee.getAge())).get();

        System.out.println(youngestEmployee);
        System.out.println(oldestEmployee);

        List<Employee> sortedByAge = new ArrayList<>();
        sortedByAge = employeeList.stream()
                .sorted(Comparator.comparing(employee -> employee.getAge()))
                .collect(Collectors.toList());

        System.out.println(sortedByAge);

        List<List<String>> skillsList = employeeList.stream()
                .map(employee -> employee.getSkills())
                .collect(Collectors.toList());

        List<String> allSkillsFlatMap = employeeList.stream()
                .map(employee -> employee.getSkills())
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(skillsList);

        System.out.println(allSkillsFlatMap);
    }
}
