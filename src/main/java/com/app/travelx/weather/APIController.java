package com.app.travelx.weather;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
@CrossOrigin(origins = "*")
public class APIController {

    @PostMapping(value = "/public")
    public void publicEndpoint(@RequestHeader("Authorization") String token) {
        System.out.println("All good. You DO NOT need to be authenticated to call /api/public.");
    }
}