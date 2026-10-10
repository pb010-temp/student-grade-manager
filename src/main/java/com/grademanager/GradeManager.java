package com.grademanager;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class GradeManager {
    private final Map<String, Student> students = new LinkedHashMap<>();

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student ID already exists");
        }

        students.put(student.getId(), student);
    }

    public Optional<Student> findStudent(String id) {
        return Optional.ofNullable(students.get(id));
    }

    public boolean deleteStudent(String id) {
        return students.remove(id) != null;
    }

    public Collection<Student> getAllStudents() {
        return java.util.List.copyOf(students.values());
    }

    public int getStudentCount() {
        return students.size();
    }
}
