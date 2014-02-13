#! /bin/bash

JARS=dist/lib

# -XX:ScopedSize= -XX:ImmortalSize=  
# -XX:+PrintGC -XX:+RTGCPrintStatistics -Xloggc:rtsgc.out

/home/sunrts/bin/java -server -d32 -cp /home/kalibera/SPECjvm98:$JARS/common_realtime.jar:$JARS/utils.jar:$JARS/java_memory.jar:$JARS/rtsj_threads.jar:$JARS/detector.jar:$JARS/binary_dump_reader.jar:$JARS/main.jar \
 	-Xms2000m -Xmx2000m \
 	-XX:RTGCCriticalReservedBytes=600m -XX:RTGCCriticalPriority=15 \
 	heap/Main input/easy.bin \
 	MAX_FRAMES 20000 PERIOD 4 SIMULATOR_PRIORITY 1 DETECTOR_PRIORITY 20 BUFFER_FRAMES 20000 \
 	PRESIMULATE \
 	DETECTOR_STARTUP_OFFSET_MILLIS 1000 \
 	USE_SPEC_NOISE \
 	SPEC_NOISE_ARGS "-a -b -s100 -m20 -M20 -t _213_javac" \
 	DETECTOR_NOISE DETECTOR_NOISE_ALLOCATE_POINTERS 20 DETECTOR_NOISE_VARIABLE_ALLOCATION_SIZE \
 	 $*

