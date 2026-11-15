import java.time.DateTimeException;

public class Error {
  static void print(String mes) throws Exception {
    System.err.println("Ошибка данных: " + mes);
    throw new DateTimeException("");
  }
}
