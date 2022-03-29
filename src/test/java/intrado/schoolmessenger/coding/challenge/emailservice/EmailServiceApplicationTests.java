package intrado.schoolmessenger.coding.challenge.emailservice;

import intrado.schoolmessenger.coding.challenge.EmailSenderParameterResolver;
import intrado.schoolmessenger.coding.challenge.emailController.EmailControllerTests;
import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;
import intrado.schoolmessenger.coding.challenge.emailservice.service.EmailSenderService;
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