package seedu.address.model.jobapplication;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the various job application deadlines.
 * Such as deadlines for applications, deadlines for online assessments.
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be in the format of DD-MM-YYYY";

    public static final String VALIDATION_REGEX = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-\\d{4}$";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-y");
    public final LocalDate deadline;

    /**
     * Gives a deadline given a string deadline.
     * @param deadline in the appropriate format.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline, FORMATTER);
    }

    /**
     * Gives a deadline given a {@link LocalDate} instance.
     * @param deadline as a {@link LocalDate} instance.
     */
    public Deadline(LocalDate deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    /**
     * Gives a default deadline 14 days from now.
     */
    public Deadline() {
        this.deadline = LocalDate.now().plusDays(14);
    }

    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return deadline.equals(otherDeadline.deadline);
    }

    @Override
    public int compareTo(Deadline d) {
        return deadline.compareTo(d.deadline);
    }

    @Override
    public String toString() {
        return FORMATTER.format(deadline);
    }
}
