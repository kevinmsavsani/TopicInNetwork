#!/bin/bash

g++ assignment2.cpp -o routing

queue_type=(INQ KOUQ iSLIP)


# Varies Type of Queue and Buffer Size keeping other things constant and Generated output is appended in outputfile.txt

for t in "${queue_type[@]}"
do 
    for i in 2 3 4
    do 
        ./routing -N 8 -B $i -p 0.5 -queue $t -K 0.3 -out outputfile -T 10000
    done

done

# Varies Type of Queue and Value of K keeping other things constant and Generated output is appended in outputfile.txt

# for t in "${queue_type[@]}"
# do 
#     for i in 0.6 0.8 1
#     do 
#         ./routing -N 8 -B 4 -p 0.5 -queue $t -K $i -out outputfile -T 10000
#     done

# done


# Varies Type of Queue and Value of N keeping other things constant and Generated output is appended in outputfile.txt


# for t in "${queue_type[@]}"
# do 
#     for a in 8 16 32 64 128 256 
#     do 
#         ./routing -N $a -B 4 -p 0.5 -queue $t -K 0.6 -out outputfile -T 10000   
#     done 
# done


