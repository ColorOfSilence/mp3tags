package mp3tags;

import java.io.File;

import javax.swing.JFileChooser;

public class mp3tags {
	public static void main(String[] args) {
		mp3tags();
	}
	
	public static void mp3tags() {
		/*
		 * Setup the variables what I will need to be using first
		 */
		String artist = "", album = "";
		int totTracks = 0;
		int[] tracks;
		String[] trackTitles;
		
		//This queries the user for how many tracks the album has so that it can initialize
		
		
		//This is to choose the current .txt file with the initial information to be converted to ffmpeg mp3tags
		File file = null;
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String fullPath = chooser.getSelectedFile().getAbsolutePath();
			file = new File(fullPath);
		}
	}
}
