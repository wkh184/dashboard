package com.nuhs.gcto;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.*;    

@Order(2) // Filters declared at the Dispatcher initializer should be registered first
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {    
    public SecurityWebApplicationInitializer() {
        super();
    }

    // Nothing else to implement. We will just use the defaults.
    // The extended initializer class will take care of registering the Spring Security filter infrastructure.
    
}    