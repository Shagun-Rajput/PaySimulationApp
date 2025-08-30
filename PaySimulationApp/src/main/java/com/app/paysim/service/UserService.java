package com.app.paysim.service;

import com.app.paysim.records.UserRecord;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public interface UserService {
    String generateAndProvideUserAuthToken(UserRecord userRecord);
    String decodeToken(String token);
}
