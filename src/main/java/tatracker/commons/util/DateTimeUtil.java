package tatracker.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates and times.
 */
public class DateTimeUtil {

    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm";

    public static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern(PATTERN_DATE);
    public static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern(PATTERN_TIME);

    public static final String CONSTRAINTS_DATE = String.format("Dates should be in %s format", PATTERN_DATE);
    public static final String CONSTRAINTS_TIME = String.format("Times should be in %s format", PATTERN_TIME);

    /**
     * Returns true if {@code String date} represents a {@code LocalDate}
     * in yyyy-MM-dd format.
     */
    public static boolean isDate(String date) {
        requireNonNull(date);
        try {
            LocalDate.parse(date, FORMAT_DATE);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    /**
     * Returns true if {@code String date} represents a {@code LocalDate}
     * in HH:mm format.
     */
    public static boolean isTime(String time) {
        requireNonNull(time);
        try {
            LocalTime.parse(time, FORMAT_TIME);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }
}
