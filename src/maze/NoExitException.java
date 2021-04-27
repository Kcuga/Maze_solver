package maze;

public class NoExitException extends InvalidMazeException
{
	public NoExitException()
	{
		super();
	}

	public NoExitException(String s)
	{
		super(s);
	}
}
