package com.itbootcamp.starter.security;

import com.itbootcamp.starter.services.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Created by foooox on 14.8.17.
 */
@Configuration
@PropertySource("classpath:security.properties")
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @Value("${access_token_time}")
    private int accessTokenTime;

    @Value("${refresh_token_time}")
    private int refreshTokenTime;

    @Value("${client_id}")
    private String clientId;

    @Value("${client_secret}")
    private String clientSecret;

    @Value("${authorizedGrantTypes}")
    private String authorizedGrantTypes;

    @Value("${scopes}")
    private String scopes;

    @Value("${tokenKeyAccess}")
    private String tokenKeyAccess;

    @Value("${checkTokenAccess}")
    private String checkTokenAccess;


    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {

        configurer.userDetailsService(userDetailsService)
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
                .tokenKeyAccess(tokenKeyAccess)
                .checkTokenAccess(checkTokenAccess);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        String[] grantTypes = authorizedGrantTypes.split("[,]");
        String[] allScopes = scopes.split("[,]");
        clients.jdbc(dataSource)
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(grantTypes)
                .scopes(allScopes)
                .autoApprove(true)
                .accessTokenValiditySeconds(accessTokenTime)
                .refreshTokenValiditySeconds(refreshTokenTime);
    }

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

}

