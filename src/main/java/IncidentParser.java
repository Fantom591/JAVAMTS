import java.time.Instant;
import java.util.regex.Pattern;

public class IncidentParser {
  static final Pattern idSample = Pattern.compile("^INC-[0-9]+$");

  static final Pattern serviceSample = Pattern.compile("^[a-z]+$");

  private static boolean check(String word, Pattern pattern) {
    return pattern.matcher(word).matches();
  }

  static public Incident parse(String line) throws Exception {
    if (line == null) {
      Error.print("Пустая строка");
    }
    String[] poles = line.split(";");
    if (poles.length != 4) {
      Error.print("Неверное количество входных полей");
    }
    boolean is_CorrectData = check(poles[0], idSample) &&
      check(poles[1], serviceSample);
    if (!is_CorrectData) {
      Error.print("Неверной содержание");
    }
    Instant startedAt = Instant.parse(poles[2]);
    Instant resolvedAt = Instant.parse(poles[3]);
    if (startedAt.isBefore(resolvedAt)) {
      return new Incident(poles[0], poles[1], startedAt, resolvedAt);
    }
    Error.print("Время окончания должно после времени начала");
    return null;
  }
}
