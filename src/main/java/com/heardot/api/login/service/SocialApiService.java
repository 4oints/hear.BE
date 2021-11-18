package com.heardot.api.login.service;

import com.heardot.config.auth.dto.OAuthAttributes;

public interface SocialApiService {

    OAuthAttributes getUserInfo(String accessToken);

}
