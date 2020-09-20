package audioParser;

import java.io.File;
import java.io.IOException;

public class Mp3AudioParser {
	//main function
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Please pass a directory name as argument");
		}
		else if(args.length > 2) {
			System.out.println("Not a valid input. The program can have maximum two arguments.");
		}
		else {
			String checkDirectory = args[0];
			String searchGenre = null;
			int genreIndex, counter=0;
			GenreList genrelist = new GenreList("genrelist.txt");
			File rootDirectory = new File(checkDirectory);
			//check if the directory exists
			if(!rootDirectory.exists()) {
				System.out.println("The specified directory does not exist!");
				System.exit(0);
			}
			genrelist.createList();
			Mp3 mp3Reader = new Mp3(genrelist);
			//if second argument i.e. the genre name to be searched is present then get the id from genre list 
			if(args.length == 2) {
				searchGenre = args[1];
				if(!searchGenre.isEmpty()) {
					genreIndex = genrelist.getGenreIndex(searchGenre);
					if(genreIndex == -1) {
						System.out.println("The mentioned genre does not exist!");
						System.exit(0);
					} 
					else
						try {
							counter = mp3Reader.searchMp3(rootDirectory, genreIndex);
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
			else {
				try {
					counter = mp3Reader.searchMp3(rootDirectory, -1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(counter == 0) {
				System.out.println("No mp3 files with ID3v1 tag found");
			}
		}		
	}
}
