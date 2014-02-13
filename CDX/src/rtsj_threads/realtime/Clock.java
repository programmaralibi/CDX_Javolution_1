
package realtime;

public class Clock  {

  static Clock singleton = new Clock();

  public static Clock getRealtimeClock() {
    return singleton;
  }

  public AbsoluteTime getTime() {
    return new AbsoluteTime( javax.realtime.Clock.getRealtimeClock().getTime() );
  }

}
