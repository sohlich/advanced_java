package cz.fai.utb.mail.client;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Frantisek Spacek
 */
public class EmailAttachment implements Serializable {

    private final String mimeType;
    private final String name;
    private final byte[] body;

    public EmailAttachment(String mimeType, String name, byte[] body) {
        this.mimeType = mimeType;
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.body = Objects.requireNonNull(body, "body cannot be null");
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getName() {
        return name;
    }

    public byte[] getBody() {
        return body;
    }
}
