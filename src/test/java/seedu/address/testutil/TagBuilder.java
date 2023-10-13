package seedu.address.testutil;

import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Tag objects
 */
public class TagBuilder {
    public static final String DEFAULT_TAG = "vendor";

    private Tag tag;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Sets the {@code Tag} of the object that we are building.
     */
    public TagBuilder withTag(String tagToBuild) {
        this.tag = new Tag(tagToBuild);
        return this;
    }

    public Tag build() {
        return new Tag(this.tag.getTagName());
    }
}
