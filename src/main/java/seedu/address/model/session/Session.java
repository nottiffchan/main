package seedu.address.model.session;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a session in TAT.
 * A session is any claimable duty that has a start and end time.
 * Guarantees: Date, Start Time and End Time are not null.
 */
public class Session {

    /**
     * Represents a session type. Session types follows the same specifications as the TSS.
     * Example session types include: Tutorial, Grading, Consultation, etc.
     */
    public enum SessionType {
        SESSION_TYPE_TUTORIAL,
        SESSION_TYPE_LAB,
        SESSION_TYPE_CONSULTATION,
        SESSION_TYPE_GRADING,
        SESSION_TYPE_PREPARATION,
        SESSION_TYPE_OTHER
    }

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private SessionType type;
    private String description;

    /**
     * Constructs a Session object.
     */
    public Session(LocalTime startTime, LocalTime endTime, LocalDate date, SessionType type, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    /**
     * Returns the date when the session will take place.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the start time of the session.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the session.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the type of session.
     */
    public SessionType getSessionType() {
        return this.type;
    }

    /**
     * Returns the description of the session.
     */
    public String getDescription() {
        return this.description;
    }
}
