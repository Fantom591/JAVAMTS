import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IncidentAnalyticsTest {
  private static Incident createIncident(String id, long nanos) {
    Instant start = Instant.parse("2024-01-01T00:00:00Z");
    return new Incident(id, "s", start, start.plusNanos(nanos));
  }
  @Test
  void totalAndAverageRoundedDown() {
    var list = List.of(createIncident("A", 10), createIncident("B", 20), createIncident("C", 31));
    assertEquals(Duration.ofNanos(61), IncidentAnalytics.totalDowntime(list));
    assertEquals(Duration.ofNanos(20), IncidentAnalytics.averageDowntime(list));
  }
  @Test
  void longestIncidentCheck() {
    Instant t0 = Instant.parse("2024-01-01T00:00:00Z");
    var a = new Incident("INC-2", "s", t0.plusSeconds(10), t0.plusSeconds(20));
    var b = new Incident("INC-1", "s", t0,t0.plusSeconds(10));
    assertEquals("INC-1",
      IncidentAnalytics.longestIncident(List.of(a, b)).orElseThrow().id());
    var c = new Incident("INC-0", "s", t0, t0.plusSeconds(10));
    assertEquals("INC-0",
      IncidentAnalytics.longestIncident(List.of(b, c)).orElseThrow().id());
  }
  @Test
  void emptyListCheck() {
    List<Incident> empty = List.of();
    assertEquals(Duration.ZERO, IncidentAnalytics.totalDowntime(empty));
    assertEquals(Duration.ZERO, IncidentAnalytics.averageDowntime(empty));
    assertTrue(IncidentAnalytics.longestIncident(empty).isEmpty());
    assertTrue(IncidentAnalytics.groupByStartDate(empty, ZoneId.of("UTC")).isEmpty());
  }
  @Test
  void localDateInBerlinDiffersFromUtcDate() {
    var a = new Incident("INC-1", "s",
      Instant.parse("2024-03-30T23:30:00Z"),
      Instant.parse("2024-03-31T00:00:00Z"));
    var b = new Incident("INC-2", "s",
      Instant.parse("2024-03-31T00:30:00Z"),
      Instant.parse("2024-03-31T01:00:00Z"));

    var byBerlin = IncidentAnalytics.groupByStartDate(
      List.of(b, a), ZoneId.of("Europe/Berlin"));
    assertEquals(Set.of(LocalDate.of(2024, 3, 31)), byBerlin.keySet());
    assertEquals(List.of("INC-1", "INC-2"),
      byBerlin.get(LocalDate.of(2024, 3, 31))
        .stream().map(Incident::id).toList());
    var byUtc = IncidentAnalytics.groupByStartDate(
      List.of(a, b), ZoneId.of("UTC"));
    assertEquals(Set.of(LocalDate.of(2024, 3, 30), LocalDate.of(2024, 3, 31)),
      byUtc.keySet());
  }
}