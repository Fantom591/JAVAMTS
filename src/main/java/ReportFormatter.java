import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class ReportFormatter {

  private static DateTimeFormatter format =
    DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm XXX (VV)");

  public static String formatWindow(MaintenanceWindow window, ZoneId zone, Locale locale) {
    format = format.withLocale(locale);
    ZonedDateTime start = window.start().atZone(zone);
    ZonedDateTime end = window.end().atZone(zone);
    return format.format(start) + " – " + format.format(end);
  }
}