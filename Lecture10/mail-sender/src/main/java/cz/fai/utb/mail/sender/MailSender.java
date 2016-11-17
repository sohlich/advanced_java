package cz.fai.utb.mail.sender;

import cz.fai.utb.mail.client.EmailMessage;
import javax.mail.MessagingException;

/**
 *
 * @author Frantisek Spacek
 */
public interface MailSender {

    void send(EmailMessage emailMessage) throws MessagingException;
}
