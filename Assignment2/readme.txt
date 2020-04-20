To compile assignment2.cpp :-
g++ assignment2.cpp  -o routing

To run assignment2.cpp :-
./routing -N switchportcount -B buffersize -p packetgenprob -queue INQ|KOUQ|ISLIP -K knockout -out outputfile -T maxtimeslots

Run script to generate table for varying inputs 
./run.sh

Note - This is bash script that vary input according to part of code which is being run. Comment the unwanted part of the code. 
A Comment has given explaining each section of code just uncomment the required section and comment the other section to generate output.

The output will be appended to outputfile.txt make sure the file is having only labels for the table before running the code 
for new output.
