package com.hyekyeong.securityex.mapper;

import com.hyekyeong.securityex.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MemberMapper {

    public MemberVO read(String userid);

}
