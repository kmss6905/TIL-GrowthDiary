package com.example.security.config;

import com.example.security.filter.CustomAuthenticationSuccessHandler;
import com.example.security.filter.CustomLoginCheckerFilter;
import com.example.security.member.Member;
import com.example.security.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final MemberRepository memberRepository;
  private final ObjectMapper objectMapper;

  public SecurityConfig(MemberRepository memberRepository, ObjectMapper objectMapper) {
    this.memberRepository = memberRepository;
    this.objectMapper = objectMapper;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // form login disable
    http
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable);


    http
            .authorizeHttpRequests(it ->
                    it.requestMatchers(antMatcher("/login")).permitAll()
                            .anyRequest().authenticated()
            ).sessionManagement(it -> it.maximumSessions(1)
                    .maxSessionsPreventsLogin(true));

    http.addFilterBefore(customLoginCheckerFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }


  @Bean
  public CustomLoginCheckerFilter customLoginCheckerFilter() {
    CustomLoginCheckerFilter customLoginCheckerFilter = new CustomLoginCheckerFilter(objectMapper);
    customLoginCheckerFilter.setAuthenticationManager(authenticationManager());
    customLoginCheckerFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
    return customLoginCheckerFilter;
  }

  @Bean
  public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    return new CustomAuthenticationSuccessHandler(objectMapper);
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(daoAuthenticationProvider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      Member member = memberRepository.findMemberByUserId(username)
              .orElseThrow(() -> new UsernameNotFoundException("not found user : " + username));
      return new User(member.getUserId(), member.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    };
  }
}
