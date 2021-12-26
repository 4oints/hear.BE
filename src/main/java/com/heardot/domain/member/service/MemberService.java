package com.heardot.domain.member.service;

import com.heardot.api.member.dto.UpdateMemberDto;
import com.heardot.config.auth.dto.OAuthAttributes;
import com.heardot.config.auth.dto.TokenDto;
import com.heardot.domain.member.Member;
import com.heardot.domain.member.repository.MemberRepository;
import com.heardot.domain.memberToken.MemberToken;
import com.heardot.exception.InvalidParameterException;
import com.heardot.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(OAuthAttributes oAuthAttributes, TokenDto tokenDto, String defaultProfileImageUrl) {
        Optional<Member> optionalMember = memberRepository.findByEmail(oAuthAttributes.getEmail());

        if(optionalMember.isEmpty()) {
            Member member = Member.createOauthMember(oAuthAttributes, defaultProfileImageUrl);
            Member savedMember = memberRepository.save(member);

            //리프레시 토큰 저장
            saveRefreshToken(savedMember, tokenDto);
        }
    }
    /**
     * refresh token 저장
     * @param tokenDto
     */
    public void saveRefreshToken(Member member, TokenDto tokenDto) {
        Date refreshTokenExpireTime = tokenDto.getRefreshTokenExpireTime();

        LocalDateTime tokenExpiredTime = DateTimeUtils.convertToLocalDateTime(refreshTokenExpireTime);

        MemberToken memberToken = MemberToken.createMemberToken(member, tokenDto.getRefreshToken(), tokenExpiredTime);
        member.updateMemberToken(memberToken);
        memberRepository.save(member);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("해당 회원을 찾을 수 없습니다."));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("해당 회원이 존재하지 않습니다."));
    }

    private void checkPassword(Member member, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new InvalidParameterException("회원 정보가 옳지 않습니다.");
        }
    }

    public Long update(Member member, UpdateMemberDto.Request request) {
        if (!StringUtils.isEmptyOrWhitespace(request.getProfileImageUrl())) {
            member.updateProfileImageUrl(request.getProfileImageUrl());
        }
        if (!StringUtils.isEmptyOrWhitespace(request.getMemberName())) {
            member.updateMemberName(request.getMemberName());
        }
        return member.getMemberId();
    }
}