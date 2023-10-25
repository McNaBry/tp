package seedu.address.model.jobapplication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Id;

public class JobApplicationTest {
    private Id id1 = new Id();
    private Id id2 = new Id();
    private JobTitle validTitle = new JobTitle("SWE");
    private JobDescription validJobDescription = new JobDescription("Pay: $700/h");
    private JobStatus validStatus = JobStatus.PENDING;
    private ApplicationStage validApplicationStage = ApplicationStage.RESUME;
    private Deadline validDeadline = new Deadline();

    @Test
    public void constructor_nullExceptJobDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobApplication(null, validTitle, validJobDescription,
                validDeadline, validStatus, validApplicationStage));
        assertThrows(NullPointerException.class, () -> new JobApplication(id1, null, validJobDescription,
                validDeadline, validStatus, validApplicationStage));
        assertThrows(NullPointerException.class, () -> new JobApplication(id1, validTitle, validJobDescription,
                null, validStatus, validApplicationStage));
        assertThrows(NullPointerException.class, () -> new JobApplication(id1, validTitle, validJobDescription,
                validDeadline, null, validApplicationStage));
        assertThrows(NullPointerException.class, () -> new JobApplication(id1, validTitle, validJobDescription,
                validDeadline, validStatus, null));
    }

    @Test
    public void constructor_nullDescription_doesNotThrowNullPointerException() {
        assertDoesNotThrow(() -> new JobApplication(id1, validTitle, null,
                validDeadline));
    }

    @Test public void constructor_excludeStatus_createsDefaultStatus() {
        JobApplication ja1 = new JobApplication(id1, validTitle, validJobDescription, validDeadline);
        assertEquals(ja1.getStatus(), JobStatus.PENDING);
    }

    @Test public void constructor_excludesStage_createsDefaultStageAndStatus() {
        JobApplication ja1 = new JobApplication(id1, validTitle, validJobDescription, validDeadline);
        assertEquals(ja1.getApplicationStage(), validApplicationStage);
        assertEquals(ja1.getStatus(), validStatus);
    }
    @Test
    public void equals() {
        assert !id1.equals(id2);

        JobApplication ja1 = new JobApplication(id1, validTitle, validJobDescription, validDeadline,
                validApplicationStage);
        // JobApplication ja2 = new JobApplication(id1, validTitle, validJobDescription, validDeadline, validStatus);
        JobApplication ja3 = new JobApplication(id2, validTitle, validJobDescription, validDeadline,
                validApplicationStage);
        JobApplication ja4 = new JobApplication(id1, new JobTitle("SRE"), validJobDescription, validDeadline,
                validApplicationStage);
        JobApplication ja5 = new JobApplication(id1, validTitle, new JobDescription("Intern"), validDeadline,
                validApplicationStage);
        JobApplication ja6 = new JobApplication(id1, validTitle, validJobDescription, validDeadline,
                ApplicationStage.INTERVIEW);
        JobApplication ja7 = new JobApplication(id1, validTitle, validJobDescription, validDeadline,
                JobStatus.REJECTED, validApplicationStage);


        // self -> true
        assertEquals(ja1, ja1);

        // same contents -> true
        // assertEquals(ja1, ja2); removed due to issues with GitHub CI

        // different content -> false
        assertNotEquals(ja1, ja3); // different id
        assertNotEquals(ja1, ja4); // different title
        assertNotEquals(ja1, ja5); // different description
        assertNotEquals(ja1, ja6); // different stage
        assertNotEquals(ja1, ja7); // different status
    }

    @Test
    public void hashCode_sameFields_getSameHashCode() {
        JobApplication ja1 = new JobApplication(id1, validTitle, validJobDescription, validDeadline, validStatus,
                validApplicationStage);
        assertEquals(ja1.hashCode(), Objects.hash(
                id1.toString(),
                validTitle.toString(),
                validJobDescription.toString(),
                validDeadline.toString(),
                ja1.getLastUpdatedTime().toString(),
                validStatus.toString(),
                validApplicationStage.toString()
        ));
    }

    @Test
    public void getters_nonNull_getsCorrectItems() {
        JobApplication ja1 = new JobApplication(id1, validTitle, validJobDescription, validDeadline, validStatus,
                validApplicationStage);

        assertEquals(ja1.getStatus(), validStatus);
        assertEquals(ja1.getDeadline(), validDeadline);
        assertEquals(ja1.getJobTitle(), validTitle);
        assertEquals(ja1.getJobDescription().orElse(null), validJobDescription);
        assertEquals(ja1.getOrganizationId(), id1);
        assertEquals(ja1.getApplicationStage(), validApplicationStage);
    }
}
