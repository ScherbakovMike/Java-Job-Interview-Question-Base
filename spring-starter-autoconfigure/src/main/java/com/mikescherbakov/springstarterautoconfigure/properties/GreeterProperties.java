package com.mikescherbakov.springstarterautoconfigure.properties;

import lombok.*;
import org.springframework.boot.context.properties.*;

@ConfigurationProperties(prefix = "greeter.service")
@Getter
@Setter
public class GreeterProperties {

    private boolean enabled = false; // default

    /**
     * The name of the user to greet.
     */
    private String userName = "World";

    /**
     * The salutation to use in the greeting.
     */
    private String salutation = "Hello";

    /**
     * The suffix to add to the greeting.
     */
    private String suffix = "!";
}
