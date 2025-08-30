package com.app.paysim.service.impl;

import com.app.paysim.records.UserRecord;
import com.app.paysim.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Lazy
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    @Value("${app.jwt.secret.key}")
    private String tokenSignatureSecretKey;
    @Value("${app.jwt.expiration.minutes:10}")
    private int tokenExpirationInMinutes;

    @Override
    public String generateAndProvideUserAuthToken(UserRecord userRecord) {
        try {
            logger.info("Generating JWT token for user: {}", userRecord.username());
            // Header
            String header = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
            // Calculate issued at and expiration times
            long now = System.currentTimeMillis() / 1000; // Current time in seconds
            long expirationTime = now + (tokenExpirationInMinutes * 60); // *** minutes from now
            // Payload
            String payload = String.format("{\"userId\":\"%s\",\"username\":\"%s\",\"email\":\"%s\",\"iat\":%d,\"exp\":%d}",
                    userRecord.userId(), userRecord.username(), userRecord.email(), now, expirationTime);
            String encodedPayload = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(payload.getBytes());
            // Signature
            String dataToSign = header + "." + encodedPayload;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(tokenSignatureSecretKey.getBytes(), "HmacSHA256"));
            String signature = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(mac.doFinal(dataToSign.getBytes()));
            logger.info("JWT token generated successfully for user: {}", userRecord.username());
            // JWT Token
            return header + "." + encodedPayload + "." + signature;
        } catch (Exception exception) {
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    /**
     * Decode and verify a JWT token.
     * @param token The JWT token to decode.
     * @return The decoded payload as a JSON string if the token is valid.
     * @throws IllegalArgumentException if the token format is invalid or the signature does not match.
     */
    @Override
    public String decodeToken(String token) {
        try {
            // Split the JWT token into parts
            String[] tokenParts = token.split("\\.");
            if (tokenParts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT token format");
            }
            // Decode the header and payload
            String header = new String(Base64.getUrlDecoder().decode(tokenParts[0]));
            String payload = new String(Base64.getUrlDecoder().decode(tokenParts[1]));
            String providedSignature = tokenParts[2];

            // Recreate the signature using the header and payload
            String dataToSign = tokenParts[0] + "." + tokenParts[1];
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(tokenSignatureSecretKey.getBytes(), "HmacSHA256"));
            String recreatedSignature = Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(dataToSign.getBytes()));
            // Verify the signature
            if (!recreatedSignature.equals(providedSignature)) {
                throw new IllegalArgumentException("Invalid JWT signature");
            }
            // Convert the payload into a JSON object for better readability
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonPayload = objectMapper.readValue(payload, Object.class);
            // Return the JSON string representation of the payload
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonPayload);
        } catch (Exception exception) {
            throw new RuntimeException("Error decoding JWT token", exception);
        }
    }
}
