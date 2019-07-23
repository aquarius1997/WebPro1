package com.hyekyeong.securityex.mapper;

import com.hyekyeong.securityex.domain.BoardVO;
import com.hyekyeong.securityex.domain.Criteria;
import lombok.Setter;
import lombok.extern.java.Log;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardMapperTests {

    //Logger 객체 생성
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    //DB에서 모든 데이터가 올바르게 가져와 지는지 확인 (getList메소드 확인)
    @Test
    public void testGetList(){

        mapper.getList().forEach(board -> logger.info(String.valueOf(board)));
    }

    //insert 메소드 확인
    @Test
    public void testInsert(){

        BoardVO board = new BoardVO();
        board.setTitle("new title1");
        board.setContent("new content1");
        board.setWriter("newbie");

        mapper.insert(board);

        logger.info(String.valueOf(board));
    }

    //insertSelectKey 메소드 확인-
    @Test
    public void testInsertSelectKey(){
        BoardVO board = new BoardVO();
        board.setTitle("new title2");
        board.setContent("new content2");
        board.setWriter("newbie");


        mapper.insertSelectKey(board);

        logger.info(String.valueOf(board));
    }

    //read 메소드 확인
    @Test
    public void testRead(){

        //존재하는 게시물 번호로 테스트 진행
        BoardVO board = mapper.read(1L);

        logger.info(String.valueOf(board));
    }

    //delete 메소드 확인
    @Test
    public void testDelete(){

        logger.info("Delete count : " + mapper.delete(8L));
    }


    //update 메소드 확인
    @Test
    public void testUpdate(){

        BoardVO board = new BoardVO();
        //실행 전 존재 번호인지 확인하기
        board.setBno(3L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);
        logger.info("Update Count : " + count);
    }

    //목록 페이지 처리 확인
    @Test
    public void testPaging(){

        Criteria cri = new Criteria();

        //10개씩 1페이지 (보여주고싶은 페이지를 setPageNum에 파라미터로 기입
        cri.setPageNum(1);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> logger.info(String.valueOf(board)));
    }

    //동적 SQL 테스트
    @Test
    public void testSearch(){

        Criteria cri = new Criteria();
        cri.setKeyword("test");
        cri.setType("TC");

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> logger.info(String.valueOf(board)));

    }

}
