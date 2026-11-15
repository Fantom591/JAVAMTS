import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;


public final class LegacyDateAdapter {
  public static Instant toInstant(Date date) throws Exception{
    if (date == null) {
      Error.print("Дата не должна быть пустой");
    }
    return date.toInstant();
  }

  public static Date toDate(Instant instant) throws Exception {
    if (instant == null) {
      Error.print("Время не должно быть пустым");
    }
    return Date.from(instant);
  }

  public static ZonedDateTime toZonedDateTime(GregorianCalendar calendar) throws Exception{
    if (calendar == null) {
      Error.print("Календарь не должен быть пустым");
    }
    Instant instant = calendar.toInstant();
    ZoneId zoneId = calendar.getTimeZone().toZoneId();
    return instant.atZone(zoneId);
  }

  public static GregorianCalendar toGregorianCalendar(ZonedDateTime zonedDateTime) throws Exception{
    if (zonedDateTime == null) {
      Error.print("Время не должно быть пустым");
    }
    return GregorianCalendar.from(zonedDateTime);
  }
}
