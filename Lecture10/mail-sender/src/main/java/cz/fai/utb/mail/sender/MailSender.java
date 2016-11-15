/*
 */
package cz.fai.utb.mail.sender;

import javax.mail.MessagingException;

/**
 *
 * @author František Špaček
 */
public interface MailSender {

    void sendEmail(EmailMessage emailMessage) throws MessagingException;
}
