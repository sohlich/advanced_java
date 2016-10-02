package cz.sohlich.commons.impl;

import com.auth0.jwt.JWTSigner;
import cz.sohlich.commons.TokenFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Radomir Sohlich on 9/25/16.
 */
public class JwtTokenFactory implements TokenFactory {


    private final String secret;
    private Map<String, Object> claims = new HashMap<String, Object>();


    public JwtTokenFactory(String secret) {
        this.secret = secret;
    }

    public JwtTokenFactory addContent(String key, Object value) {
        claims.put(key, value);
        return this;
    }

    @Override
    public String generateToken() {
        final JWTSigner signer = new JWTSigner(secret);
        return signer.sign(claims);
    }
}
