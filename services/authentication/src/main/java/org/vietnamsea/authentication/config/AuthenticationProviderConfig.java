package org.vietnamsea.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.vietnamsea.authentication.service.impl.CustomerUserDetailServiceImpl;
import org.vietnamsea.authentication.service.impl.PartnerUserDetailServiceImpl;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderConfig implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final CustomerUserDetailServiceImpl customerUserDetailService;
    private final PartnerUserDetailServiceImpl partnerUserDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        var password = authentication.getCredentials().toString();
        Optional<UserDetails> userDetails = Optional.empty();
        if(username.startsWith("customer_")) {
            var user = customerUserDetailService.loadUserByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                userDetails = Optional.of(user);
            }
        }else if (username.startsWith("partner_")) {
            var user = partnerUserDetailService.loadUserByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                userDetails = Optional.of(user);
            }
        } else if (username.startsWith("system_")) {
            var user = partnerUserDetailService.loadUserByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                userDetails = Optional.of(user);
            }
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        if (userDetails.isPresent()) {
            return new UsernamePasswordAuthenticationToken(userDetails.get(), password, userDetails.get().getAuthorities());
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
