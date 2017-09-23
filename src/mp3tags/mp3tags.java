package mp3tags;

import java.io.File;

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
		int[] tracks;
		String[] trackTitles;
		String[] VariousArtists;//If album has various artists use this array of strings to get the artists for the titles
		
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
		
		//Writer set up to write to file and save it as an .sh to be executable
	}
}
