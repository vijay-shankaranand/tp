package seedu.address.testutil;

import seedu.address.model.JobFestGo;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building JobFestGo objects.
 * Example usage: <br>
 *     {@code JobFestGo ab = new JobFestGoBuilder().withContact("John", "Doe").build();}
 */
public class JobFestGoBuilder {
    private JobFestGo jobFestGo;

    public JobFestGoBuilder() {
        jobFestGo = new JobFestGo();
    }

    public JobFestGoBuilder(JobFestGo jobFestGo) {
        this.jobFestGo = jobFestGo;
    }

    /**
     * Adds a new {@code Contact} to the {@code JobFestGo} that we are building.
     */
    public JobFestGoBuilder withContact(Contact contact) {
        jobFestGo.addContact(contact);
        return this;
    }

    public JobFestGo build() {
        return jobFestGo;
    }
}
