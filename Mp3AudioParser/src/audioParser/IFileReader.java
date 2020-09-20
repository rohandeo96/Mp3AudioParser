package audioParser;

import java.io.File;
import java.io.IOException;

public interface IFileReader {
	public int searchMp3(File searchDirectory, int genre) throws IOException;
	public Mp3Data getMp3Data(File file, int genre) throws IOException;
}
