
package realtime;

public class SchedulingParameters {

  private final javax.realtime.SchedulingParameters realParameters;

  protected SchedulingParameters( javax.realtime.SchedulingParameters realParameters ) {
    this.realParameters = realParameters;
  }

  public javax.realtime.SchedulingParameters getRealParameters() {
    return realParameters;
  }
}
