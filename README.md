# IntradoCodingChallenge


The high points:
---------------------
1) In the root dir is the archive dir with contains files like EmailMessage.1648259499154.json.  Each file represents a single email that was sent.  
   The number in the name is a timestamp.  There are some of these files in the zip.
3) application.properties has gmail email config info for one if my old gmail accts.
4) Request headers and request body are validated via @RequestHeader and @RequestBody markup (in the controller) such as @Email, @NotEmpty, and @Pattern. 
5) ConstraintViolationExceptions that come from the validation in #3 above are transformed from 500 internal server errors to 400 Bad Request errors via 
   @ExceptionHandler in the controller.
6) The urls are, 1)  http://localhost:8080/send-email which is a POST and takes 4 headers From, To, Subject, and DateSent. All are required, DateSent format 
   is mm/dd/yyyy. Validation errors are json format. 2) is http://localhost:8080/email-history which has no header or parms and returns an array of json 
   objects like this: [{"to":"RussLAlbert@gmail.com","from":"rlalber@gmail.com","subject":" ","message":"This is the email message","date":"03/28/2022", . . . ]} 
   which are derived from the EmailMessage.1648259499154.json files.
7) I implemented a log4jh2 logger.


The low points:
-------------------
I couldn't complete JUnit 5 tests or mockups.  I got stuck here.  There is one trivial email service test but that's it.  I tried a lot of things to get 
controller testing done but couldn't get a clean build or had many different runtime exceptions. Probably should have stuck with JUnit 4.
