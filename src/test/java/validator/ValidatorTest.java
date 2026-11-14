package validator;

import org.junit.jupiter.api.Test;
import annotations.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    static class Ok {
        @NotNull
        String name = "Alice";
        @Size(min = 2, max = 5)
        String code = "abc";
        @Range(min = 1, max = 10)
        int age = 5;
        @Email
        String email = "a@b.c";
    }


    static class Bad {
        @NotNull(message = "n")
        String a = null;
        @Size(min = 2, max = 3, message = "s")
        String b = "x";
        @Range(min = 0, max = 1, message = "r")
        int c = 5;
        @Email(message = "e")
        String d = "bad";
    }

    static class Bounds {
        @Size(min = 2, max = 4, message = "s")
        String s;
        @Range(min = 1, max = 10, message = "r")
        int i;

        Bounds(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }


    static class Config {
        @Size(min = -1, max = 5)
        String negMin = "x";
        @Size(min = 5, max = 2)
        String badOrder = "x";
        @Size(min = 1, max = 2)
        int sizeOnInt = 1;
        @Range(min = 5, max = 2)
        int rangeOrder = 1;
        @Range(min = 1, max = 2)
        String rangeOnString = "x";
        @Range(min = Double.NaN, max = 2)
        int rangeNaN = 1;
        @Range(min = 1, max = Double.POSITIVE_INFINITY)
        int rangeInf = 1;
        @Email
        int emailOnInt = 1;
    }

    @Test
    void validObject() {
        ValidationResult r = Validator.validate(new Ok());
        assertTrue(r.isValid());
        assertTrue(r.getErrors().isEmpty());
    }

    @Test
    void violationsCollected() {
        Set<ValidationError> expected = Set.of(
            new ValidationError("a", "NotNull", "n"),
            new ValidationError("b", "Size", "s"),
            new ValidationError("c", "Range", "r"),
            new ValidationError("d", "Email", "e")
        );
        assertEquals(expected, new HashSet<>(Validator.validate(new Bad()).getErrors()));
    }

    @ParameterizedTest
    @CsvSource({"ab,5", "abc,5", "abcd,5"})
    void sizeBoundsPass(String s, int i) {
        assertTrue(Validator.validate(new Bounds(s, i)).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcde"})
    void sizeOutOfBoundsFail(String s) {
        assertEquals("Size",
            Validator.validate(new Bounds(s, 5)).getErrors().get(0).annotationName());
    }

    @ParameterizedTest
    @CsvSource({"abc,1", "abc,5", "abc,10"})
    void rangeBoundsPass(String s, int i) {
        assertTrue(Validator.validate(new Bounds(s, i)).isValid());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void rangeOutOfBoundsFail(int i) {
        assertEquals("Range",
            Validator.validate(new Bounds("abc", i)).getErrors().get(0).annotationName());
    }

    @Test
    void validateNullThrows() {
        assertThrows(NullPointerException.class, () -> Validator.validate(null));
    }

    @Test
    void configErrorsThrow() {
        assertThrows(ValidationConfigurationException.class,
            () -> Validator.validate(new Config()));
    }

    @Test
    void notNullDetects() {
        var e = Validator.validate(new Object() {
            @NotNull(message = "n")
            String v = null;
        }).getErrors();
        assertEquals(Set.of(new ValidationError("v", "NotNull", "n")), new HashSet<>(e));
    }

    static class Base { @NotNull(message = "base") private String base = null; }
    static class Child extends Base {
        @NotNull(message = "child") private String child = null;
        @NotNull(message = "static") private static String STATIC = null;
    }

    @Test void privateInheritedStatic() {
        var expected = Set.of(
            new ValidationError("base", "NotNull", "base"),
            new ValidationError("child", "NotNull", "child"));
        assertEquals(expected, new HashSet<>(Validator.validate(new Child()).getErrors()));
    }

    @Test void getErrorsImmutable() {
        var errors = Validator.validate(new Bad()).getErrors();
        assertThrows(UnsupportedOperationException.class,
            () -> errors.add(new ValidationError("x", "X", "x")));
    }



}