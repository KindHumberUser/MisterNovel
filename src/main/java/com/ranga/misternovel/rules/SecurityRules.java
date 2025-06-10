/*
 * Created by Sagar Ranga on 2025.6.10
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.rules;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public interface SecurityRules {
    void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry);
}
