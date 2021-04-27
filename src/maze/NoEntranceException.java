package maze;

public class NoEntranceException extends InvalidMazeException
{
	public NoEntranceException()
	{
		super();
	}

	public NoEntranceException(String s)
	{
		super(s);
	}
}
