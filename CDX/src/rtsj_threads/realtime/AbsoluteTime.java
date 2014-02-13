
package realtime;

public class AbsoluteTime extends HighResolutionTime {

  AbsoluteTime( javax.realtime.AbsoluteTime realTime ) {
    super( realTime );
  }

  public AbsoluteTime( long millis, int nanos ) {
    super( new javax.realtime.AbsoluteTime( millis, nanos ) );
  }

  public AbsoluteTime add( long millis, int nanos ) {
    return new AbsoluteTime( ((javax.realtime.AbsoluteTime)realTime).add(millis, nanos) );
  }
}
