import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class MaintenanceScheduler {
  Clock clock;

  public MaintenanceScheduler(Clock clock) {
    this.clock = clock;
  }

  public MaintenanceWindow nextWindow(
    LocalDate date, LocalTime localStart, Duration duration,
    ZoneId zone, OverlapPolicy policy) throws Exception {
    Instant start = null;
    Instant end = null;
    if (duration.isNegative() || duration.isZero()) {
      Error.print("Длительность должна быть положительна");
    }
    LocalDateTime localStartDateTime = LocalDateTime.of(date, localStart);
    LocalDateTime localEndDateTime = localStartDateTime.plus(duration);
    if (zone.getRules().getTransition(localStartDateTime) != null &&
      zone.getRules().getTransition(localStartDateTime).isGap() ||
      zone.getRules().getTransition(localEndDateTime) != null &&
        zone.getRules().getTransition(localEndDateTime).isGap()) {
      Error.print("Время попало в Gap");
    }

    if (OverlapPolicy.EARLIER.equals(policy)) {
      start = localStartDateTime.atZone(zone).withEarlierOffsetAtOverlap().toInstant();
      end = localEndDateTime.atZone(zone).withEarlierOffsetAtOverlap().toInstant();
    } else if (OverlapPolicy.LATER.equals(policy)) {
      start = localStartDateTime.atZone(zone).withLaterOffsetAtOverlap().toInstant();
      end = localEndDateTime.atZone(zone).withLaterOffsetAtOverlap().toInstant();
    } else {
      Error.print("Некоректное policy");
    }
    return new MaintenanceWindow(start, end);
  }

  public List<MaintenanceWindow> upcomingWindows(
    int days, LocalTime localStart, Duration duration,
    ZoneId zone, OverlapPolicy policy) throws Exception {
    if (days < 0) {
      Error.print("Количество дней не может быть отрицательным");
    }
    var list = new ArrayList<MaintenanceWindow>(days);
    var nowDate = LocalDate.now(clock.withZone(zone));
    for (int i = 0; i < days; i++) {
      list.add(nextWindow(nowDate, localStart, duration, zone, policy));
      nowDate = nowDate.plus(1, ChronoUnit.DAYS);
    }
    return list;
  }
}
