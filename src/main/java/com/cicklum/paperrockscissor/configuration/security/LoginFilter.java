package com.cicklum.paperrockscissor.configuration.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class Used to create a filter login in /login GET, so we check if the authentication it´s sucessfull with out Bean 'listUsers'
 * if Login it´s successfull we add a JWT to the Header Authentication
 */
@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final String URILOGIN = "/user/login";

    private final AuthenticationService authenticationService;


    /**
     * Create a Login Filter with Authentication Manager and Authentication Service
     * on URL_LOGIN
     * @param authManager
     * @param authenticationService
     */
    public LoginFilter(AuthenticationManager authManager, AuthenticationService authenticationService) {
        super(new AntPathRequestMatcher(URILOGIN));
        setAuthenticationManager(authManager);
        this.authenticationService = authenticationService;
    }


    /**
     * Method for Authentication
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        UserDto credentials = new ObjectMapper()
                .readValue(request.getInputStream(), UserDto.class);
        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(
                    credentials.getUserName(),
                    credentials.getPassword(),
                    Collections.emptyList()
            )
        );
    }

    /**
     * Method trigered when authentication it´s succesfull, so we add a JWT to Header Authetication
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        authenticationService.addToken(response, authResult.getName());
    }

    @Override
    protected AuthenticationFailureHandler getFailureHandler() {
        return super.getFailureHandler();
    }
}
