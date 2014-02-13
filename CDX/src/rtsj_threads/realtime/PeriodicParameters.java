
package realtime;

public class PeriodicParameters extends ReleaseParameters {

  public PeriodicParameters( HighResolutionTime start, RelativeTime period, RelativeTime cost,
    RelativeTime deadline, AsyncEventHandler overrunHandler, AsyncEventHandler missHandler ) {
    
      super( 
        new javax.realtime.PeriodicParameters(
          ( start == null ) ? null : start.getRealTime(),
          ( period == null ) ? null : (javax.realtime.RelativeTime) period.getRealTime(),
          ( cost == null ) ? null : (javax.realtime.RelativeTime) cost.getRealTime(),
          ( deadline == null ) ? null : (javax.realtime.RelativeTime) deadline.getRealTime(),
          ( overrunHandler == null ) ? null : overrunHandler.getRealHandler(),
          ( missHandler == null ) ? null : missHandler.getRealHandler()
        )
      );
  }
}
