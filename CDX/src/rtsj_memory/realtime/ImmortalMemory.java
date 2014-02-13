
package realtime;

public class ImmortalMemory extends MemoryArea {

  protected static ImmortalMemory singleton = new ImmortalMemory();

  protected ImmortalMemory() {
    super( javax.realtime.ImmortalMemory.instance() );
  }

  public static ImmortalMemory instance() {
    return singleton;
  }
  
}
