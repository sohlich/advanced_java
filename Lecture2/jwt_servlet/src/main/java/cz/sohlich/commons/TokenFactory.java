package cz.sohlich.commons;

/**
 * Interface for factory that generates access token.
 * <p>
 * Created by Radomir Sohlich on 9/25/16.
 */
public interface TokenFactory {


    /**
     * Generates access token for client.
     *
     * @return access token
     */
    public String generateToken();

}
