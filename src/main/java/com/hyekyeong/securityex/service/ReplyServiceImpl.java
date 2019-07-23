package com.hyekyeong.securityex.service;

import com.hyekyeong.securityex.domain.Criteria;
import com.hyekyeong.securityex.domain.ReplyPageDTO;
import com.hyekyeong.securityex.domain.ReplyVO;
import com.hyekyeong.securityex.mapper.BoardMapper;
import com.hyekyeong.securityex.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    //댓글 등록하기
    @Transactional
    @Override
    public int register(ReplyVO vo) {

        log.info("register......" + vo);

        boardMapper.updateReplyCnt(vo.getBno(), 1);

        return mapper.insert(vo);

    }

    //댓글 조회하기
    @Override
    public ReplyVO get(Long rno){

        log.info("get......" + rno);

        return mapper.read(rno);
    }

    //댓글 수정하기
    @Override
    public int modify(ReplyVO vo){

        log.info("modify......" + vo);

        return mapper.update(vo);
    }

    //댓글 삭제하기
    @Transactional
    @Override
    public int remove(Long rno){

        log.info("remove...." + rno);

        ReplyVO vo = mapper.read(rno);

        boardMapper.updateReplyCnt(vo.getBno(), -1);
        return mapper.delete(rno);

    }

    //페이징 처리한 댓글 목록 보기
    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno){

        log.info("get Reply List of a Board " + bno);

        return mapper.getListWithPaging(cri, bno);
    }


    //페이징 처리한 댓글 목록을 보기 위해 ReplyPageDTO를 반환
    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno){

        return new ReplyPageDTO(
                mapper.getCountByBno(bno),
                mapper.getListWithPaging(cri, bno));

    }
}
