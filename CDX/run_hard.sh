#! /bin/bash

JARS=dist/lib

# -XX:ScopedSize= -XX:ImmortalSize=  
# -XX:+PrintGC -XX:+RTGCPrintStatistics -Xloggc:rtsgc.out

/home/sunrts/bin/java -server -d32 -cp $JARS/common_realtime.jar:$JARS/utils.jar:$JARS/java_memory.jar:$JARS/rtsj_threads.jar:$JARS/detector.jar:$JARS/binary_dump_reader.jar:$JARS/main.jar \
 	-Xms300m -Xmx300m -XX:+PrintGC -XX:+RTGCPrintStatistics -Xloggc:rtsgc.out \
 	-XX:RTGCCriticalReservedBytes=50m -XX:RTGCCriticalPriority=15 \
 	heap/Main input/hard.bin \
 	MAX_FRAMES 10000 PERIOD 10 SIMULATOR_PRIORITY 1 DETECTOR_PRIORITY 20 BUFFER_FRAMES 10000 \
 	PRESIMULATE \
 	DETECTOR_STARTUP_OFFSET_MILLIS 1000 \
 	 $*

