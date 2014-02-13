
package realtime;

public class HighResolutionTime {

  protected javax.realtime.HighResolutionTime realTime;

  HighResolutionTime( javax.realtime.HighResolutionTime realTime ) {
    this.realTime = realTime;
  }

  public long getMilliseconds() {
    return realTime.getMilliseconds();
  }
  
  public int getNanoseconds() {
    return realTime.getNanoseconds();
  }

  javax.realtime.HighResolutionTime getRealTime() {
    return realTime;
  }
}
