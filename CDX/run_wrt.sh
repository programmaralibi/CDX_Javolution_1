#! /bin/bash

MEMORY=rtsj
THREADS=rtsj
JARS=dist/lib

SPECDIR=/home/kalibera/SPECjvm98

if [ "X$1" != X ] ; then
	MEMORY=$1
	shift
	if [ "X$1" != X ] ; then
		THREADS="$1"
		shift
	fi
fi

echo "Running with $THREADS threads and $MEMORY memory"

#  	-Xgc:scopedMemoryMaximumSize=512m -Xgc:immortalMemorySize=512m \
# 	-Xms50m -Xmx50m  \
# 	-Xms1g -Xmx1g \
#	-Xdump:java:events=throw,filter=java/lang/OutOfMemoryError \
# -XXgc:trigger=

/opt/ibm-wrt-i386-60/bin/java -Xrealtime -cp ${SPECDIR}:$JARS/common_realtime.jar:$JARS/utils.jar:$JARS/${MEMORY}_memory.jar:$JARS/${THREADS}_threads.jar:$JARS/detector.jar \
 	-Xms1024m -Xmx1024m -Xdisableexplicitgc \
 	-Xgc:scopedMemoryMaximumSize=512m -Xgc:immortalMemorySize=512m -Xnojit -Xaot \
 	heap/Main input/quad_oscillator2 \
 	MAX_FRAMES 5000 PERSISTENT_DETECTOR_SCOPE_SIZE 50000000 TRANSIENT_DETECTOR_SCOPE_SIZE 10000000 PERIOD 10 DETECTOR_PRIORITY 20 TIME_SCALE 1 FPS 100 \
 	PRESIMULATE BUFFER_FRAMES 11000 $*


