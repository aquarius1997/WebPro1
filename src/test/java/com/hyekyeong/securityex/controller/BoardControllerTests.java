package com.hyekyeong.securityex.controller;

import lombok.Setter;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.lang.Exception;

//Controller 테스트
@WebAppConfiguration

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardControllerTests {

    //Logger 객체 생성
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    // @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    //전체 게시물 조회 테스트
    @Test
    public void testList() throws Exception{

        logger.info(
                String.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap()));
    }

    //페이징 처리한 게시물 목록 조회 테스트
    //2페이지의 게시글 10개를 조회한다
    @Test
    public void testListPaging() throws Exception{

        logger.info(String.valueOf(mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list").param("pageNum", "2").
                        param("amount", "10")).andReturn().getModelAndView().getModelMap()));

    }

    //등록 테스트
    @Test
    public void testRegister()throws Exception{

        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register").param("title", "postTesting").param("content", "controllertest1-content").param("writer", "user00")).andReturn().getModelAndView().getViewName();

        logger.info(resultPage);

    }

    //특정 게시물 조회 테스트
    @Test
    public void testGet() throws Exception{

        logger.info(String.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "2")).andReturn().getModelAndView().getModelMap()));

    }

    //수정 테스트
    @Test
    public void testModify() throws Exception{

        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify").param("bno", "1").param("title", "수정된테스트새글제목").param("content", "t수정된 테스트 새글 내용").param("writer", "user00")).andReturn().getModelAndView().getViewName();

        logger.info(resultPage);
    }

    //삭제 테스트
    @Test
    public void testRemove()throws Exception{
        //삭제전 DB 게시물 번호 확인 필요
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", "1")).andReturn().getModelAndView().getViewName();

        logger.info(resultPage);
    }
}
