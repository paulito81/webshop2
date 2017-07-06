package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by paulhasfjord on 10.12.2016.
 */
public class URL2Validator implements ConstraintValidator<URL2, String> {

    private String protocol;
    private String host;
    private int port;

    @Override
    public void initialize(URL2 url) {
        this.protocol = url.protocol();
        this.host = url.host();
        this.port = url.port();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.length() == 0) {
            return true;
        }

        URL url;
        try {
            url = new URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        return !(protocol != null && protocol.length() > 0 && !url.getProtocol().equals(protocol)) && !(host != null && host.length() > 0 && !url.getHost().startsWith(host)) && !(port != -1 && url.getPort() != port);
    }

}
