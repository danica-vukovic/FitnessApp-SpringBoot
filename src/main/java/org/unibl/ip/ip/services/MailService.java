package org.unibl.ip.ip.services;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}
