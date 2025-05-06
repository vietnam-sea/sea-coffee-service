package org.vietnamsea.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vietnamsea.authentication.repository.CustomerAccountRepository;
import org.vietnamsea.authentication.util.AuthUtils;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailServiceImpl implements UserDetailsService {
    private final CustomerAccountRepository customerAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username cannot be null");
        }
        var account = customerAccountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return User.builder()
                .username(account.getUsername())
                .password(account.getHashPassword())
                .authorities(AuthUtils.convertRoleToAuthority(account))
                .disabled(!account.isEnabled())
                .accountLocked(account.isLocked())
                .build();
    }
    
}
