// package com.elliotmcs.sample_api.config;

// import org.springframework.security.authentication.AbstractAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;

// import java.util.Collection;

// public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
//     private final String apiSecret;

//     public ApiKeyAuthenticationToken(String apiSecret) {
//         super(null);
//         this.apiSecret = apiSecret;
//         setAuthenticated(false);
//     }

//     public ApiKeyAuthenticationToken(String apiSecret, Collection<? extends GrantedAuthority> authorities) {
//         super(authorities);
//         this.apiSecret = apiSecret;
//         super.setAuthenticated(true);
//     }

//     @Override
//     public Object getCredentials() {
//         return this.apiSecret;
//     }

//     @Override
//     public Object getPrincipal() {
//         return null;
//     }

//     @Override
//     public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//         if (isAuthenticated) {
//             throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
//         }
//         super.setAuthenticated(false);
//     }

//     @Override
//     public void eraseCredentials() {
//         super.eraseCredentials();
//     }
// }