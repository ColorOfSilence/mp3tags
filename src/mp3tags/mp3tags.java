package mp3tags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class mp3tags {
	public static void main(String[] args) {
		mp3tags();
		System.out.println("===End Program===");
		System.exit(42);
	}
	
	public static void mp3tags() {
		/*
		 * Setup the variables what I will need to be using first
		 */
		String artist = "", album = "";
		int totTracks = 0;
		int[] tracks = null;
		String[] trackTitles = null;
		String[] variousArtists;//If album has various artists use this array of strings to get the artists for the titles
		boolean hasVA = false;
		
		//This queries the user for how many tracks the album has so that it can initialize
		/* CURRENTLY GONNA TRY AND NOT QUERY THE USER*/
		
		//Choose the current .txt file with the initial information to be converted to ffmpeg mp3tags
		File file = null;
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String fullPath = chooser.getSelectedFile().getAbsolutePath();
			file = new File(fullPath);
		}
		
		//Scanner set up to read the file and populate the needed variables
		try {
			Scanner reader = new Scanner(file);
			String[] trackArray;
			
			while(reader.hasNextLine()) {
				artist = reader.nextLine(); //Artist would be the first line to read
				album = reader.nextLine(); //Album is the following line
				totTracks = Integer.parseInt(reader.nextLine()); //totTracks is the counter that delegates the loops
				if(artist.equals("Various Artists")) { //if it is VA then it needs to follow this format
					tracks = new int[totTracks];
					trackTitles = new String[totTracks];
					variousArtists = new String[totTracks];
					for(int i = 0; i < totTracks; i++) {
						trackArray = reader.nextLine().split("_");
						tracks[i] = Integer.parseInt(trackArray[0]);
						trackTitles[i] = trackArray[1];
						variousArtists[i] = trackArray[2];
					}
				}
				tracks = new int[totTracks];
				trackTitles = new String[totTracks];
				variousArtists = new String[totTracks];
				for(int i = 0; i < totTracks; i++) {
					trackArray = reader.nextLine().split("_");
					tracks[i] = Integer.parseInt(trackArray[0]);
					trackTitles[i] = trackArray[1];
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Writer set up to write to file and save it as an .sh to be executable
		try {
			PrintWriter writer = new PrintWriter(file);
			/*
			 * tracks[i],trackTitles[i],artist,album,track[i],totTracks,tracks[i],tracks[i],tracks[i],artist,trackTitles[i]
			 */
			String menu = "ffmpeg -i %d.m4a -metadata title=\"%s\" "
					+ "-metadata artist=\"%s\" -metadata album=\"%s\" "
					+ "-metadata track=\"%d/%d\" %d.mp3" + "\n\n" +
					"ffmpeg -i %d.mp3 -i cover.jpg -map 0:0 -map 1:0 -acodec copy \\-id3v2_version 3 "
					+ "\"%d %s - %s\".mp3" + "\n\n";
			
			for(int i = 0; i < totTracks; i++) {
				writer.printf(menu, tracks[i],trackTitles[i],artist,album,tracks[i],totTracks,tracks[i],tracks[i],tracks[i],artist,trackTitles[i]);
			}
			writer.print("mv *.m4a \"mp3 tags and m4a files\"\n");
			for(int i = 0; i < totTracks; i++) {
				writer.print("rm " + (i + 1) + ".mp3\n");
			}
			writer.print("mv *.sh \"mp3tags and m4a files\"");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.renameTo(new File(file.getParentFile(), "mp3tags.sh"));
	}
}
