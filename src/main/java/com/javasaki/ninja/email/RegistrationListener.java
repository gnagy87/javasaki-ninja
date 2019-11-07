package com.javasaki.ninja.email;

import com.javasaki.ninja.user.UserNinja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  @Autowired
  private EmailService emailService;

  @Qualifier("messageSource")
  @Autowired
  private MessageSource messages;

  @Autowired
  private MailSender mailSender;

  @Override
  public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    UserNinja user = event.getUser();
    VerificationToken token = emailService.getVerificationToken(user);

    String recipientAddress = user.getEmail();
    String subject = "Registration Confirmation";
    String confirmationUrl
        = event.getAppUrl() + "confirmRegistration/" + token.getToken();
    String message = messages.getMessage("message.regSucc", null, event.getLocale());

    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + " " + confirmationUrl);
    mailSender.send(email);
  }

}
