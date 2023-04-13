package at.kaindorf.lambdas.bl;

import at.kaindorf.lambdas.data.Event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Programming-Exercise - Lambdas<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 12. April 2023<br>
 * <b>Time:</b> 21:27<br>
 */

public class EventBL {

    private static final List<String> AVAILABLE_SUBJECTS = List.of("POS", "DBI", "NVS", "TINF");
    private static final List<String> AVAILABLE_ACTIVITIES = List.of("PLF", "Übung", "Aufgabe");
    private static final LocalDate START_DATE = LocalDate.of(2019, 9, 9);
    private static final LocalDate END_DATE = LocalDate.of(2019, 12, 20);
    private List<Event> events = new ArrayList<>();

    public void initEvents () {
        Random random = new Random();

        events =
                IntStream
                        .range(0, 25)
                        .mapToObj(i ->
                                new Event(AVAILABLE_SUBJECTS.get(random.nextInt(AVAILABLE_SUBJECTS.size()))
                                        + "-" + AVAILABLE_ACTIVITIES.get(random.nextInt(AVAILABLE_ACTIVITIES.size()))
                                        ,getRandomDate()))
                        .collect(Collectors.toList());
    }

    public void sortEvents (boolean upwards) {
        if (upwards) {
            events.sort(Comparator.comparing(Event::getDate));
        } else {
            events.sort(Comparator.comparing(Event::getDate).reversed());
        }
    }

    public void filterEvents () {
        events.removeIf(event -> event.getText().contains("PLF"));
    }

    public void printEvents () {
        System.out.println("-------------------------");
        if (Math.random() > 0.5) {
            events.forEach(System.out::println);
        } else {
            events.forEach(event -> System.out.println(event));
        }
        System.out.println("-------------------------");
    }

    private LocalDate getRandomDate () {
        Random random = new Random();
        long daysBetween = ChronoUnit.DAYS.between(START_DATE, END_DATE);
        LocalDate randomDate = null;

        while (randomDate == null || randomDate.getDayOfWeek() == DayOfWeek.SATURDAY || randomDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            randomDate = START_DATE.plusDays(random.nextInt((int) daysBetween + 1));
        }

        return randomDate;
    }

    public static void main(String[] args) {
        EventBL eventBL = new EventBL();
        eventBL.initEvents();
        eventBL.printEvents();
        eventBL.sortEvents(false);
        eventBL.printEvents();
        eventBL.sortEvents(true);
        eventBL.printEvents();
        eventBL.filterEvents();
        eventBL.printEvents();
    }
}
