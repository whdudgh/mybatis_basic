package com.ezen.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.board.dto.BoardDTO;
import com.ezen.mybatis.domain.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 보드맵퍼 테스트클래스
 * @author 재헌
 * (2023-08-09)
 */
@Slf4j
public class BoardTest {

	private SqlSession sqlSession = null;

	@BeforeEach
	void init() {
		String resource = "mybatis-config.xml";

		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession(true);

		System.out.println("==================== sqlSession 생성 완료 ====================");
	}

	@Test
	@Disabled
	void createTest() {
		BoardDTO board = BoardDTO.builder()
							.category(1)
							.title("게시판 제목 테스트2")
							.description("게시판 내용 테스트2")
							.build();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.create(board);
//		sqlSession.rollback();
		sqlSession.commit();
		System.out.println("신규 게시판 등록 완료");
		assertThat(board)
		.isNotNull();
	}
	
	@Test
	@Disabled
	public void findAllTest(){
		System.out.println("==================== 전체 게시판 조회 ========================");
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		log.debug("hi : {}", mapper);
		List<BoardDTO> list = mapper.findAll();
		for (BoardDTO boardDTO : list) {
			System.out.println(boardDTO);
		}
	}
	
	@Test
	@Disabled
	void updateBoardTest() {
		BoardDTO updateBoard = new BoardDTO();
		updateBoard.setBoardId(50);
		updateBoard.setTitle("게시판 제목 테스트");
		updateBoard.setDescription("게시판 내용 테스트");
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.updateBoard(updateBoard);
		log.debug("{}", updateBoard);
		
		assertThat(updateBoard)
			.isNotNull();
	}
	
	
}