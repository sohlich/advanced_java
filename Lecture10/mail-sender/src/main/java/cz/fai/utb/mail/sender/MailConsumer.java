package cz.fai.utb.mail.sender;

import com.google.gson.Gson;
import cz.fai.utb.mail.client.EmailMessage;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Frantisek Spacek
 */
@MessageDriven(mappedName = "mailConsumer", activationConfig
        = @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/mail"))
public class MailConsumer implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MailConsumer.class);

    private MailSender mailSender;

    @Inject
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onMessage(Message message) {
        LOG.info("Recieved new mail to sent");
        try {
            if(!(message instanceof ObjectMessage)){
                return;
            }
            final EmailMessage emailMessage = (EmailMessage) ((ObjectMessage) message).getObject();
//            final String jsonMessage = message.getBody(String.class);
//            final EmailMessage emailMessage = new Gson().fromJson(jsonMessage, EmailMessage.class);
            mailSender.send(emailMessage);
        } catch (JMSException ex) {
            LOG.error("Unable to process jms message", ex);
        } catch (MessagingException ex) {
            LOG.error("Unable to send email", ex);
        }
    }
}
