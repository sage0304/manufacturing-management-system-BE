package com.manufacturing.manufacturingmanagementsystem.service;

import com.manufacturing.manufacturingmanagementsystem.dtos.responses.IntrospectResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.InvalidatedTokenRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Roles.RolesServices;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JwtService {

    private final String SIGNER_KEY = "Cf3X07omDRzLIp2hYuvrBmZ5vGlIcge12VEllyTdD1Q";
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final RolesServices rolesServices;
    public String generateToken(UsersEntity User) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(User.getEmail())
                .issueTime(new Date())
                .claim("role",rolesServices.getRoleNameById(User.getId()))
                .claim("userId", User.getId())
                .expirationTime(new Date(new Date().getTime() + 5 * 60 * 1000))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(String token) throws AppException {
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (Exception e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    public SignedJWT verifyToken(String token) throws Exception {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expirationTime.after(new Date())))
            throw new Exception();

        String jit = signedJWT.getJWTClaimsSet().getJWTID();

        if (invalidatedTokenRepository.existsById(jit))
            throw new Exception();

        return signedJWT;
    }
}
