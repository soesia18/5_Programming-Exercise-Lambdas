package at.kaindorf.lambdas.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Programming-Exercise - Lambdas<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 12. April 2023<br>
 * <b>Time:</b> 21:25<br>
 */

@Data
@AllArgsConstructor
public class Event {
    private static final DateTimeFormatter dTF = DateTimeFormatter.ofPattern("E dd.MM.yyyy");

    private String text;
    private LocalDate date;

    @Override
    public String toString() {
        return text + " - " + date.format(dTF);
    }
}
