/*
 */
package cz.fai.utb.mail.sender;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class DefaultMailSender implements MailSender {

    private Session mailSession;

    @Resource(mappedName = "java:jboss/mail/gmail")
    public void setMailSession(Session mailSession) {
        this.mailSession = mailSession;
    }

    @Override
    public void sendEmail(EmailMessage emailMessage) throws MessagingException {
        final MimeMessage mimeMessage = new MimeMessage(mailSession);
        mimeMessage.addFrom(new InternetAddress[]{new InternetAddress("no-reply@info.com")});
        mimeMessage.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(emailMessage.getRecipient()));
        mimeMessage.setSubject(emailMessage.getSubject());
        mimeMessage.setText(emailMessage.getText());
        Transport.send(mimeMessage);
    }

}
