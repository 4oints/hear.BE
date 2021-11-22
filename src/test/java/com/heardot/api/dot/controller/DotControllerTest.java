package com.heardot.api.dot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heardot.api.BaseApiController;
import com.heardot.api.dot.dto.CreateDotDto;
import com.heardot.api.dot.dto.UpdateDotDto;
import com.heardot.domain.dot.Dot;
import com.heardot.domain.dot.repository.DotRepository;
import com.heardot.domain.member.Member;
import com.heardot.exception.EntityNotFoundException;
import com.heardot.resolver.CurrentMember;
import com.heardot.util.TestUtil;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.meta.When;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DotControllerTest extends BaseApiController {

    @Autowired ObjectMapper objectMapper;

    @Autowired
    DotRepository dotRepository;

    @Test
    @DisplayName("DOT 생성")
    void create_dot() throws Exception { // TODO 쿼리 확인하고 시간 줄이기

        //given
        CreateDotDto.Request request = new CreateDotDto.Request("https://www.test", "test", "test","test", "YOUTUBE",
                "2000", "1000","test장소",null);

        //when
        MvcResult mvcResult = mockMvc.perform(post("/api/dot")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateDotDto.Response response = TestUtil.convert(mvcResult, CreateDotDto.Response.class);
/*
        given(dotRepository.findWithMemberByDotId(response.getDotId()))
                .willReturn(dotRepository.findWithMemberByDotId(response.getDotId()));

        given(dotRepository.save(new Dot(request, member)))
                .willReturn(dotRepository.save(new Dot(request, member)));*/

        Dot savedDot = dotRepository.findById(response.getDotId()).orElseThrow(() -> new EntityNotFoundException(response.getDotId()));

        // then
        assertNotNull(savedDot);
    }

    /*@Test
    @DisplayName("DOT 정보 변경")
    void update_dot_success() throws Exception {
        // given

        UpdateDotDto.Request request = new UpdateDotDto.Request("변경된 지역 별명", "변경된 코멘트");


        mockMvc.perform(patch("/api/dot")
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }*/
}