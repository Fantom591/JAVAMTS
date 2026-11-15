import java.time.*;

public record Incident(
  String id,
  String service,
  Instant startedAt,
  Instant resolvedAt) {
  public Duration duration(){
    return Duration.between(startedAt,resolvedAt);
  }
}
