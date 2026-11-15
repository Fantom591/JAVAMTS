import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LegacyDateAdapterTest {

  @Test
  void dateInstantRoundTrip() throws Exception {
    Instant i = Instant.parse("2024-03-31T01:00:00.123Z");
    Date d = LegacyDateAdapter.toDate(i);
    assertEquals(i, LegacyDateAdapter.toInstant(d));
  }

  @Test
  void calendarRoundTripPreservesInstantAndZone() throws Exception {
    ZonedDateTime zdt = ZonedDateTime.of(
      2024, 3, 31, 3, 0, 0, 123_000_000,
      ZoneId.of("Europe/Berlin"));

    var callendar = LegacyDateAdapter.toGregorianCalendar(zdt);
    var back = LegacyDateAdapter.toZonedDateTime(callendar);

    assertEquals(zdt.toInstant(), back.toInstant());
    assertEquals(zdt.getZone(), back.getZone());
  }

  @Test
  void rejectsNull() {
    assertThrows(DateTimeException.class, () -> LegacyDateAdapter.toInstant(null));
    assertThrows(DateTimeException.class, () -> LegacyDateAdapter.toDate(null));
    assertThrows(DateTimeException.class, () -> LegacyDateAdapter.toZonedDateTime(null));
    assertThrows(DateTimeException.class, () -> LegacyDateAdapter.toGregorianCalendar(null));
  }
}