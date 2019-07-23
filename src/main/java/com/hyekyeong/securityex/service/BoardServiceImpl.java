package com.hyekyeong.securityex.service;

import com.hyekyeong.securityex.domain.BoardAttachVO;
import com.hyekyeong.securityex.domain.BoardVO;
import com.hyekyeong.securityex.domain.Criteria;
import com.hyekyeong.securityex.mapper.BoardAttachMapper;
import com.hyekyeong.securityex.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
@Service
@Log4j2
public class BoardServiceImpl implements BoardService{

    //Logger 객체 생성
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private BoardAttachMapper attachMapper;


    //전체 게시물의 개수 구하기
    @Override
    public int getTotal(Criteria cri){

        logger.info("get total count");
        return mapper.getTotalCount(cri);
    }


    //게시물 등록
    @Transactional
    @Override
    public void register(BoardVO board) {

        log.info("register......" + board);

        mapper.insertSelectKey(board);

        if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
            return;
        }

        board.getAttachList().forEach(attach -> {

            attach.setBno(board.getBno());
            attachMapper.insert(attach);
        });
    }

    //특정 게시물 조회
    @Override
    public BoardVO get(Long bno){

        logger.info("get---------------------" + bno);

        return mapper.read(bno);
    }

    //삭제가 올바르게 진행되면 1 이상의 값을 리턴
    @Transactional
    @Override
    public boolean remove(Long bno) {

        log.info("remove...." + bno);

        attachMapper.deleteAll(bno);

        return mapper.delete(bno) == 1;
    }

//    //모든 게시물 조회
//    @Override
//    public List<BoardVO> getList(){
//
//        logger.info("getList------------------");
//
//        return mapper.getList();
//    }

    //게시물을 페이징 처리해 조회
    @Override
    public List<BoardVO> getList(Criteria cri){


        logger.info("get list with criteria : " + cri);

        return mapper.getListWithPaging(cri);
    }

    //게시물 수정
    @Transactional
    @Override
    public boolean modify(BoardVO board) {

        log.info("modify......" + board);

        attachMapper.deleteAll(board.getBno());

        boolean modifyResult = mapper.update(board) == 1;

        if (modifyResult && board.getAttachList() != null) {

            board.getAttachList().forEach(attach -> {

                attach.setBno(board.getBno());
                attachMapper.insert(attach);
            });
        }

        return modifyResult;
    }



    @Override
    public List<BoardAttachVO> getAttachList(Long bno) {

        log.info("get Attach list by bno" + bno);

        return attachMapper.findByBno(bno);
    }

    @Override
    public void removeAttach(Long bno) {

        log.info("remove all attach files");

        attachMapper.deleteAll(bno);
    }

}
