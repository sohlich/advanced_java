package cz.fai.utb.mail.sender;

import cz.fai.utb.mail.client.EmailAttachment;
import cz.fai.utb.mail.client.EmailMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

@Stateless
public class DefaultMailSender implements MailSender {

    private Session mailSession;

    @Resource(mappedName = "java:/mail/gmail")
    public void setMailSession(Session mailSession) {
        this.mailSession = mailSession;
    }

    @Override
    public void send(EmailMessage emailMessage) throws MessagingException {
        final MimeMessage mimeMessage = new MimeMessage(mailSession);
        mimeMessage.addFrom(new InternetAddress[]{new InternetAddress("no-reply@info.com")});
        mimeMessage.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(emailMessage.getRecipient()));
        mimeMessage.setSubject(emailMessage.getSubject());

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
}
