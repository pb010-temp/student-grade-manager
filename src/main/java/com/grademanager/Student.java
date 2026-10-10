package com.grademanager;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private final String id;
    private final String name;
    private final Map<String, Integer> marks = new LinkedHashMap<>();

    public Student(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }

        this.id = id.trim();
        this.name = name.trim();
    }

    public void addOrUpdateMark(String subject, int mark) {
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }

        marks.put(subject.trim(), mark);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getMarks() {
        return Map.copyOf(marks);
    }

    public int getTotal() {
        return marks.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getAverage() {
        if (marks.isEmpty()) {
            return 0.0;
        }

        return (double) getTotal() / marks.size();
    }

    public String getGrade() {
        double average = getAverage();

        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        return "F";
    }
}
