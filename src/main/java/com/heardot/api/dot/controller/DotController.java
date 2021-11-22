package com.heardot.api.dot.controller;

import com.heardot.api.dot.dto.*;
import com.heardot.api.dot.service.DotService;
import com.heardot.api.dto.ApiResult;
import com.heardot.domain.dot.Dot;
import com.heardot.domain.member.Member;
import com.heardot.domain.music.constant.SiteType;
import com.heardot.exception.InvalidParameterException;
import com.heardot.resolver.CurrentMember;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dot")
public class DotController {

    private final DotService dotService;

    @Operation(summary = "DOT 생성 API", description = "DOT 생성 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue ="jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDotDto.Response> createDot(@CurrentMember Member member, @RequestBody CreateDotDto.Request request){
        if (StringUtils.isAnyBlank(request.getLatitude(), request.getLongitude(), request.getRegionNickname())) {
            throw new InvalidParameterException("장소 정보가 부족합니다.");
        }
        if (StringUtils.isAnyBlank(request.getMusicUrl(), request.getAlbumArt(), request.getMusicName(), request.getArtist())) {
            throw new InvalidParameterException("노래 정보가 부족합니다.");
        }
        if (!SiteType.isSiteType(request.getSiteType())) {
            throw new InvalidParameterException("지원하지 않는 사이트입니다.");
        }

        long savedDotId = dotService.createDot(member, request);
        CreateDotDto.Response response = CreateDotDto.Response.create(savedDotId);
        return ok(response);
    }

    @Operation(summary = "DOT 정보 업데이트 API", description = "DOT 정보 업데이트 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue ="jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @PatchMapping(value = "")
    public ResponseEntity<UpdateDotDto.Response> updateDot(@CurrentMember Member member, @RequestBody UpdateDotDto.Request request) {
        Long updatedDotId = dotService.update(member, request);
        UpdateDotDto.Response response = UpdateDotDto.Response.create(updatedDotId);
        return ok(response);
    }

    @Operation(summary = "Dot 요약 정보 조회", description = "Dot 요약 정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @GetMapping(value = "/simple/{dotId}")
    public ResponseEntity<GetSimpleDotDto.Response> getSimpleDot(@CurrentMember Member member, @PathVariable Long dotId) {
        Dot dot = dotService.getDotWithMusic(member, dotId);
        GetSimpleDotDto.Response response = GetSimpleDotDto.Response.create(dot);
        return ok(response);
    }

    @Operation(summary = "Dot 상세 정보 조회", description = "Dot 상세 정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @GetMapping(value = "/detail/{dotId}")
    public ResponseEntity<GetDetailDotDto.Response> getDetailDot(@CurrentMember Member member, @PathVariable Long dotId) {
        Dot dot = dotService.getDotWithMusic(member, dotId);
        GetDetailDotDto.Response response = GetDetailDotDto.Response.create(dot);
        return ok(response);
    }

    @Operation(summary = "Dot 월 별 정보 조회", description = "Dot 월 별 정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @GetMapping(value = "/month/{month}")
    public ResponseEntity<List<GetSimpleDotDto.Response>> getDotsByMonth(@CurrentMember Member member, @PathVariable int month) {
        List<Dot> dots = dotService.getDotsByMonth(member, month);
        List<GetSimpleDotDto.Response> responses = new ArrayList<>();
        for (Dot dot : dots) {
            responses.add(GetSimpleDotDto.Response.create(dot));
        }
        return ok(responses);
    }

    @Operation(summary = "저장한 닷 전체 조회 - Map", description = "저장한 닷 전체 조회 - Map")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<GetLocationDotDto.Response>> getDotsWithLocation(@CurrentMember Member member) {
        List<Dot> dots = dotService.getDotsWithLocation(member);
        List<GetLocationDotDto.Response> responses = new ArrayList<>();
        for (Dot dot : dots) {
            responses.add(GetLocationDotDto.Response.create(dot));
        }
        return ok(responses);
    }

    @Operation(summary = "Dot 삭제", description = "Dot 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue = "jwt access token", dataType = "string", value = "jwt access token", required = true, paramType = "header")
    })
    @DeleteMapping(value = "/{dotId}")
    public ResponseEntity<ApiResult> deleteDot(@CurrentMember Member member, @PathVariable Long dotId) {
        dotService.deleteDot(member, dotId);
        ApiResult apiResult = ApiResult.createOk();
        return ok(apiResult);
    }
}

