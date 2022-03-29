package intrado.schoolmessenger.coding.challenge.emailservice.service.impl;

import com.google.gson.Gson;
import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;
import intrado.schoolmessenger.coding.challenge.emailservice.service.EmailSenderService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Service
public class EmailSenderServiceImpl implements EmailSenderService
{
    private final String ARCHIVE_DIR = "archive";
    private final String ARCHIVE_FILE = "EmailMessage";
    private final String ARCHIVE_TYPE = "json";
    private final String FROM = "rlalber1@gmail.com";
    private final JavaMailSender mailSender;
    private Logger logger = LogManager.getLogger(EmailSenderServiceImpl.class);

    public EmailSenderServiceImpl(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    @Override
    public String sendEmail(EmailMessage emailMessage)
    {
        String result = "Success";
        logger.info(String.format("<=== Entered EmailSenderSErviceImpl.sendEmail() emailMessage=%s", emailMessage.toString()));

        try
        {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(emailMessage.getTo());
            simpleMailMessage.setSubject(emailMessage.getSubject());
            simpleMailMessage.setText(emailMessage.getMessage());
            simpleMailMessage.setFrom(FROM);

            this.mailSender.send(simpleMailMessage);
            archiveEmail(emailMessage);
        }
        catch (Exception e)
        {
            logger.info(String.format("<=== In EmailSenderServiceImpl.sendEmail() Execption occurred: %s", e.getMessage()));
            result = String.format("Exception: %s", e.getMessage());
        }

        logger.info("<=== Exited EmailSenderSErviceImpl.sendEmail()");

        return result;
    }

    private void archiveEmail(EmailMessage emailMessage)
    {
        logger.info("<=== Entered EmailSenderServiceImpl.archiveEmail()");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = String.format("%s\\%s.%s.%s", ARCHIVE_DIR, ARCHIVE_FILE, timestamp.getTime(), ARCHIVE_TYPE);
        Gson gson = new Gson();
        String emailMessageJsonStr = gson.toJson(emailMessage);

        logger.info(String.format("<=== In EmailSenderServiceImple.archiveEmail() emailMessageJsonStr=%s", emailMessageJsonStr));
        logger.info(String.format("<=== In EmailSenderServiceImple.archiveEmail() serializing email message to filename=%s", filename));

        try
        {
            Path filePath = Paths.get(filename);
            logger.info("<========= Path.get()="+filePath.getFileName());
            Files.write(filePath, emailMessageJsonStr.getBytes(StandardCharsets.UTF_8));
        }
        catch (java.io.IOException e)
        {
            logger.error(String.format("Error writing EmailMessage EmailMessage to json file '%s', exception is: %s", filename, e.getMessage()));
        }

        logger.info("<=== Exiting EmailSenderServiceImple.archiveEmail()");
    }

    public String emailHistory( )
    {
        logger.info("<=== Entered EmailSenderServiceImple.meailHistory()");
        StringBuilder result = new StringBuilder("[\n");

        File f = new File(ARCHIVE_DIR);

        FilenameFilter filter = new FilenameFilter()
        {
            @Override
            public boolean accept(File f, String name)
            {
                return name.endsWith(String.format(".%s", ARCHIVE_TYPE));
            }
        };

        Gson gson = new Gson();
        String[] filenames = f.list(filter);

        for (String file: filenames)
        {
            logger.info(String.format("<=== filename=%s", file));
            String filename = String.format("%s\\%s", ARCHIVE_DIR, file);
            logger.info(String.format("<=== Full filename %s", filename));

            try
            {
                Path fileName = Path.of(filename);
                String emailMessageAsJson = Files.readString(fileName);
                result.append(String.format("    %s,\n", emailMessageAsJson));
                logger.info(String.format("<=== EmailMessage as json=%s", emailMessageAsJson));
            }
            catch (java.io.FileNotFoundException e)
            {
                logger.info(String.format("Error 2 reconstituting EmailMessage to file '%s', exception is: %s", filename, e.getMessage()));
            }
            catch (java.io.IOException e)
            {
                logger.info(String.format("Error 3 reconstituting EmailMessage to file '%s', exception is: %s", filename, e.getMessage()));
            }
        }

        logger.info("<=== Exiting EmailSenderServiceImple.meailHistory()");

        result.delete(result.length()-2, result.length()-1);  //Delete last to chars i.e. ',\n'
        result.append("]");
        logger.info(String.format("<=== Exiting EmailSenderServiceImple.meailHistory() returning:\n%s", result.toString()));
        return result.toString();
    }
}