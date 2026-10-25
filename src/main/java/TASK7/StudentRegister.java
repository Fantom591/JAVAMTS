package TASK7;

import java.util.*;

record Student(int id, String name, double grade) {
    public Student {
        if (id <= 0) {
            throw new IllegalArgumentException("Некорректный ID");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Некорректное Имя");
        }
        if (grade < 0 || grade > 10) {
            throw new IllegalArgumentException("Некорректная оценка");
        }
    }
}

public class StudentRegister {
    Map<Integer, Student> hashMap = new HashMap<>();
    NavigableMap<Integer, Student> treeMap = new TreeMap<>(Comparator.reverseOrder());

    List<Student> findStudentsByGradeRange(Map<Integer, Student> students, double minGrade, double maxGrade) {
        if ((minGrade > maxGrade) || (minGrade < 0) || (maxGrade > 10)) {
            throw new IllegalArgumentException();
        }
        List<Student> list = new ArrayList<>();
        students.forEach((key, value) -> {
            if (minGrade <= value.grade() && value.grade() <= maxGrade) {
                list.add(value);
            }
        });
        return list;
    }

    List<Student> getTopNStudentsById(NavigableMap<Integer, Student> students, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        n = Math.min(n, students.size());
        List<Student> list = new ArrayList<>();
        for (Student s : students.values()) {
            if (list.size() == n) {
                break;
            }
            list.add(s);
        }
        return list;
    }

    public void add(Student student) {
        hashMap.put(student.id(), student);
        treeMap.put(student.id(), student);
    }
}
