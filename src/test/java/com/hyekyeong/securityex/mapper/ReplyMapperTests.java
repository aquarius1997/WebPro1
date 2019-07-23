package com.hyekyeong.securityex.mapper;

import com.hyekyeong.securityex.domain.BoardVO;
import com.hyekyeong.securityex.domain.Criteria;
import com.hyekyeong.securityex.domain.ReplyVO;
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
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class ReplyMapperTests {

    //Logger 객체 생성
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인
    private Long[] bnoArr = {46L, 47L, 48L, 49L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    //댓글 생성 테스트
    @Test
    public void testCreate(){

        IntStream.rangeClosed(1, 10).forEach(i -> {ReplyVO vo = new ReplyVO(); vo.setBno(bnoArr[i%4]);
        vo.setReply("댓글 테스트" + i); vo.setReplyer("replyer" + i); mapper.insert(vo);
        });

    }

    //댓글 조회 테스트
    @Test
    public void testRead(){

        Long targetRno = 5L;

        ReplyVO vo = mapper.read(targetRno);

        logger.info(String.valueOf(vo));

    }

    //댓글 삭제 테스트
    @Test
    public void testDelete(){

        Long targetRno = 2L;

        mapper.delete(targetRno);

    }

    //댓글 수정 테스트
    @Test
    public void testUpdate(){

        Long targetRno = 2L;

        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("Update Reply ");

        int count = mapper.update(vo);

        logger.info("UPDATE COUNT : " + count);
    }

    //페이징 처리한 댓글 목록 보기 테스트
    @Test
    public void testList(){

        Criteria cri = new Criteria();

        //bnoArr[0] : 46
        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply->logger.info(String.valueOf(reply)));
    }

    //mapper 객체 생성 테스트
    @Test
    public void testMapper(){

        logger.info(String.valueOf(mapper));

    }

    //인덱스를 사용한 페이징 처리한 댓글 목록 조회
    @Test
    public void testList2(){

        //2페이지의 10개 확인
        Criteria cri = new Criteria(1, 10);

        List<ReplyVO> replies = mapper.getListWithPaging(cri, 47L);

        replies.forEach(reply -> logger.info(String.valueOf(reply)));
    }


}
