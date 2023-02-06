package com.courseology.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    protected String SECRET_KEY = "6351665468576D5A7134743777217A25432A462D4A614E645267556B586E3272357538782F413F4428472B4B6250655368566D59713373367639792442264529";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Get the authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }

        // Extract the JWT from the authorization header
        String jwt = authorizationHeader.substring("Bearer ".length());

        // Verify the JWT and extract the claims
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
//        Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt).getBody();

        // Check if the user has the necessary permissions
//        String role = (String) claims.get("role");
//        if (!role.equals("admin")) {
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
//            return false;
//        }

        // Store the claims in the request so that they can be used later
        request.setAttribute("claims", claims);

        return true;
    }
}