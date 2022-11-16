import java.io.IOException;
public class DeleteFieldException extends Exception
{
  public DeleteFieldException()
  {
    super();
  }

  public DeleteFieldException(String message)
  {
    super(message);
  }

  public DeleteFieldException(String message, Throwable cause)
  {
    super(message,cause);
  }

  public DeleteFieldException(Throwable cause)
  {
    super(cause);
  }
} // DeleteFieldException
