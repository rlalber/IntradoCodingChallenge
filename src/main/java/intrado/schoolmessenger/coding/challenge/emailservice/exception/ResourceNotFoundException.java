package intrado.schoolmessenger.coding.challenge.emailservice.exception;

public class ResourceNotFoundException extends Exception
{
    private static final long serialVersionUID = -9079434843631061034L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

}
