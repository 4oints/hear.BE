package com.heardot.config.auth.dto;

import com.heardot.domain.member.Member;
import com.heardot.domain.member.constant.SocialType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private SocialType socialType;
    private String password;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, SocialType socialType, String password) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.socialType = socialType;
        this.password = password;
    }

}
