package com.ezen.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.article.dto.ArticleDTO;
import com.ezen.mybatis.domain.board.mapper.ArticleMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleMapperTest {
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
		
		log.debug("==================== sqlSession 생성 완료 ====================");
	}
	
	
	@Test
	@DisplayName("전체 게시판 조회")
	@Disabled
//	 전체 게시판 조회 재헌(2023-08-09)
	public void findAllTest(){
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		log.debug("hi : {}", mapper);
		List<ArticleDTO> list = mapper.findAll();
		for (ArticleDTO articleDTO : list) {
			System.out.println(articleDTO);
		}
	}
	
	@Test
	@DisplayName("게시글 첫 글 등록")
	@Disabled
//	 게시글 등록 재헌(2023-08-09)
	void createTest() {
		ArticleDTO article = ArticleDTO.builder()
							.boardId(20)
							.writer("monday")
							.subject("게시글 제목 테스트")
							.content("게시글 내용 테스트")
							.passwd("1111")
							.levelNo(0)
							.orderNo(0)
							.build();
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		mapper.create(article);
//		sqlSession.rollback();
		System.out.println("신규 게시글 등록 완료");
		assertThat(article)
			.isNotNull();
	}
	
	@Test
	@DisplayName("게시글 댓글 등록")
	@Disabled
	// 댓글 등록 재헌(2023-08-09)
	void commentCreateTest() {
		ArticleDTO article = ArticleDTO.builder()
							.boardId(10)
							.writer("tuesday")
							.subject("게시글 댓글 제목 테스트1")
							.content("게시글 댓글 내용 테스트1")
							.passwd("1111")
							.groupNo(1)
							.levelNo(1)
							.build();
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		mapper.commentCreate(article);
//		sqlSession.rollback();
		System.out.println("신규 댓글 등록 완료");
		assertThat(article)
			.isNotNull();
	}
	
	@Test
	@DisplayName("게시글 상세보기")
	@Disabled
	// 게시글 상세보기 영호(2023-08-09)
	public void readArticleTest() {
		
		//given
		int groupNo = 1;
		
		//when
		ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		List<ArticleDTO> articleDTO = articleMapper.readArticle(groupNo);
		
		//then
		for (ArticleDTO article : articleDTO) {
//			log.debug("{}", article);
			log.debug("가져온 게시글의 아티클 : {}",article);
		}
	}
	
	@Test
	@DisplayName("대댓글 달기")
	@Disabled
	//대댓글 달기 테스트 영호(2023-08-09)
	public void setRReflyTest() {
		
		//given
		ArticleDTO articleDTO = ArticleDTO.builder()
										    .boardId(10)
										    .writer("monday")
										    .subject("새로달린 대댓글 입니다.")
										    .content("이사람 또왔내")
										    .passwd("1234")
										    .groupNo(1)
										    .levelNo(2)
										    .articleId(2)
										    .build();
		//when
		ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		boolean result = articleMapper.setRReply(articleDTO);
		
		//then
		assertThat(result).isTrue();
		log.debug("대댓글 등록 결과 : {}",result);
		
		if(result == true) {
//			sqlSession.commit();			
		} else {
//			sqlSession.rollback();			
		}
		
		
	}
	
	@Test
	@DisplayName("해당 게시글 삭제")
	@Disabled
	// 삭제기능 테스트 영호(2023-08-09)
	public void deleteArticleTest() {
		
		//given(get이나 post방식으로 선택된 articleId를 넘겨받았다 치고 작업함.)
		String passwd = "1111";
		int articleId = 3;
		
		//when
		ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		boolean result = articleMapper.deleteArticle(passwd, articleId);
		
		//then
		assertThat(result).isTrue();
		log.debug("삭제 여부 : {}", result);
		
//		if(result == true) {
//			sqlSession.commit();			
//		} else {
//			sqlSession.rollback();			
//		}
		
	}
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
	
}
