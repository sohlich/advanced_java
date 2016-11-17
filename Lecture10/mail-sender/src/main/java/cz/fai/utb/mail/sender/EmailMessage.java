/*
 */
package cz.fai.utb.mail.sender;

/**
 *
 * @author František Špaček
 */
public class EmailMessage {
    
    private String subject;
    private String text;
    private String recipient;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    
}
