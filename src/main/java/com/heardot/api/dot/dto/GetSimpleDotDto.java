package com.heardot.api.dot.dto;

import com.heardot.domain.dot.Dot;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetSimpleDotDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    @ApiModel(value = "Dot 요약 정보 조회 반환 객체", description = "Dot 요약 정보 조회 반환 객체")
    public static class Response {

        @ApiModelProperty(value = "닷 ID")
        private Long dotId;

        @ApiModelProperty(value = "앨범아트")
        private String albumArt;

        @ApiModelProperty(value = "노래 제목")
        private String musicName;

        @ApiModelProperty(value = "아티스트")
        private String artist;

        @ApiModelProperty(value = "저장한 날짜")
        private String regTime;

        public static Response create(Dot dot) {
            return Response.builder()
                    .dotId(dot.getDotId())
                    .albumArt(dot.getMusic().getAlbumArt())
                    .musicName(dot.getMusic().getMusicName())
                    .artist(dot.getMusic().getArtist())
                    .regTime(dot.getRegTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .build();
        }
    }
}
