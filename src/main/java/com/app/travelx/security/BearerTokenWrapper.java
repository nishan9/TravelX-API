package com.app.travelx.security;

import org.json.JSONObject;

import java.util.Base64;

public class BearerTokenWrapper {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSub(){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.getString("sub");
    }

}
