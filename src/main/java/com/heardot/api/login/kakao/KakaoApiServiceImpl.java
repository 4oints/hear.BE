package com.heardot.api.login.kakao;

import com.heardot.api.login.service.SocialApiService;
import com.heardot.config.auth.dto.OAuthAttributes;
import com.heardot.domain.member.constant.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoApiServiceImpl implements SocialApiService {

    private final PasswordEncoder passwordEncoder;
    private final KakaoFeignClient kakaoFeignClient;

    @Value("${my-pass}")
    private String pass;

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoUserInfo kakaoUserInfo = kakaoFeignClient.kakaoLogin(contentType, "Bearer " + accessToken);
        log.info("kakao email: " + kakaoUserInfo.getKakaoAccount().getEmail());
        log.info("kakao nickname: " + kakaoUserInfo.getKakaoAccount().getProfile().getNickname());

        return OAuthAttributes.builder()
                .email(StringUtils.isBlank(kakaoUserInfo.getKakaoAccount().getEmail()) ? kakaoUserInfo.getId() : kakaoUserInfo.getKakaoAccount().getEmail())
                .name(kakaoUserInfo.getKakaoAccount().getProfile().getNickname()) // TODO 이름 랜덤 생성
                .socialType(SocialType.KAKAO)
                .password(passwordEncoder.encode(pass))
                .build();
    }

}
