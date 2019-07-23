package com.hyekyeong.securityex.mapper;

import com.hyekyeong.securityex.domain.Criteria;
import com.hyekyeong.securityex.domain.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReplyMapper {

    //댓글 추가
    public int insert(ReplyVO vo);

    //댓글 조회
    public ReplyVO read(Long rno);

    //댓글 삭제
    public int delete(Long rno);

    //댓글 수정. 댓글의 내용(reply)과 최종 수정시간(updatedate)을 수정
    public int update(ReplyVO reply);

    //페이징 처리한 댓글 목록 보기
    public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);

    //해당 게시물의 전체 댓글 수 파악하기
    public int getCountByBno(Long bno);


}
