import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class IncidentParserTest {
  @Test
  void parsesValidLineAndDuration() throws Exception {
    Incident i = IncidentParser.parse(
      "INC-1;billing;2024-03-30T22:30:00Z;2024-03-31T01:00:00Z");
    assertNotNull(i);
    assertEquals("INC-1", i.id());
    assertEquals("billing", i.service());
    assertEquals(Instant.parse("2024-03-30T22:30:00Z"), i.startedAt());
    assertEquals(Instant.parse("2024-03-31T01:00:00Z"), i.resolvedAt());
    assertEquals(Duration.ofMinutes(150), i.duration());
  }

  @Test
  void rejectsInvalidTimestamp() {
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(
      "INC-1;billing;2024-02-31T00:00:00Z;2024-03-01T00:00:00Z"));
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(
      "INC-1;billing;not-a-time;2024-03-01T00:00:00Z"));
  }
  @Test
  void rejectsId() {
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(
      ";billing;2024-03-30T22:30:00Z;2024-03-31T01:00:00Z"));
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(
      "WRONG-1;billing;2024-03-30T22:30:00Z;2024-03-31T01:00:00Z"));
  }
  @Test
  void rejectsEndBeforeStart() {
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(
      "INC-1;billing;2024-03-31T01:00:00Z;2024-03-30T22:30:00Z"));
  }

  @Test
  void rejectsWrongFieldData() {
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(
      "INC-1;billing;2024-03-30T22:30:00Z"));
    assertThrows(DateTimeException.class, () -> IncidentParser.parse(null));
  }

}