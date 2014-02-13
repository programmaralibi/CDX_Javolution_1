#! /bin/bash

MEMORY=java
THREADS=rtsj
JARS=dist/lib

if [ "X$1" != X ] ; then
	MEMORY=$1
	shift
	if [ "X$1" != X ] ; then
		THREADS="$1"
		shift
	fi
fi

echo "Running with $THREADS threads and $MEMORY memory"

# 	-XX:ScopedSize=128m -XX:ImmortalSize=128m \
/home/sunrts/bin/java -cp $JARS/common_realtime.jar:$JARS/utils.jar:$JARS/${MEMORY}_memory.jar:$JARS/${THREADS}_threads.jar:$JARS/detector.jar  \
 	-Xms50m -Xmx50m  \
 	heap/Main input/mega10 \
 	MAX_FRAMES 400 PERSISTENT_DETECTOR_SCOPE_SIZE 500000 TRANSIENT_DETECTOR_SCOPE_SIZE 600000 PERIOD 5 DETECTOR_PRIORITY 30 $*

