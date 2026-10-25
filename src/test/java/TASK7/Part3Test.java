package TASK7;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Part3Test {
    StudentRegister register = new StudentRegister();
    static Student student1=new Student(1, "Misha", 10.0);
    static Student student2=new Student(4, "Artem", 7.0);
    static Student student3=new Student(2, "Grisha", 5.0);
    static Student student4=new Student(3, "Ignat", 2.0);
    static Student student5=new Student(5, "Danil", 1.0);

    @BeforeEach
    void setUp() {
        register.add(student3);
        register.add(student2);
        register.add(student4);
        register.add(student1);
        register.add(student5);
    }

    @Test
    void findStudentsInEmptyMapReturnsEmptyList() {
        assertThat(register.findStudentsByGradeRange(new HashMap<>(), 0, 10)).isEmpty();
    }

    @Test
    void getTopNFromEmptyMapReturnsEmptyList() {
        NavigableMap<Integer, Student> empty = new TreeMap<>(Comparator.reverseOrder());
        assertThat(register.getTopNStudentsById(empty, 5)).isEmpty();
    }

    static Arguments[] minMaxGradeForTest1() {
        return new Arguments[]{
            Arguments.of(-5, 8),
            Arguments.of(2, 15),
            Arguments.of(9, 2)
        };
    }

    @ParameterizedTest
    @MethodSource("minMaxGradeForTest1")
    void isThrowExceptionCorrect(double minGrade, double maxGrade) {
        assertThrows(IllegalArgumentException.class, () -> register.findStudentsByGradeRange(register.hashMap, minGrade, maxGrade));
        assertThrows(IllegalArgumentException.class, () -> register.findStudentsByGradeRange(register.treeMap, minGrade, maxGrade));
    }

    static Arguments[] minMaxGradeForTest2() {
        return new Arguments[]{
            Arguments.of(7, 10,new ArrayList(List.of(student1,student2))),
            Arguments.of(1, 2,new ArrayList(List.of(student4,student5))),
            Arguments.of(1, 10,new ArrayList(List.of(student1,student2,student3,student4,student5)))
        };
    }

    @ParameterizedTest
    @MethodSource("minMaxGradeForTest2")
    void isResultsCorrect(double minGrade, double maxGrade,List<Student> students) {
        List<Student> list=register.findStudentsByGradeRange(register.hashMap,minGrade,maxGrade);
        assertThat(list).containsExactlyInAnyOrderElementsOf(students);
        list=register.findStudentsByGradeRange(register.treeMap,minGrade,maxGrade);
        assertThat(list).containsExactlyInAnyOrderElementsOf(students);
    }

    static Arguments[] nForTest3() {
        return new Arguments[]{
            Arguments.of(-2)
        };
    }

    @ParameterizedTest
    @MethodSource("nForTest3")
    void checkExceptionInGetTopNStudentsById(int n){
        assertThrows(IllegalArgumentException.class,()->register.getTopNStudentsById(register.treeMap,n));
    }

    static Arguments[] nForTest4() {
        return new Arguments[]{
            Arguments.of(2,new ArrayList(List.of(student5,student2))),
            Arguments.of(5,new ArrayList(List.of(student5,student2,student4,student3,student1))),
            Arguments.of(15,new ArrayList(List.of(student5,student2,student4,student3,student1)))
        };
    }

    @ParameterizedTest
    @MethodSource("nForTest4")
    void checkResultInGetTopNStudentsById(int n,List<Student> students){
        List<Student> list=register.getTopNStudentsById(register.treeMap,n);
        assertThat(list).containsExactlyElementsOf(students);

    }

    @Test
    void checkIfNEquals0(){
        List<Student> list=register.getTopNStudentsById(register.treeMap,0);
        assertThat(list.size()).isEqualTo(0);
    }





}