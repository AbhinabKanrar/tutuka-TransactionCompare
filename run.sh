#!/usr/bin/env bash

java -Dserver.port=9090 -Dcsv.separator=, -Dheap.location=/tmp/ -Dtime.out=1 -Dmin.thread.pool=2 -Dmax.thread.pool=5 -Dmax.queue.capacity=500 -jar TransactionCompare-web-1.0.war
