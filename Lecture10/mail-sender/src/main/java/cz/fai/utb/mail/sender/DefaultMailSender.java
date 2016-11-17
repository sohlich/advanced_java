<<<<<<< HEAD
package cz.fai.utb.mail.sender;

import cz.fai.utb.mail.client.EmailAttachment;
import cz.fai.utb.mail.client.EmailMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
=======
/*
 */
package cz.fai.utb.mail.sender;

>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
<<<<<<< HEAD
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
=======
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef

@Stateless
public class DefaultMailSender implements MailSender {

    private Session mailSession;

<<<<<<< HEAD
    @Resource(mappedName = "java:/mail/gmail")
=======
    @Resource(mappedName = "java:jboss/mail/gmail")
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
    public void setMailSession(Session mailSession) {
        this.mailSession = mailSession;
    }

    @Override
<<<<<<< HEAD
    public void send(EmailMessage emailMessage) throws MessagingException {
=======
    public void sendEmail(EmailMessage emailMessage) throws MessagingException {
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
        final MimeMessage mimeMessage = new MimeMessage(mailSession);
        mimeMessage.addFrom(new InternetAddress[]{new InternetAddress("no-reply@info.com")});
        mimeMessage.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(emailMessage.getRecipient()));
        mimeMessage.setSubject(emailMessage.getSubject());
<<<<<<< HEAD

        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(emailMessage.getText());

        final Multipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(messageBodyPart);

        for (EmailAttachment attachment : emailMessage.getAttachments()) {
            multiPart.addBodyPart(prepareAttachmentFrom(attachment));
        }
        mimeMessage.setContent(multiPart);

        Transport.send(mimeMessage);
    }

    private MimeBodyPart prepareAttachmentFrom(EmailAttachment emailAttachment) throws MessagingException {
        final MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        final DataSource source = new ByteArrayDataSource(emailAttachment.getBody(), emailAttachment.getMimeType());
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(emailAttachment.getName());
        return attachmentBodyPart;
    }
=======
        mimeMessage.setText(emailMessage.getText());
        Transport.send(mimeMessage);
    }

>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
}
