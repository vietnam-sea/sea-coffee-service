package org.vietnamsea.authentication.service.impl;

import org.vietnamsea.base_proto.GrpcTokenServiceGrpc.GrpcTokenServiceImplBase;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;
import org.vietnamsea.authentication.service.JwtService;
import org.springframework.stereotype.Service;
import org.vietnamsea.base_proto.TokenRequest;
import org.vietnamsea.base_proto.TokenValidationResponse;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Service
@RequiredArgsConstructor
public class TokenServiceImpl extends GrpcTokenServiceImplBase {
    private final JwtService jwtService;

    @Override
    public void verifyToken(TokenRequest request, StreamObserver<TokenValidationResponse> responseObserver) {
        UserClaims userClaims = null;
        switch (request.getType()) {
            case ACCESS_TOKEN:
                userClaims = jwtService.getUserClaimsFromJwt(request.getToken(), JwtTokenType.ACCESS_TOKEN);
                break;
        
            case REFRESH_TOKEN:
                userClaims = jwtService.getUserClaimsFromJwt(request.getToken(), JwtTokenType.REFRESH_TOKEN);
                break;
            default:
                break;
            
        }
    }
    
}
