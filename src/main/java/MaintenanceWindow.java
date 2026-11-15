import java.time.Duration;
import java.time.Instant;

public record MaintenanceWindow (
  Instant start,
  Instant end){
  public Duration duration(){
    return Duration.between(start,end);
  }
}
