package intrado.schoolmessenger.coding.challenge.emailservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import intrado.schoolmessenger.coding.challenge.emailservice.controller.EmailController;
import intrado.schoolmessenger.coding.challenge.emailservice.service.EmailSenderService;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.assertj.core.api.ErrorCollector;
//import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;

//@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(EmailController.class)
public class EmailServiceImplTest
{
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Mock
    private EmailSenderService emailSenderService;
    //private EmailSenderServiceImpl emailSenderService = mock(EmailSenderService.class);
    //private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailController emailController;

    @BeforeAll
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }
}