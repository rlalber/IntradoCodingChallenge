package intrado.schoolmessenger.coding.challenge.emailservice;

import intrado.schoolmessenger.coding.challenge.EmailSenderParameterResolver;
import intrado.schoolmessenger.coding.challenge.emailController.EmailControllerTests;
import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;
import intrado.schoolmessepackage intrado.schoolmessenger.coding.challenge.emailservice;

import com.google.gson.Gson;
import intrado.schoolmessenger.coding.challenge.EmailSenderParameterResolver;
import intrado.schoolmessenger.coding.challenge.emailController.EmailControllerTests;
import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;
import intrado.schoolmessenger.coding.challenge.emailservice.service.EmailSenderService;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(EmailSenderParameterResolver.class)
class EmailServiceApplicationTests
{
	@Autowired
	private final EmailSenderService emailSenderService;
	Logger logger = LogManager.getLogger(EmailServiceApplicationTests.class);

	EmailServiceApplicationTests(EmailSenderServiceImpl emailSenderService)
	{
		this.emailSenderService = emailSenderService;
	}

	@Test
	void testSendEmail_Perfect()
	{
		// Send a perfect email
		EmailMessage emailMessage = new EmailMessage("RussLAlbert@gmail.com"
				                                        ,"rlalber@somecompany.com"
				                                        ,"This is the subject line"
				                                        ,"This is the email message"
				                                        ,"03/28/2022");
		String result = emailSenderService.sendEmail(emailMessage);
		assertEquals("Success", result);
	}

	@Test
	void testArchiveEmail_BadDirConfig()
	{
	    /*
		   Ignore this test if ARCHIVE_DIR is "archive". To not ignore this test, set ARCHIVE_DIR in EmailServiceConfig.java
		   to a non-existent dir for this test be be run
	     */
        Assumptions.assumeFalse(EmailServiceConfig.ARCHIVE_DIR.equals("archive"));
		EmailMessage emailMessage = new EmailMessage("RussLAlbert@gmail.com"
				                                        ,"rlalber@gmail.com"
				                                        ,"This is the subject line for bad archive test"
				                                        ,"This is the email message for bad archive test"
				                                        ,"03/31/2022");
		String result = emailSenderService.archiveEmail(emailMessage);
		assertEquals("Exception", result.substring(0,9));
	}

	@Test
	void testEmailHistory()
	{
		String result = emailSenderService.emailHistory();
		Gson gson = new Gson();
		EmailMessage[] emailMessages = gson.fromJson(result, EmailMessage[].class);

		assertTrue(emailMessages.length > 0);
	}
}nger.coding.challenge.emailservice.service.EmailSenderService;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(EmailSenderParameterResolver.class)
class EmailServiceApplicationTests
{
	@Autowired
	private final EmailSenderService emailSenderService;
	Logger logger = LogManager.getLogger(EmailServiceApplicationTests.class);

	EmailServiceApplicationTests(EmailSenderServiceImpl emailSenderService)
	{
		this.emailSenderService = emailSenderService;
	}

	@Test
	void testSendEmail_Perfect()
	{
		EmailMessage emailMessage = new EmailMessage("RussLAlbert@gmail.com", "rlalber@gmail.com", "This is the subject line", "This is the email message", "03/28/2022");
		String result = emailSenderService.sendEmail(emailMessage);
		assertEquals("Success", result);
	}
}
