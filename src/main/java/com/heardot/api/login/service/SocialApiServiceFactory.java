package com.heardot.api.login.service;

import com.heardot.domain.member.constant.SocialType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialApiServiceFactory {

    private static Map<String, SocialApiService> socialApiServices;

    public SocialApiServiceFactory(Map<String, SocialApiService> socialApiServices) {
        this.socialApiServices = socialApiServices;
    }

    public static SocialApiService getSocialApiService(SocialType socialType) {


        String socialApiServiceBeanName = "";

        if(SocialType.GOOGLE.equals(socialType)) {
            socialApiServiceBeanName = "googleApiServiceImpl";
        } else if(SocialType.KAKAO.equals(socialType)) {
            socialApiServiceBeanName = "kakaoApiServiceImpl";
        }

        SocialApiService socialApiSerivce = socialApiServices.get(socialApiServiceBeanName);

        if(socialApiSerivce == null){
            throw new IllegalArgumentException("잘못된 소셜 로그인 타입입니다.");
        }

        return socialApiSerivce;
    }

}
