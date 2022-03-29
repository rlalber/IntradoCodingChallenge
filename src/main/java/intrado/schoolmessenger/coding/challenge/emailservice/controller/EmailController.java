package intrado.schoolmessenger.coding.challenge.emailservice.controller;

import intrado.schoolmessenger.coding.challenge.emailservice.exception.BadRequestException;
import intrado.schoolmessenger.coding.challenge.emailservice.pojo.EmailMessage;
import intrado.schoolmessenger.coding.challenge.emailservice.service.EmailSenderService;
import intrado.schoolmessenger.coding.challenge.emailservice.service.impl.EmailSenderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;

@RestController
@Validated
public class EmailController
{
    private final EmailSenderService emailSenderService;
    private Logger logger = LogManager.getLogger(EmailSenderServiceImpl.class);

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    // Prevent 500 Server Errors normally caused by contstraint violation exceptions,
    // clean up date regexp error, and return 400 Bad Request error instead
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e)
    {
        if (e.getMessage().contains("sendEmail.date: must match"))
            return new ResponseEntity<>("Request not valid due to validation error: date format must have 2 digit day, 2 digit month, and 4 digit year separated by /", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Request not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestHeader("To") @Email String to
                                   ,@RequestHeader("Subject") @NotEmpty String subject
                                   ,@RequestHeader("From") @Email String from
                                   ,@RequestHeader("DateSent") @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$") String date
                                   ,@RequestBody @NotEmpty String message) throws BadRequestException
    {
        logger.info(String.format("<=== Entered EmailController.sendEmail() Headers are: to=%s, from=%s, subject='%s', date=%s, message='%s'", to, from, subject, date, message));

        EmailMessage emailMessage = new EmailMessage(to, from, subject, message, date);
        this.emailSenderService.sendEmail(emailMessage);

        logger.info("<=== Exiting EmailController.sendEmail()");

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/email-history")
    public ResponseEntity emailHistory( )
    {
        logger.info(String.format("<=== Entered EmailController.emailHistory()"));

        String result = this.emailSenderService.emailHistory();

        logger.info("<=== Exiting EmailController.emailHistory()");

        return ResponseEntity.ok(result);
    }
}