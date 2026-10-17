package TASK6;

public class ArrayUtils {
    public static <T> int findFirst(T[] array, T element) {
        if (array == null) {
            return -1;
        }
        int idx = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                if (element == null) {
                    return i;
                } else {
                    continue;
                }
            } else if (array[i].equals(element)) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        // пример использования
        final String[] names = {"Alice", "Bob", "Charlie"};
        final int index = ArrayUtils.findFirst(names, "Bob");// Ожидаем: 1
        System.out.println(index);
    }
}
