package cz.fai.utb.mail.client;

import java.util.Collections;
import java.util.List;


public class EmailMessageBuilder {

    private String subject;
    private String text;
    private String recipient;
    private List<EmailAttachment> attachments;

    public EmailMessageBuilder() {
    }

    public EmailMessageBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailMessageBuilder text(String text) {
        this.text = text;
        return this;
    }

    public EmailMessageBuilder recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public EmailMessageBuilder withAttachments(List<EmailAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public EmailMessage build() {
        if(attachments == null){
            attachments = Collections.emptyList();
        }
        return new EmailMessage(subject, text, recipient, attachments);
    }
}
