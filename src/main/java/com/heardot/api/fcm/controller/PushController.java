package com.heardot.api.fcm.controller;

import com.heardot.api.dot.service.DotService;
import com.heardot.api.dto.ApiResult;
import com.heardot.api.fcm.dto.PushClickDto;
import com.heardot.api.fcm.dto.PushDto;
import com.heardot.api.fcm.service.PushService;
import com.heardot.domain.dot.Dot;
import com.heardot.domain.member.Member;
import com.heardot.exception.BusinessException;
import com.heardot.resolver.CurrentMember;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class PushController {

    private final PushService pushService;
    private final DotService dotService;

    @Operation(summary = "저장된 닷 접근시 푸시 API", description = "저장된 닷 접근시 푸시 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue ="jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PostMapping("/push")
    public ResponseEntity<ApiResult> checkDotExist(@CurrentMember Member member, @RequestBody PushDto.Request request) throws IOException {
        Long dotCount = dotService.countDotByLocation(member, request);
        if (dotCount == 0) {
            ApiResult apiResult = new ApiResult(String.valueOf(HttpStatus.BAD_REQUEST.value()), "해당하는 위치에 닷이 없습니다.");
            return ResponseEntity.ok(apiResult);
        }
        try {
            pushService.sendMessageTo(request.getTargetToken(), member.getMemberName(), dotCount);
            return ResponseEntity.ok(ApiResult.createOk());
        } catch (Exception e) {
            throw new BusinessException("푸시 알림 처리 중 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "저장된 닷 접근시 푸시 API", description = "저장된 닷 접근시 푸시 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @GetMapping("/push/click")
    public ResponseEntity<List<PushClickDto.Response>> pushClick(@CurrentMember Member member, @RequestBody PushClickDto.Request request) {
        List<Dot> dots = dotService.getDotsByLocation(member, request);
        List<PushClickDto.Response> responses = new ArrayList<>();
        for (Dot dot : dots) {
            responses.add(PushClickDto.Response.create(dot));
        }
        return ResponseEntity.ok(responses);
    }
}
