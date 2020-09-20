package audioParser;

public interface IConfigFile {
	public void createList();
	public int getGenreIndex(String genreName);
	public String getGenreName(int genreIndex); 
}
