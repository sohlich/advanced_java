package cz.fai.utb.mail.client;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

/**
 *
 * @author Frantisek Spacek
 */
@Stateless
public class EmailClient {

    private JMSContext context;
    private Destination mailQueue;

    @Inject
    public void setContext(JMSContext context) {
        this.context = context;
    }

    @Resource(mappedName = "java:/jms/queue/mail")
    public void setMailQueue(Destination mailQueue) {
        this.mailQueue = mailQueue;
    }

    public void sendMessage(EmailMessage message) {
        context.createProducer().send(mailQueue, message);
    }
}
