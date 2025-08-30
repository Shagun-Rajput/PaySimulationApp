package com.app.paysim.controller;


import com.app.paysim.model.ApiResponse;
import com.app.paysim.records.UserRecord;
import com.app.paysim.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.app.paysim.constant.ApiURIs.URI_USER_AUTH_GEN;
import static com.app.paysim.constant.Constants.INT_200;
import static com.app.paysim.constant.Constants.MSG_USER_AUTH_TOKEN_SUCCESS;

@RestController
public class UserAuthController {
    private final UserService userService;
    public UserAuthController(@Lazy UserService userService) {
        this.userService = userService;
    }
    /**
     * Endpoint to generate and provide a user authentication token.
     * Note:
     *  - This endpoint is publicly accessible and does not require prior authentication.
     *  - It accepts user details in the request body and returns an authentication token upon successful validation.
     *  - Currently user data management  is not tehre, only generating jwt token as per user provide info only.
     * @param userRecord The user details required for authentication.
     * @return A ResponseEntity containing the API response with the auth token or error message.
     */
    @PostMapping(URI_USER_AUTH_GEN)
    public ResponseEntity<ApiResponse> generateAndProvideUserAuthToken(@RequestBody UserRecord userRecord){
        return ResponseEntity.ok(new ApiResponse(
                MSG_USER_AUTH_TOKEN_SUCCESS,
                userService.generateAndProvideUserAuthToken(userRecord),
                INT_200,
                null));

    }
    /**
     * User registration endpoint.
     * - This endpoint allows new users to register by providing their details.
     */
}
