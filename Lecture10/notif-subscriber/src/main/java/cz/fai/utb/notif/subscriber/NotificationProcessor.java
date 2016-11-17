package cz.fai.utb.notif.subscriber;

import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@MessageDriven(mappedName = "notifConsumer", activationConfig
        = @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topics/notifications"))
public class NotificationProcessor implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationProcessor.class);

    @Override
    public void onMessage(Message message) {
        LOG.info("Message consumer at {}", new Date());

    }

}
