package org.vietnamsea.authentication.service.impl;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;
import org.vietnamsea.authentication.service.JwtService;
import org.vietnamsea.base_proto.GrpcTokenServiceGrpc.GrpcTokenServiceImplBase;
import org.vietnamsea.base_proto.TokenRequest;
import org.vietnamsea.base_proto.TokenValidationResponse;
import org.vietnamsea.base_proto.UserTokenResponse;

@GrpcService
@RequiredArgsConstructor
public class TokenServiceImpl extends GrpcTokenServiceImplBase {
    private final JwtService jwtService;

    @Override
    public void verifyToken(TokenRequest request, StreamObserver<TokenValidationResponse> responseObserver) {
        UserClaims userClaims = null;
        switch (request.getType()) {
            case ACCESS_TOKEN:
                userClaims = jwtService.getUserClaimsFromJwt(request.getToken(), JwtTokenType.ACCESS_TOKEN).orElse(null);
                break;
            case REFRESH_TOKEN:
                userClaims = jwtService.getUserClaimsFromJwt(request.getToken(), JwtTokenType.REFRESH_TOKEN).orElse(null);
                break;
            default:
                responseObserver.onNext(TokenValidationResponse.newBuilder()
                .setIsValid(false)
                .setErrorMessage("Token type is's supported")
                .build());
                responseObserver.onCompleted();
                return;
        }
        TokenValidationResponse tokenRpcResponse = TokenValidationResponse.newBuilder()
        .setIsValid(true)
        .setUserInfo(UserTokenResponse.newBuilder()
                .addAllRoles(userClaims.getRoles())
            .setUsername(userClaims.getUsername())
            .setIsActive(true)
            .setIsVerified(true)
            .setType(request.getType())
        .build())
        .setErrorMessage("")
        .build();
        responseObserver.onNext(tokenRpcResponse);
    }
    
}
