package com.hyekyeong.securityex.service;

import com.hyekyeong.securityex.domain.BoardVO;
import com.hyekyeong.securityex.domain.Criteria;
import lombok.extern.java.Log;
import org.junit.runner.RunWith;
import lombok.Setter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardServiceTests {

    @Setter(onMethod_ = {@Autowired})
    private BoardService service;

    //Logger 객체 생성
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //BoardService 객체 주입 확인
    @Test
    public void testExist(){

        logger.info(String.valueOf(service));
        assertNotNull(service);
    }

    //게시물 등록 테스트
    @Test
    public void testRegister(){
        BoardVO board = new BoardVO();
        board.setTitle("testRegister-title1");
        board.setContent("testRegister-content1");
        board.setWriter("testRegister-writer1");

        service.register(board);

        logger.info("생성된 게시물 번호 : " + board.getBno());
    }

    //특정 게시물 조회 테스트
    @Test
    public void testGet(){

        logger.info(String.valueOf(service.get(1L)));
    }

    //게시물 삭제 테스트
    @Test
    public void testDelete(){

        //게시물 번호 존재 여부 확인 필수
        logger.info("Remove Result : " + service.remove(7L));
    }

    //게시물 수정 테스트
    @Test
    public void testUpdate(){

        BoardVO board = service.get(1L);

        if(board == null)
            return;

        board.setTitle("Test-수정된 제목");
        log.info("MODIFY RESULT : " + service.modify(board));
    }

//    //전체 게시물 조회 테스트
//    @Test
//    public void testGetList(){
//
//        service.getList().forEach(board -> logger.info(String.valueOf(board)));
//    }

    //페이징 처리한 게시물 조회 테스트
    @Test
    public void testGetList(){

        service.getList(new Criteria(1, 10)).forEach(board -> logger.info(String.valueOf(board)));

    }




}
