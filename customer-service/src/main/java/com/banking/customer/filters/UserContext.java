package com.banking.customer.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "txn-correlation-id";
    public static final String AUTH_TOKEN = "txn-auth-token";
    public static final String USER_ID = "txn-user-id";
    public static final String ORGANIZATION_ID = "txn-organization-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<String>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<String>();
    private static final ThreadLocal<String> userId = new ThreadLocal<String>();
    private static final ThreadLocal<String> organizationId = new ThreadLocal<String>();

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String corlId) {
        correlationId.set(corlId);
    }

    public static String getAuthToken() {
        return authToken.get();
    }

    public static void setAuthToken(String authenticationToken) {
        authToken.set(authenticationToken);
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String aUser) {
        userId.set(aUser);
    }

    public String getOrganizationId() {
        return organizationId.get();
    }

    public void setOrganizationId(String orgId) {
        organizationId.set(orgId);
    }

    public static HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CORRELATION_ID, getCorrelationId());

        return httpHeaders;
    }
}