package com.heardot.api.fcm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(url = "https://fcm.googleapis.com/v1/projects/heardot-671cc/messages:send", name = "PushClient")
public interface PushFeignClient {

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    String FcmRequest(
            @RequestHeader("Content-type") String contentType,
            @RequestHeader("Authorization") String accessToken,
            @RequestBody String body
    );
}
