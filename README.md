#Author: Juan Donosa
#Last Updated: Aug 26, 2017
#Description:   Creates a .sh file that converts m4a files to mp3 files with correct tags for artist/band, album, tracks, track titles, cover

Initial .txt file containing info about files to be converted to ffmpeg tags
is in the following format:

Band
Album

1_Track title
2_Track title
....
n_Track title

#currently using _ to seperate track number from track titles

#output is a file with ffmpeg tags in an .sh file that is executable and ready to run in the background and convert, the m4a files and the .sh file are currently set to move to a folder called 'm4a files and mp3tags', can also be changed so that these files are removed; this will be done when sure that program is running correctly.

##Thinking of adding number of tracks directly to Initial .txt file following the album so that the program can use that to populate the trackNum variable without having to query the user
