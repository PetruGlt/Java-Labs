
import java.time.LocalTime;

public class TimeInterval extends Pair<LocalTime, LocalTime> {
    public TimeInterval(LocalTime first, LocalTime second) {
        super(first, second);
    }

    public boolean overlaps(TimeInterval other) {
        return getFirst().isBefore(other.getSecond()) && getFirst().isAfter(other.getSecond());
    }
}
