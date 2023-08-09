package com.ezen.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.member.dto.Member;
import com.ezen.mybatis.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberMapperTest {
	SqlSession sqlSession;	
	
	@BeforeEach
	public void setUp() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	
	@Test
	@Disabled
	public void createTest() {
		
		//given
		Member member = Member.builder()
							  .id("hoho")
							  .passwd("1111")
							  .name("조영호")
							  .email("hoho@gmail.com")
							  .build();
		//when
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		boolean success = memberMapper.create(member);
		
		//then
		assertThat(success).isTrue();
		log.debug("가입 여부 : {}",success);
		
		if(success == true) {
			sqlSession.commit();			
		} else {
			sqlSession.rollback();			
		}
		
	}
	
	@Test
	@Disabled
	public void findByUserTest() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		String id = "bangry3";
		String passwd = "1111";
		params.put("id", id);
		params.put("passwd", passwd);
		
		//when
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		Member member = memberMapper.findByUser(id, passwd);
		
		//then
		assertThat(member).isNotNull();
		log.debug("찾아온 회원 : {}",member);
	}
	
	@Test
	@Disabled
	public void findByAllTest() {
		//given
		
		//when
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		log.debug("{}",memberMapper);
		List<Member> list = memberMapper.findByAll();
		
		//then
		assertThat(list).isNotNull();
		for (Member member : list) {
			log.debug("회원 정보 : {}",member);
		}
	}
	
	
	
	
	
	@Test
	@Disabled
	public void findById() {
		
		//given
		String id = "melon";
		
		//when
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		Member member = memberMapper.findById(id);
		
		//then
		assertThat(member).isNotNull();
		log.debug("찾아온 회원 : {}",member);
	}
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
	
}
