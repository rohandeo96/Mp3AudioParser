package audioParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;

public class Mp3 implements IFileReader {
	private IConfigFile config;
	//constructor
	public Mp3(IConfigFile config) {
		this.config = config;
	}
	//Search for Mp3 files with ID3v1 Tag through the root directory and its sub-directories
	public int searchMp3(File searchDirectory, int genre) throws IOException {
		Queue<File> queue = new LinkedList<>();
		queue.add(searchDirectory);
		RandomAccessFile randomFile=null;
		int counter = 0;
		Mp3Data mp3Data;
		while(!queue.isEmpty()) {
			File current = queue.poll();
			File[] listofFilesandDirectories = current.listFiles();
			if(listofFilesandDirectories != null) {
				for(File file : listofFilesandDirectories) {
					if(file.isDirectory()) {
						queue.add(file);
					}
					else {
						if(file.getName().endsWith(".mp3")) {
							try {
								randomFile = new RandomAccessFile(file, "r");
							} catch (FileNotFoundException e) {
								e.printStackTrace();
								System.out.println("There was a problem with to access the file "+file.getName());
								continue;
							}
							randomFile.seek(randomFile.length()-128);
							byte[] byteArray = new byte[3];
							randomFile.read(byteArray);
							String tag = new String(byteArray);
							if(tag.equals("TAG")) {
								mp3Data = getMp3Data(file, genre);
								if(mp3Data != null) {
									printResult(mp3Data);
								}
								counter++;
							}
							randomFile.close();
						}
					}
				}
			}
		}
		return counter;
	}
	
	//read the metadata of the Mp3 file
	public Mp3Data getMp3Data(File file, int genre) throws IOException {
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String title = null;
		String interpreter = null;
		String genreName = null;
		int genreIndex;
		Mp3Data mp3Data;
		byte[] byteArray;
		if(!(genre == -1)) {
			randomFile.seek(randomFile.length()-1);
			byte[] filegenre = new byte[1];
			randomFile.read(filegenre);
			int genreOfFile = filegenre[0];
			if(genre==genreOfFile) {
				randomFile.seek(randomFile.length() - 125);
				byteArray = new byte[30];
	            randomFile.read(byteArray);
	            title = new String(byteArray);
	            randomFile.read(byteArray);
	            interpreter= new String(byteArray);
	            genreName = config.getGenreName(genre);
	            mp3Data = new Mp3Data(title, interpreter, genreName, file.getName());
	            return mp3Data;
			}
		}
		else {
			randomFile.seek(randomFile.length() - 125);
			byteArray = new byte[30];
	        randomFile.read(byteArray);
	        title = new String(byteArray);
	        randomFile.read(byteArray);
	        interpreter= new String(byteArray);
	        randomFile.seek(randomFile.length()-1);
	        byteArray = new byte[1];
	        randomFile.read(byteArray);
	        genreIndex = byteArray[0];
	        genreName = config.getGenreName(genreIndex);
	        mp3Data = new Mp3Data(title, interpreter, genreName, file.getName());
	        return mp3Data;
		}
		randomFile.close();
		return null;
	}
	
	//Print the metadata
	public void printResult(Mp3Data mp3Data) {
		String format = "%-40s%s-%s (%s)%n";
		System.out.printf(format, mp3Data.getGenreName(), mp3Data.getInterpreter(), mp3Data.getTitle(), mp3Data.getFileName());
	}
}
