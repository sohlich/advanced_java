<<<<<<< HEAD
package cz.fai.utb.mail.sender;

import cz.fai.utb.mail.client.EmailMessage;
=======
/*
 */
package cz.fai.utb.mail.sender;

>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
import javax.mail.MessagingException;

/**
 *
<<<<<<< HEAD
 * @author Frantisek Spacek
 */
public interface MailSender {

    void send(EmailMessage emailMessage) throws MessagingException;
=======
 * @author František Špaček
 */
public interface MailSender {

    void sendEmail(EmailMessage emailMessage) throws MessagingException;
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
}
