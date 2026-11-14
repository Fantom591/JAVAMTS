package validator;

import annotations.Email;
import annotations.NotNull;
import annotations.Range;
import annotations.Size;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class Validator {
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    public static ValidationResult validate(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        List<ValidationError> errors = new ArrayList<>();
        Class<?> clas = object.getClass();
        while (clas != null && clas != Object.class) {
            for (Field field : clas.getDeclaredFields()) {
                if (field.isSynthetic()) {
                    continue;
                }
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                Object value = readField(field, object);
                validateField(field, value, errors);
            }
            clas = clas.getSuperclass();
        }
        return new ValidationResult(errors);
    }

    private static Object readField(Field field, Object object) {
        String mes="Cannot access field '" + field.getName()
            + "' of class " + field.getDeclaringClass().getName();
        try {
            if (!field.trySetAccessible()) {
                throw new ValidationConfigurationException(mes);
            }
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new ValidationConfigurationException(mes);
        }
    }

    private static void validateField(Field field, Object value, List<ValidationError> errors) {
        NotNull notNull = field.getAnnotation(NotNull.class);
        if (notNull != null) {
            validateNotNull(field, value, notNull, errors);
        }

        Size size = field.getAnnotation(Size.class);
        if (size != null) {
            validateSize(field, value, size, errors);
        }

        Range range = field.getAnnotation(Range.class);
        if (range != null) {
            validateRange(field, value, range, errors);
        }

        Email email = field.getAnnotation(Email.class);
        if (email != null) {
            validateEmail(field, value, email, errors);
        }
    }

    private static void validateNotNull(Field field, Object value, NotNull ann, List<ValidationError> errors) {
        if (field.getType().isPrimitive()) {
            return;
        }
        if (value == null) {
            errors.add(new ValidationError(field.getName(), "NotNull", ann.message()));
        }
    }

    private static void validateSize(Field field, Object value,
                                     Size ann, List<ValidationError> errors) {
        if (ann.min() < 0) {
            throw new ValidationConfigurationException();
        }
        if (ann.min() > ann.max()) {
            throw new ValidationConfigurationException();
        }
        if (!CharSequence.class.isAssignableFrom(field.getType())) {
            throw new ValidationConfigurationException();
        }
        if (value == null) {
            return;
        }
        int length = ((CharSequence) value).length();
        if (length < ann.min() || length > ann.max()) {
            errors.add(new ValidationError(field.getName(), "Size", ann.message()));
        }
    }

    private static void validateEmail(Field field, Object value,
                                      Email ann, List<ValidationError> errors) {
        if (!CharSequence.class.isAssignableFrom(field.getType())) {
            throw new ValidationConfigurationException();
        }
        if (value == null) {
            return;
        }
        String s = value.toString();
        if (s.isBlank() || !EMAIL_PATTERN.matcher(s).matches()) {
            errors.add(new ValidationError(field.getName(), "Email", ann.message()));
        }
    }

    private static void validateRange(Field field, Object value,
                                      Range ann, List<ValidationError> errors) {
        if (Double.isNaN(ann.min()) || Double.isNaN(ann.max())) {
            throw new ValidationConfigurationException();
        }
        if (Double.isInfinite(ann.min()) || Double.isInfinite(ann.max())) {
            throw new ValidationConfigurationException();
        }
        if (ann.min() > ann.max()) {
            throw new ValidationConfigurationException();
        }
        if (!isNumeric(field.getType())) {
            throw new ValidationConfigurationException();
        }
        if (value == null) {
            return;
        }
        double v = ((Number) value).doubleValue();
        if (Double.isNaN(v) || v < ann.min() || v > ann.max()) {
            errors.add(new ValidationError(field.getName(), "Range", ann.message()));
        }
    }

    private static boolean isNumeric(Class<?> type) {
        if (type.isPrimitive()) {
            return type == int.class || type == long.class
                || type == short.class || type == byte.class
                || type == float.class || type == double.class;
        }
        return Number.class.isAssignableFrom(type);
    }


}
