package intrado.schoolmessenger.coding.challenge.emailservice.service;

import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;

public interface EmailSenderService
{
    String sendEmail (EmailMessage emailMessage);
    String emailHistory ();
}