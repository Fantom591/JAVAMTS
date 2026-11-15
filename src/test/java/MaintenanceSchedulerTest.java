import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceSchedulerTest {
  private static MaintenanceScheduler newMaintenanceScheduler(String instant) {
    return new MaintenanceScheduler(
      Clock.fixed(Instant.parse(instant), ZoneOffset.UTC));
  }

  @Test
  void buildsCorrectInstants() throws Exception {
    var window = newMaintenanceScheduler("2024-03-30T00:00:00Z").nextWindow(
      LocalDate.of(2024, 3, 30), LocalTime.of(3, 30),
      Duration.ofMinutes(30), ZoneId.of("Europe/Berlin"),
      OverlapPolicy.EARLIER);
    assertEquals(Instant.parse("2024-03-30T02:30:00Z"), window.start());
    assertEquals(Duration.ofMinutes(30), window.duration());
  }

  @Test
  void rejectsBadDurationAndGap() {
    var maintenanceScheduler = newMaintenanceScheduler("2024-03-30T00:00:00Z");
    var berlin = ZoneId.of("Europe/Berlin");
    assertThrows(DateTimeException.class, () -> maintenanceScheduler.nextWindow(
      LocalDate.of(2024, 3, 30), LocalTime.of(3, 30),
      Duration.ZERO, berlin, OverlapPolicy.EARLIER));
    assertThrows(DateTimeException.class, () -> maintenanceScheduler.nextWindow(
      LocalDate.of(2024, 3, 31), LocalTime.of(2, 30),
      Duration.ofMinutes(30), berlin, OverlapPolicy.EARLIER));
  }

  @Test
  void overlapEarlierVsLater() throws Exception {
    var maintenanceScheduler = newMaintenanceScheduler("2024-10-27T00:00:00Z");
    var berlin = ZoneId.of("Europe/Berlin");
    var firstTest = maintenanceScheduler.nextWindow(LocalDate.of(2024, 10, 27), LocalTime.of(2, 30),
      Duration.ofMinutes(30), berlin, OverlapPolicy.EARLIER);
    var secondTest = maintenanceScheduler.nextWindow(LocalDate.of(2024, 10, 27), LocalTime.of(2, 30),
      Duration.ofMinutes(30), berlin, OverlapPolicy.LATER);
    assertEquals(ZoneOffset.ofHours(2), firstTest.start().atZone(berlin).getOffset());
    assertEquals(ZoneOffset.ofHours(1), secondTest.start().atZone(berlin).getOffset());
    assertEquals(Duration.ofHours(1), Duration.between(firstTest.start(), secondTest.start()));
  }

  @Test
  void rejectsNegativeDays() {
    assertThrows(DateTimeException.class, () -> newMaintenanceScheduler("2024-03-30T00:00:00Z")
      .upcomingWindows(-1, LocalTime.of(3, 30), Duration.ofMinutes(30),
        ZoneId.of("Europe/Berlin"), OverlapPolicy.EARLIER));
  }

  @Test
  void zeroDaysReturnsEmpty() throws Exception {
    assertTrue(newMaintenanceScheduler("2024-03-30T00:00:00Z")
      .upcomingWindows(0, LocalTime.of(3, 30), Duration.ofMinutes(30),
        ZoneId.of("Europe/Berlin"), OverlapPolicy.EARLIER).isEmpty());
  }
}