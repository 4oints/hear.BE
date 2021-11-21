package com.heardot.api.dot.service;

import com.heardot.api.dot.dto.CreateDotDto;
import com.heardot.api.dot.dto.UpdateDotDto;
import com.heardot.domain.dot.Dot;
import com.heardot.domain.dot.repository.DotRepository;
import com.heardot.domain.member.Member;
import com.heardot.exception.EntityNotFoundException;
import com.heardot.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DotService {

    private final DotRepository dotRepository;


    public long createDot(Member member, CreateDotDto.Request request) {
        Dot dot = new Dot(request, member);
        Dot savedDot = dotRepository.save(dot);
        return savedDot.getDotId();
    }

    public Long update(Member member, UpdateDotDto.Request request) {
        Dot foundDot = findWithMemberById(request.getDotId());
        checkOwner(member, foundDot);
        if (StringUtils.isNotEmpty(request.getComment())) {
            foundDot.updateComment(request.getComment());
        }
        if (StringUtils.isNotEmpty(request.getRegionNickname())) {
            foundDot.updateRegionNickname(request.getRegionNickname());
        }
        return foundDot.getDotId();
    }

    public Dot getDotWithMusic(Member member, Long dotId) {
        Dot foundDot = findWithMemberMusicById(dotId);
        checkOwnerWithMusic(member, foundDot);
        return foundDot;
    }

    private void checkOwner(Member member, Dot dot) {
        if (!dot.isOwner(member)) {
            throw new ForbiddenException("해당 닷에 대한 변경 권한이 없습니다.");
        }
    }

    private void checkOwnerWithMusic(Member member, Dot dot) {
        if (!dot.isOwner(member)) {
            throw new ForbiddenException("해당 닷에 대한 변경 권한이 없습니다.");
        }
    }

    public Dot findById(Long dotId) {
        return dotRepository.findById(dotId).orElseThrow(() -> new EntityNotFoundException(dotId));
    }

    public Dot findWithMemberById(Long dotId) {
        return dotRepository.findWithMemberByDotId(dotId).orElseThrow(() -> new EntityNotFoundException(dotId));
    }

    public Dot findWithMemberMusicById(Long dotId) {
        return dotRepository.findWithMemberMusicByDotId(dotId).orElseThrow(() -> new EntityNotFoundException(dotId));
    }


    public void deleteDot(Member member, Long dotId) {
        Dot foundDot = findWithMemberById(dotId);
        checkOwner(member, foundDot);
        dotRepository.delete(foundDot);
    }

    public List<Dot> getDotsByMonth(Member member, int month) {
        return dotRepository.findDotsByMonth(member, month);
    }

    public List<Dot> getDotsWithLocation(Member member) {
        return dotRepository.findDots(member);
    }
}
