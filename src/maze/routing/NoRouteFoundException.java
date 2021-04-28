package maze.routing;

public class NoRouteFoundException extends Exception
{
	public NoRouteFoundException()
	{
		super();
	}

	public NoRouteFoundException(String s)
	{
		super(s);
	}
}
