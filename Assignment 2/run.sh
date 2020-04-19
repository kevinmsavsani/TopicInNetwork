#!/bin/bash

g++ assignment2.cpp -o routing

queue_type=(INQ KOUQ iSLIP)

# for t in "${queue_type[@]}"
# do 
#     for i in 2 3 4
#     do 
#         ./routing -N 8 -B $i -p 0.5 -queue $t -K 4.8 -out outputfile -T 10000
#     done

# done


for t in "${queue_type[@]}"
do 
    for a in 8 16 32 64 128 256 512 1024
    do 
        ./routing -N $a -B 4 -p 0.5 -queue $t -K 4.8 -out outputfile -T 10000  
        # increment the value 
        # a=`expr $a * 2` 
    done 
done


