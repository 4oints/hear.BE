package com.heardot.api.member.dto;

import com.heardot.domain.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberInfoDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "멤버 정보 조회 반환 객체", description = "멤버 정보 조회 반환 객체")
    public static class Response {

        @ApiModelProperty("닉네임")
        private String memberName;

        @ApiModelProperty("이메일")
        private String email;

        @ApiModelProperty("프로필 이미지 URL")
        private String profileImageUrl;

        public static Response create(Member member) {
            return new MemberInfoDto.Response(member.getMemberName(), member.getEmail(), member.getProfileImageUrl());
        }
    }
}
