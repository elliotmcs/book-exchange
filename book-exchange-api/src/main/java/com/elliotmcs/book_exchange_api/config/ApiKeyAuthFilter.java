// package com.elliotmcs.sample_api.config;

// import com.elliotmcs.sample_api.errors.MissingCredentialsException;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.util.matcher.RequestMatcher;

// import java.io.IOException;

// public class ApiKeyAuthFilter extends AbstractAuthenticationProcessingFilter {
//     private static final String API_SECRET_HEADER = "API-Secret";

//     public ApiKeyAuthFilter(RequestMatcher requiresAuth) {
//         super(requiresAuth);
//     }

//     @Override
//     public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
//         String apiSecret = request.getHeader(API_SECRET_HEADER);

//         if (apiSecret == null) {
//             throw new MissingCredentialsException("Missing API key or secret");
//         }

//         Authentication auth = new ApiKeyAuthenticationToken(apiSecret);
//         return getAuthenticationManager().authenticate(auth);
//     }

//     @Override
//     protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//         super.successfulAuthentication(request, response, chain, authResult);
//         chain.doFilter(request, response);
//     }
// }