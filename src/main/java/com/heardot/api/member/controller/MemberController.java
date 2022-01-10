package com.heardot.api.member.controller;

import com.heardot.api.dot.dto.UpdateDotDto;
import com.heardot.api.dto.ApiResult;
import com.heardot.api.member.dto.DefaultProfileUrl;
import com.heardot.api.member.dto.MemberInfoDto;
import com.heardot.api.member.dto.UpdateMemberDto;
import com.heardot.domain.member.Member;
import com.heardot.domain.member.service.MemberService;
import com.heardot.resolver.CurrentMember;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "멤버 정보 변경 API", description = "멤버 정보 변경 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PatchMapping("")
    public ResponseEntity<UpdateMemberDto.Response> updateMember(@CurrentMember Member member, @RequestBody UpdateMemberDto.Request request) {
        Long memberId = memberService.update(member, request);
        return ResponseEntity.ok(UpdateMemberDto.Response.create(memberId));
    }

    @Operation(summary = "멤버 정보 조회 API", description = "멤버 정보 조회 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @GetMapping("")
    public ResponseEntity<MemberInfoDto.Response> getMemberInfo(@CurrentMember Member member) {
        return ResponseEntity.ok(MemberInfoDto.Response.create(member));
    }

    @Operation(summary = "기본 프로필 이미지로 변경 API", description = "기본 프로필 이미지로 변경 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PatchMapping("/defaultProfileImage")
    public ResponseEntity<ApiResult> setDefaultProfileImage(@RequestBody DefaultProfileUrl.Request request, @CurrentMember Member member) {
        memberService.setDefaultProfileImage(member, request.getDefaultProfileImageUrl());
        return ResponseEntity.ok(ApiResult.createOk());
    }
}
