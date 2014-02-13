
package realtime;

public class LTMemory extends ScopedMemory {

  public LTMemory(long initial, long maximum) {
    super( new javax.realtime.LTMemory( initial, maximum ) );
  }  
}
