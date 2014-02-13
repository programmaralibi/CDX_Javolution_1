#! /bin/bash

JARS=dist/lib
#	MAX_FRAMES 20000 PERIOD 10 DETECTOR_PRIORITY 9 FPS 500 \

java -cp $JARS/common_realtime.jar:$JARS/utils.jar:$JARS/java_memory.jar:$JARS/java_threads.jar:$JARS/detector.jar:$JARS/simulator.jar:$JARS/main.jar \
 	-Xms1500m -Xmx1500m  \
 	heap/Main input/easy \
	MAX_FRAMES 25000 PERIOD 4 DETECTOR_PRIORITY 9 FPS 250 \
	BUFFER_FRAMES 25000 \
 	PRESIMULATE \
	FRAMES_BINARY_DUMP \
	SIMULATE_ONLY \
	2>&1
