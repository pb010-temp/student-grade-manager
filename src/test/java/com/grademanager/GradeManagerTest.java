package com.grademanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeManagerTest {

    @Test
    void addsAndFindsStudent() {
        GradeManager manager = new GradeManager();
        Student student = new Student("S101", "Rahul");

        manager.addStudent(student);

        assertTrue(manager.findStudent("S101").isPresent());
        assertEquals("Rahul", manager.findStudent("S101").orElseThrow().getName());
        assertEquals(1, manager.getStudentCount());
    }

    @Test
    void rejectsDuplicateStudentIds() {
        GradeManager manager = new GradeManager();

        manager.addStudent(new Student("S101", "Rahul"));

        assertThrows(
            IllegalArgumentException.class,
            () -> manager.addStudent(new Student("S101", "Priya"))
        );
    }

    @Test
    void deletesStudent() {
        GradeManager manager = new GradeManager();
        manager.addStudent(new Student("S101", "Rahul"));

        assertTrue(manager.deleteStudent("S101"));
        assertFalse(manager.findStudent("S101").isPresent());
        assertEquals(0, manager.getStudentCount());
    }

    @Test
    void returnsFalseWhenDeletingMissingStudent() {
        GradeManager manager = new GradeManager();

        assertFalse(manager.deleteStudent("S999"));
    }
}
