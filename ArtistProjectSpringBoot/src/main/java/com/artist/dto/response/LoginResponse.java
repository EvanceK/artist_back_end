package com.artist.dto.response;

public class LoginResponse {
    private String token;
    private String nickName;
    private String customerId;
    
    public LoginResponse(String token, String nickName) {
        this.token = token;
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public String getNickName() {
        return nickName;
    }

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}
    
}