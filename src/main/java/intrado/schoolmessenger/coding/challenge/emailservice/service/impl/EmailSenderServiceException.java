package intrado.schoolmessenger.coding.challenge.emailservice.service.impl;

public class EmailSenderServiceException extends Exception
{
    private static final long serialVersionUID = -470180507998010368L;

    public EmailSenderServiceException()
    {
        super();
    }

    public EmailSenderServiceException(final String message)
    {
        super(message);
    }
}
