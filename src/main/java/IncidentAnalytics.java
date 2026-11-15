import java.math.BigInteger;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class IncidentAnalytics {
  static Duration totalDowntime(List<Incident> incidents) {
    return incidents.stream()
      .map(Incident::duration)
      .reduce(Duration.ZERO, Duration::plus);
  }

  static Duration averageDowntime(List<Incident> incidents) {
    if (incidents == null || incidents.isEmpty()) {
      return Duration.ZERO;
    }
    BigInteger sum = incidents.stream()
      .map(Incident::duration)
      .map(Duration::toNanos)
      .map(BigInteger::valueOf)
      .reduce(BigInteger.ZERO, BigInteger::add);
    BigInteger average = sum.divide(BigInteger.valueOf(incidents.size()));
    BigInteger nano = BigInteger.valueOf(1_000_000_000);
    return Duration.ofSeconds(average.divide(nano).longValue(), average.mod(nano).longValue());
  }

  static Optional<Incident> longestIncident(List<Incident> incidents) {
    return incidents.stream()
      .max((a, b) -> {
        if (a.duration().compareTo(b.duration()) != 0) {
          return a.duration().compareTo(b.duration());
        } else if (a.startedAt().compareTo(b.startedAt()) != 0) {
          return -a.startedAt().compareTo(b.startedAt());
        } else {
          return -a.id().compareTo(b.id());
        }
      });
  }

  static Map<LocalDate, List<Incident>> groupByStartDate(
    List<Incident> incidents, ZoneId zone) {
    return incidents.stream()
      .sorted(Comparator.comparing(Incident::startedAt)
        .thenComparing(Incident::id))
      .collect(Collectors.groupingBy(
        inc -> LocalDate.ofInstant(inc.startedAt(), zone),
        TreeMap::new,
        Collectors.toList()
      ));
  }
}

