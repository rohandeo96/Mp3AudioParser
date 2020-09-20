package audioParser;

public class Mp3Data {
	private String title;
	private String interpreter;
	private String genreName;
	private String fileName;
	
	public Mp3Data(String title, String interpreter, String genreName, String fileName) {
		this.title = title;
		this.interpreter = interpreter;
		this.genreName = genreName;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(String interpreter) {
		this.interpreter = interpreter;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
