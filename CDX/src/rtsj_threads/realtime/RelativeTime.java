
package realtime;

public class RelativeTime extends HighResolutionTime {

  public RelativeTime(long millis, int nanos) {
    super(new javax.realtime.RelativeTime( millis, nanos ));
  }
}
