package intrado.schoolmessenger.coding.challenge;

import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailSenderParameterResolver implements ParameterResolver
{
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
                             throws ParameterResolutionException
    {
       return parameterContext.getParameter().getType() == EmailSenderServiceImpl.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
                             throws ParameterResolutionException
    {
        JavaMailSender jms = new JavaMailSenderImpl();
        return new EmailSenderServiceImpl(jms);
    }
}