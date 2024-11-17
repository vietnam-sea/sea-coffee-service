package org.vietnamsea.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.vietnamsea.authentication.service.impl.CustomerUserDetailServiceImpl;
import org.vietnamsea.authentication.service.impl.PartnerUserDetailServiceImpl;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderConfig implements AuthenticationProvider {
    private final CustomerUserDetailServiceImpl customerUserDetailService;
    private final PartnerUserDetailServiceImpl partnerUserDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        var password = authentication.getCredentials().toString();
        UserDetails userDetails = null;

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
