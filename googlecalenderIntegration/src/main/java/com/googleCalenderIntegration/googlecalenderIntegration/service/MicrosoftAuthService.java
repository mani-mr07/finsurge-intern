package com.googleCalenderIntegration.googlecalenderIntegration.service;

import com.azure.identity.AuthorizationCodeCredential;
import com.azure.identity.AuthorizationCodeCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MicrosoftAuthService {

    private static final String TENANT_ID = "1eb2c39b-a5de-4852-ba7e-0ac6212856d5";
    private static final String CLIENT_ID = "7f4dac42-8f5d-437d-a402-24108e5af681";
    private static final String CLIENT_SECRET = "4ti8Q~ZNnYd~TIXLX1fhMKCtPMwMYtxnT1KbEbAv";
    private static final String REDIRECT_URI = "https://app.getpostman.com/oauth2/callback"; // Set this in Azure
    private static String authorizationCode;

    // 🔹 Generate Microsoft Authorization URL
    public String getAuthorizationUrl() {
        return "https://login.microsoftonline.com/" + TENANT_ID + "/oauth2/v2.0/authorize?"
                + "client_id=" + CLIENT_ID
                + "&response_type=code"
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_mode=query"
                + "&scope=Calendars.ReadWrite offline_access"
                + "&state=12345";
    }

    // 🔹 Store authorization code received from callback
    public void setAuthorizationCode(String code) {
        authorizationCode = code;
    }

    // 🔹 Build OAuth Credential
    private AuthorizationCodeCredential getAuthCredential() {
        if (authorizationCode == null || authorizationCode.isEmpty()) {
            throw new IllegalStateException("Authorization code is not set! Authenticate first.");
        }
        return new AuthorizationCodeCredentialBuilder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .authorizationCode(authorizationCode)
                .redirectUrl(REDIRECT_URI)
                .build();
    }

    // 🔹 Authenticate with Microsoft Graph
    public GraphServiceClient<Request> getGraphClient() {

        AuthorizationCodeCredential credential = getAuthCredential();

        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(
                List.of("https://graph.microsoft.com/.default"), credential
        );

        return GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }
}
