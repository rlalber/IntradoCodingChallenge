package intrado.schoolmessenger.coding.challenge.emailservice.pojo;

import java.io.Serializable;

public class EmailMessage implements Serializable
{
    private String to;
    private String from;
    private String subject;
    private String message;
    private String date;

    public EmailMessage(String to, String from, String subject, String message, String date)
    {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) { this.message = message; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString()
    {
       return String.format("EmailMessage: To=%s, From=%s, Date=%s, Subject=%s, Message=%s", to, from, date, subject, message);
    }
}