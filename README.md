# Mp3AudioParser
Mp3 ID3v1 tag parser
README

------------------------
Command Line Arguments:
------------------------
Argument 1: Path of the root directory in which the ".mp3" files should be searched.
Argument 2 (Optional): Name of the Genre to be searched.
------------------------
Important Note:
------------------------
1.	Argument 1 is assumed to be an absolute path of the root directory.
2.	The “genrelist.txt” should be in the project directory as it contains the genre list.
3.  Mp3 files with ID3v1 tag will be detected.
------------------------
Sample Input on Command Prompt
------------------------
1.	java -jar Mp3AudioParserRunnable.jar F:\Songs
2.	java -jar Mp3AudioParserRunnable.jar F:\Songs Jazz
