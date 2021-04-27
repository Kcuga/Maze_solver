package maze;

public class InvalidMazeException extends Exception
{
	public InvalidMazeException()
	{
		super();
	}

	public InvalidMazeException(String s)
	{
		super(s);
	}
}