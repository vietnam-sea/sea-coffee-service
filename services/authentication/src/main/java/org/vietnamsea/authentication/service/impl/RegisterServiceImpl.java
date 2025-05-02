package org.vietnamsea.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vietnamsea.authentication.model.dto.request.CustomerAccountRegisterRequest;
import org.vietnamsea.authentication.repository.CustomerAccountRepository;
import org.vietnamsea.authentication.service.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final CustomerAccountRepository customerAccountRepository;

    public void customerRegister (CustomerAccountRegisterRequest request) {
        var result = customerAccountRepository.findById(request.getUsername())
                .orElseThrow(() -> new RuntimeException(""));
    }
}
