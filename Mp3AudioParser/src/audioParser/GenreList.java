package audioParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GenreList implements IConfigFile {
	private HashMap<Integer, String> genreList = new HashMap<Integer, String>();
	private String filePath;
	//constructor
	public GenreList(String filePath) {
		this.filePath = filePath;
	}
	//create a HashMap of the genre list
	public void createList() {
		BufferedReader br = null;
		InputStream is = getClass().getResourceAsStream(filePath);
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		String line;
		try {
			while ((line = br.readLine()) != null)
			{
			    String[] parts = line.split("	", 2);
			        genreList.put(Integer.parseInt(parts[0]), parts[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//get the genre id from the Hashmap accroding to the genre name
	public int getGenreIndex(String genreName) {
		for(Map.Entry<Integer, String> entry: genreList.entrySet()) {
			if(genreName.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return -1;
	}
	//get the genre name from the Hashmap accroding to the genre id
	public String getGenreName(int genreIndex) {
		if(genreList.containsKey(genreIndex)) {
			return genreList.get(genreIndex);
		}
		return "Unknown";
	}
}
