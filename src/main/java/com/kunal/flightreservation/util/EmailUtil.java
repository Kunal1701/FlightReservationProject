package com.kunal.flightreservation.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Value("${com.kunal.flightReservation.itinerary.email.subject}")
    private String EMAIL_SUBJECT;
    @Value("${com.kunal.flightReservation.itinerary.email.body}")
    private String EMAIL_BODY;
    @Autowired
    private JavaMailSender sender;

    public void sendItinerary(String toAddress, String filePath) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toAddress);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setText(EMAIL_BODY);
            helper.addAttachment("Itinerary", new java.io.File(filePath));
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
