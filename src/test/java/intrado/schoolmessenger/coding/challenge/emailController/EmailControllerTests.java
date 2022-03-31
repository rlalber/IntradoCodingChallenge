package intrado.schoolmessenger.coding.challenge.emailController;

import intrado.schoolmessenger.coding.challenge.EmailSenderParameterResolver;
import intrado.schoolmessenger.coding.challenge.emailservice.controller.EmailController;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupackage intrado.schoolmessenger.coding.challenge.emailController;

import com.fasterxml.jackson.databind.ObjectMapper;
import intrado.schoolmessenger.coding.challenge.EmailSenderParameterResolver;
import intrado.schoolmessenger.coding.challenge.emailservice.EmailServiceConfig;
import intrado.schoolmessenger.coding.challenge.emailservice.controller.EmailController;
import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;
import intrado.schoolmessenger.coding.challenge.emailservice.service.EmailSenderService;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
//@WebMvcTest(EmailController.class)
//@SpringBootTest
@SpringBootTest(classes = EmailControllerTests.class)
@AutoConfigureMockMvc
//@ExtendWith(EmailSenderParameterResolver.class)
public class EmailControllerTests
{
    private TestRestTemplate restTemplate = new TestRestTemplate();

    Logger logger = LogManager.getLogger(EmailControllerTests.class);

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private EmailSenderService emailSenderService;

    EmailMessage emailMessage_1 = new EmailMessage("RussLAlbert@gmail.com", EmailServiceConfig.FROM, "Subject for this email message_1", "This is the body of the email message_1", "05/20/2022");
    EmailMessage emailMessage_2 = new EmailMessage("RussLAlbert@gmail.com", EmailServiceConfig.FROM, "Subject for this email message_2", "This is the body of the email message_3", "05/20/2022");
    EmailMessage emailMessage_3 = new EmailMessage("RussLAlbert@gmail.com", EmailServiceConfig.FROM, "Subject for this email message_3", "This is the body of the email message_3", "05/20/2022");

    @Test
    public void getEmailHistory() throws Exception
    {
        //Mock return data from "/email-history"
        List<EmailMessage> messages = new ArrayList<>(Arrays.asList(emailMessage_1, emailMessage_2, emailMessage_3));

        //Return mock data from email sender service emailHistory() method calls
        Mockito.when(emailSenderService.emailHistory()).thenReturn(String.valueOf(messages));

        mockMvc.perform(MockMvcRequestBuilders
               .get("/email-history"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[2].to", is("RussLAlbert@gmail.com")));
    }

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
        logger.info(String.format("<=== In CONTROLLER TEST: result.status=%s, result.toString()=%s", result.getStatusCode(), result.toString()));

        //Assert.assertEquals(201, result.getStatusCode);
    }
}piter.api.Test;
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
