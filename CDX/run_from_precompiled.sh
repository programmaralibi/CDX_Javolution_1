#! /bin/bash

MEMORY=java
THREADS=java
JARS=dist/lib


if [ "X$1" != X ] ; then
	MEMORY=$1
	if [ "X$2" != X ] ; then
		THREADS="X$2"
	fi
fi

echo "Running with $THREADS threads and $MEMORY memory"

java -cp $JARS/common_realtime.jar:$JARS/utils.jar:$JARS/${MEMORY}_memory.jar:$JARS/${THREADS}_threads.jar:$JARS/detector.jar:$JARS/precompiled_simulator.jar:$JARS/main.jar \
 	-Xms40m -Xmx40m  \
 	heap/Main unused \
 	MAX_FRAMES 400 PERSISTENT_DETECTOR_SCOPE_SIZE 500000 TRANSIENT_DETECTOR_SCOPE_SIZE 600000 PERIOD 50 DETECTOR_PRIORITY 9 TIME_SCALE 1
