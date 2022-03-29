package intrado.schoolmessenger.coding.challenge.emailservice.exception;

public class BadRequestException extends Exception
{
    private static final long serialVersionUID = -9039434849615064027L;

    public BadRequestException()
    {
        super();
    }

    public BadRequestException(final String message)
    {
        super(message);
    }
}