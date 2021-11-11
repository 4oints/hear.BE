package com.heardot.api.dot.controller;

import com.heardot.api.dot.dto.CreateDotDto;
import com.heardot.api.dot.dto.UpdateDotDto;
import com.heardot.api.dot.service.DotService;
import com.heardot.domain.member.Member;
import com.heardot.domain.member.service.MemberService;
import com.heardot.domain.music.constant.SiteType;
import com.heardot.exception.InvalidParameterException;
import com.heardot.resolver.CurrentMember;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DotController {

    private final DotService dotService;

    @Operation(summary = "DOT 생성 API", description = "DOT 생성 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue ="jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PostMapping(value = "/dot", headers = { "Content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDotDto.Response> createDot(@CurrentMember Member member, @RequestBody CreateDotDto.Request request){
        if (StringUtils.isAnyBlank(request.getLatitude(), request.getLongitude())) {
            throw new InvalidParameterException("장소 정보가 없습니다.");
        }
        if (StringUtils.isAnyBlank(request.getMusicUrl(), request.getAlbumArt(), request.getMusicName())) {
            throw new InvalidParameterException("노래 정보가 없습니다.");
        }
        if (!SiteType.isSiteType(request.getSiteType())) {
            throw new InvalidParameterException("지원하지 않는 사이트입니다.");
        }

        long savedDotId = dotService.createDot(member, request);
        CreateDotDto.Response response = CreateDotDto.Response.create(savedDotId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "DOT 정보 업데이트 API", description = "DOT 정보 업데이트 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue ="jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PatchMapping(value = "/dot")
    public ResponseEntity<UpdateDotDto.Response> updateDot(@CurrentMember Member member, @RequestBody UpdateDotDto.Request request) {
        Long updatedDotId = dotService.update(member, request);
        UpdateDotDto.Response  response = UpdateDotDto.Response.create(updatedDotId);
        return ResponseEntity.ok(response);
    }

}

