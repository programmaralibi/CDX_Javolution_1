
package realtime;

public class NoHeapRealtimeThread extends RealtimeThread {

  public NoHeapRealtimeThread(SchedulingParameters scheduling,
    ReleaseParameters release, MemoryParameters memory, MemoryAreaWrapper area,
    ProcessingGroupParameters group, Runnable logic) {
    
      Runnable rtsjLogic = (logic == null) ? this : logic;
    
      if (area == null || area.isReal()) {
      
        initialize( new javax.realtime.NoHeapRealtimeThread(
          (scheduling == null) ? null : scheduling.getRealParameters(),
          (release == null) ? null : release.getRealParameters(),
          (memory == null) ? null : memory.getRealParameters(),
          (area == null) ? null : (javax.realtime.MemoryArea)area.getRealArea(),
          (group == null) ? null : group.getRealParameters(),
          rtsjLogic), logic);    
            
      } else {  // since the area is not real, we are completely in heap,
                // so the heap parameters (and other parameters, potentially)
                // are also in heap, and thus we cannot create non-heap thread
                
        initialize( new javax.realtime.RealtimeThread(
          (scheduling == null) ? null : scheduling.getRealParameters(),
          (release == null) ? null : release.getRealParameters(),
          (memory == null) ? null : memory.getRealParameters(),
          null,
          (group == null) ? null : group.getRealParameters(),
          rtsjLogic), logic);
      }
  }
}
