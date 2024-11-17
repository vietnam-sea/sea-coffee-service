package org.vietnamsea.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vietnamsea.authentication.repository.PartnerAccountRepository;

@Service
@RequiredArgsConstructor
public class PartnerUserDetailServiceImpl implements UserDetailsService {
    private final PartnerAccountRepository partnerAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
