package com.grademanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void calculatesTotalAndAverage() {
        Student student = new Student("S101", "Rahul");
        student.addOrUpdateMark("Maths", 80);
        student.addOrUpdateMark("Science", 90);
        student.addOrUpdateMark("English", 70);

        assertEquals(240, student.getTotal());
        assertEquals(80.0, student.getAverage(), 0.001);
    }

    @Test
    void assignsCorrectGrades() {
        Student student = new Student("S101", "Rahul");

        student.addOrUpdateMark("Maths", 85);
        assertEquals("A", student.getGrade());

        student.addOrUpdateMark("Maths", 75);
        assertEquals("B", student.getGrade());

        student.addOrUpdateMark("Maths", 65);
        assertEquals("C", student.getGrade());

        student.addOrUpdateMark("Maths", 55);
        assertEquals("D", student.getGrade());

        student.addOrUpdateMark("Maths", 35);
        assertEquals("F", student.getGrade());
    }

    @Test
    void rejectsMarksOutsideValidRange() {
        Student student = new Student("S101", "Rahul");

        assertThrows(
            IllegalArgumentException.class,
            () -> student.addOrUpdateMark("Maths", 101)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> student.addOrUpdateMark("Maths", -1)
        );
    }

    @Test
    void rejectsEmptyStudentDetails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Student("", "Rahul")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Student("S101", "")
        );
    }

    @Test
    void updatingSubjectReplacesPreviousMark() {
        Student student = new Student("S101", "Rahul");

        student.addOrUpdateMark("Maths", 60);
        student.addOrUpdateMark("Maths", 90);

        assertEquals(90, student.getTotal());
        assertEquals(90.0, student.getAverage(), 0.001);
    }
}
