package maze;

public class MultipleExitException extends InvalidMazeException
{
	public MultipleExitException()
	{
		super();
	}

	public MultipleExitException(String s)
	{
		super(s);
	}
}
