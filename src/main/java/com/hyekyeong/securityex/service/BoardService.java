package com.hyekyeong.securityex.service;

import com.hyekyeong.securityex.domain.BoardAttachVO;
import com.hyekyeong.securityex.domain.BoardVO;
import com.hyekyeong.securityex.domain.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface BoardService {

    //전체 게시물의 개수 구하기
    public int getTotal(Criteria cri);

    //게시물 등록
    public void register(BoardVO board);

    //특정 게시물 조회
    public BoardVO get(Long bno);

    //게시물 수정
    public boolean modify(BoardVO board);

    //게시물 삭제
    public boolean remove(Long bno);

//    //BoardVO 리스트 가져오기
//    public List<BoardVO> getList();

    //BoardVO 리스트를 페이징 처리해 가져오기
    public List<BoardVO> getList(Criteria cri);

    public List<BoardAttachVO> getAttachList(Long bno);

    public void removeAttach(Long bno);


}