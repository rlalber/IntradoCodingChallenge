package intrado.schoolmessenger.coding.challenge.emailController;

import intrado.schoolmessenger.coding.challenge.EmailSenderParameterResolver;
import intrado.schoolmessenger.coding.challenge.emailservice.controller.EmailController;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailControllerTests.class)
//@ExtendWith(EmailSenderParameterResolver.class)
public class EmailControllerTests
{
    @Autowired
    private TestRestTemplate restTemplate;
    Logger logger = LogManager.getLogger(EmailControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSendEmail_Perfect() throws Exception
    {
        final String baseUrl = "http://localhost:8080/send-email";
        URI uri = new URI(baseUrl);
        //Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");

        HttpHeaders headers = new HttpHeaders();
        headers.set("To", "RussLAlbert@gmail.com");
        headers.set("From", "rlalber1@gmail.com");
        headers.set("Subject", "This is the email subject");
        headers.set("DateSent", "03/31/2022");
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> request = new HttpEntity<>("This is the body of the email message", headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        logger.info("<=== In CONTROLLER TEST: result.status=%s, result.toString()=%s", result.getStatusCode(), result.toString());

        //Assert.assertEquals(201, result.getStatusCode);
    }
}