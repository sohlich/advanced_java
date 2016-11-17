package cz.fai.utb.mail.client;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Frantisek Spacek
 */
public class EmailMessage implements Serializable {

    private final String subject;
    private final String text;
    private final String recipient;
    private final List<EmailAttachment> attachments;

    public EmailMessage(String subject, String text, String recipient, List<EmailAttachment> attachments) {
        this.subject = subject;
        this.text = text;
        this.recipient = recipient;
        this.attachments = attachments;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getRecipient() {
        return recipient;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }
    
}
