package com.ritik.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ritik.scm.entities.Providers;
import com.ritik.scm.entities.User;
import com.ritik.scm.helpers.AppConstants;
import com.ritik.scm.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        logger.info("OAuthenticationSuccessHandler");

        var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationID = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationID.toString());

        var oAuthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oAuthUser.getAttributes().forEach((key, value) -> {
            logger.info("{} => {}", key, value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("#DEFAULT_PASSWORD@");

        // identify login provider - google, github, facebook,...
        // Google - Goolge attributes
        if (authorizedClientRegistrationID.equalsIgnoreCase("google")) {
            // google attributes
            user.setEmail(oAuthUser.getAttribute("email").toString());
            user.setProfilePic(oAuthUser.getAttribute("picture").toString());
            user.setName(oAuthUser.getAttribute("name").toString());
            user.setProviderUserId(oAuthUser.getName().toString());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This is a Google generated account.");

        } else if (authorizedClientRegistrationID.equalsIgnoreCase("github")) {
            // github attributes
            String email = oAuthUser.getAttribute("email") != null ? oAuthUser.getAttribute("email")
                    : oAuthUser.getAttribute("login").toString() + "@scm.com";

            String picture = oAuthUser.getAttribute("avatar_url").toString();
            String name = oAuthUser.getAttribute("name").toString();
            String providerUserId = oAuthUser.getName();

            user.setName(name);
            user.setEmail(email);
            user.setProfilePic(picture);
            user.setProvider(Providers.GITHUB);
            user.setProviderUserId(providerUserId);
            user.setAbout("This is a Github generated account.");

        } else {
            logger.info("Unknown Provider");
        }

        // Save the user
        User savedUser = userRepo.findByEmail(user.getEmail()).orElse(null);

        if (savedUser == null) {
            userRepo.save(user);
            logger.info("User saved:" + user.getEmail());
        }

        /*
         * logger.info(user.getName());
         * user.getAttributes().forEach((key, value) -> {
         * logger.info("{} => {}", key, value);
         * });
         * logger.info(user.getAuthorities().toString());
         * 
         * // save data to database
         * // req, res, authentication
         * String email = user.getAttribute("email").toString();
         * String name = user.getAttribute("name").toString();
         * String picture = user.getAttribute("picture").toString();
         * 
         * // create user and save to database
         * User user1 = new User();
         * user1.setName(name);
         * user1.setEmail(email);
         * user1.setProfilePic(picture);
         * user1.setPassword("#DEFAULT_PASSWORD@");
         * user1.setUserId(UUID.randomUUID().toString());
         * user1.setProvider(Providers.GOOGLE);
         * user1.setEnabled(true);
         * user1.setEmailVerified(true);
         * user1.setProviderUserId(user.getName());
         * user1.setRoleList(List.of(AppConstants.ROLE_USER));
         * user1.setAbout("This account is created using Google.");
         * 
         * User savedUser = userRepo.findByEmail(email).orElse(null);
         * 
         * if (savedUser == null) {
         * userRepo.save(user1);
         * logger.info("User saved:" + email);
         * }
         */

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

    }

}
