package com.heardot.api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UpdateMemberDto {
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "멤버 정보 업데이트 반환 객체", description = "멤버 정보 업데이트 반환 객체")
    public static class Response {
        @ApiModelProperty("변경된 멤버 아이디")
        private Long memberId;

        public static Response create(Long memberId) {
            return new UpdateMemberDto.Response(memberId);
        }
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "멤버 정보 업데이트 요청 객체", description = "멤버 정보 업데이트 요청 객체")
    public static class Request {
        @ApiModelProperty("변경할 멤버 이름")
        private String memberName;

        @ApiModelProperty("변경할 멤버 프로필 사진 URL")
        private String profileImageUrl;
    }
}
