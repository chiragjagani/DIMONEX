//package com.stpl.dimonex.config;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UrlPathHelper;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    // This method will be called after successful login
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        // Get the user's details (username, roles, etc.)
//        User user = (User) authentication.getPrincipal();
//        
//        // Retrieve the user ID and roles (assuming user details have the user ID)
//        String userId = user.getUsername();  // You may want to retrieve the actual user ID from the database
//        
//        // Determine the roles of the user
//        String role = user.getAuthorities().toString();
//        
//        // Based on role, we dynamically redirect the user to the correct dashboard
//        if (role.contains("ROLE_MANAGER")) {
//            // Redirect to Manager dashboard with user ID
//            response.sendRedirect("/manager/dashboard/" + userId);
//        } else if (role.contains("ROLE_POLISHER")) {
//            // Redirect to Polisher dashboard with user ID
//            response.sendRedirect("/polisher/dashboard/" + userId);
//        } else if (role.contains("ROLE_ADMIN")) {
//            // Redirect to Admin dashboard with user ID
//            response.sendRedirect("/admin/dashboard/" + userId);
//        } else {
//            // Default redirect in case the role is not recognized
//            response.sendRedirect("/login?error=unrecognized_role");
//        }
//    }
//}
