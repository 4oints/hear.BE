package com.heardot.api.fcm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.heardot.api.fcm.dto.FcmMessage;
import com.heardot.api.fcm.dto.PushDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushService {

    private final ObjectMapper objectMapper;
    private final PushFeignClient pushFeignClient;

    @Value("${project-id}")
    String PROJECT_ID;

    @Value("${firebase-sdk-path}")
    String firebaseConfigPath;

    public void sendMessageTo(PushDto.Request request, String memberName) throws IOException {
        String contentType = "application/json; UTF-8";
        String message = makeMessage(request.getTargetToken(), memberName, request.getLatitude(), request.getLongitude());

        String response = pushFeignClient.FcmRequest(contentType, "Bearer " + getAccessToken(), message);
        log.info("FCM response : " + response);
    }

    private String makeMessage(String targetToken, String title, String latitude, String longitude) throws JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .validate_only(false)
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .data(FcmMessage.Data.builder()
                                .memberName(title)
                                .latitude(latitude)
                                .longitude(longitude)
                                .build()).build()
                ).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {

        final String googleAuthUrl = "https://www.googleapis.com/auth/cloud-platform";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of(googleAuthUrl));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
