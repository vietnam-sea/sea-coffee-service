package org.vietnamsea.authentication.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.vietnamsea.authentication.model.entity.CustomerAccountEntity;
import org.vietnamsea.authentication.repository.CustomerAccountRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final CustomerAccountRepository customerAccountRepository;
    public CustomerAccountEntity getUserAccountFromAuthentication() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) { throw new AuthenticationException("Authentication required") {}; }
            String username = auth.getName();
            return customerAccountRepository.findByUsername(username).orElseThrow();
        } catch (Exception ex) {
            throw new AuthenticationException("This user isn't authentication, please login again") {};
        }
    }
    public Optional<CustomerAccountEntity> getCurrentUserAccount() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) return Optional.empty();
            String username = auth.getName();
            return customerAccountRepository.findByUsername(username);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
