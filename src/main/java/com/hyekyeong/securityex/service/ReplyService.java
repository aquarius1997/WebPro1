package com.hyekyeong.securityex.service;

import com.hyekyeong.securityex.domain.Criteria;
import com.hyekyeong.securityex.domain.ReplyPageDTO;
import com.hyekyeong.securityex.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    //댓글 등록
    public int register(ReplyVO vo);

    //댓글 조회
    public ReplyVO get(Long rno);

    //댓글 수정
    public int modify(ReplyVO vo);

    //댓글 삭제
    public int remove(Long rno);

    //페이징 처리한 댓글 목록 보기
    public List<ReplyVO> getList(Criteria cri, Long bno);

    //페이징 처리한 댓글 목록을 보기 위해 ReplyPageDTO를 반환
    public ReplyPageDTO getListPage(Criteria cri, Long bno);

}
