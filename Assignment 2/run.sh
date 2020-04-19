#!/bin/bash

g++ assignment2.cpp -o routing

queue_type=(INQ KOUQ iSLIP)

for t in "${queue_type[@]}"
do 
    for i in 2 3 4
    do 
        ./routing -N $i -B 4 -p 0.5 -queue $t -K 4.8 -out outputfile -T 10000
    done

done

