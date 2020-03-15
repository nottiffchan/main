package seedu.address.model.group;

import java.util.ArrayList;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Matric;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a group in TAT.
 * A group is anything that would include a
 * group of students such as a lab or tutorial.
 */
public class Group {

    /**
     * Represents a group type.
     * Can be a lab or a tutorial.
     */
    public enum GroupType {
        LAB,
        TUTORIAL;
    }

    private final String identifier;
    private final GroupType groupType;
    private final UniquePersonList students;


    /**
     * Constructs a group object.
     *
     * @param identifier identifies the group. For example,
     *                   the tutorial code for a tutorial, etc.
     */
    public Group(String identifier, GroupType groupType) {
        this.identifier = identifier;
        students = new UniquePersonList();
        this.groupType = groupType;
    }

    /**
     * Adds student to students.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Gets student with given matric number.
     */
    public Student getStudent(Matric matric) {
        Student student = null;
        for (int i = 0; i < students.size(); ++i) {
            student = students.get(i);
            if (student.getMatric().equals(matric)) {
                break;
            }
        }
        return student;
    }

    /**
     * Returns the student list.
     */
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    /**
     * Returns the group identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns true if both groups have the same identifiers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getIdentifier().equals(this.getIdentifier());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(identifier);
    }

    //TODO: edit once Student is made
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getIdentifier());
        return builder.toString();
    }
}
