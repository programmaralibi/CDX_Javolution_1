
package realtime;

public class RealtimeThread extends Thread implements Runnable {

  protected javax.realtime.RealtimeThread realThread;
  protected Runnable logic = null;

  public RealtimeThread( SchedulingParameters scheduling ) {

    realThread = new javax.realtime.RealtimeThread( scheduling.getRealParameters(), 
      null,
      null,
      null,
      null,
      this );
  }

  public void deschedulePeriodic() {
    realThread.deschedulePeriodic();
  }

  public void schedulePeriodic() {
    realThread.schedulePeriodic();
  }

  public void setReleaseParameters( ReleaseParameters release ) {
    realThread.setReleaseParameters( release.getRealParameters() );
  }

  protected RealtimeThread() {
  }

  protected void initialize ( javax.realtime.RealtimeThread realThread, Runnable logic ) {
    this.realThread = realThread;
    this.logic = logic;
  }
  
  public static boolean waitForNextPeriod() {
    return javax.realtime.RealtimeThread.waitForNextPeriod();
  }
  
  public void start() {
    realThread.start();
/*    if (logic != null) {
      this.start();
    }*/
  }
  
  public void run() {
    logic.run();   // this should be overriden when logic == this
    
/*    try {
      realThread.join(); // needed to 
    } catch ( InterruptedException e ) {
    }    */
  }
  
/* no way, join is final
  public void join() {
    realThread.join();
  }
*/

  public void joinReal() throws InterruptedException {
    realThread.join();
  }
}
