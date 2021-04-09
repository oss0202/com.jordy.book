package com.jordy.books.springboot.config.auth;

import com.jordy.books.springboot.config.auth.dto.OAuthAttributes;
import com.jordy.books.springboot.config.auth.dto.SessionUser;
import com.jordy.books.springboot.domain.user.User;
import com.jordy.books.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 1)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // 2)

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); // 3)

        User user = saveOrUpdateEvent(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); // 4)

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                        ,attributes.getAttributes()
                        ,attributes.getNameAttributeKey());
    }

    public User saveOrUpdateEvent(OAuthAttributes attrebutes){
        User user = userRepository.findByEmail(attrebutes.getEmail())
                .map(entity ->entity.update(attrebutes.getName(), attrebutes.getPicture()))
                .orElse(attrebutes.toEntity());
        return userRepository.save(user);
    }
}