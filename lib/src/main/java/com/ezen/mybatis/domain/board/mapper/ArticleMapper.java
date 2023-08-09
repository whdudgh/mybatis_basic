package com.ezen.mybatis.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.mybatis.domain.article.dto.ArticleDTO;
import com.ezen.mybatis.domain.common.web.PageParams;

/**
 * article 테이블 관련 명세
 */
public interface ArticleMapper {
	/** 신규 게시글 등록 #7번 sql사용함. */
	public void create(ArticleDTO articleDo);
	
	/** 전체 게시글 목록 반환 연습삼아 먼저해봄.*/
	public List<ArticleDTO> findAll();

	// 페이징 계산에 필요한 게시글 전체 갯수 반환
	public int getCountAll();
	
	// 페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수 반환 재헌 함.
	public int getCountAll(PageParams pageParams);
	
	// 요청 페이지, 페이지당 보여지는 목록 갯수에 따른 목록 반환 재헌 함. 
	public List<ArticleDTO> findByAll(PageParams pageParams);
	
	// 댓글 쓰기 기능
	public void commentCreate(ArticleDTO articleDTO);
	
	/**
	 * 조영호
	 * @param articleDTO
	 * @return 성공여부
	 * 대댓글 등록하기 (2023-08-09)
	 */
	public boolean setRReply(ArticleDTO articleDTO);
	
	/**
	 * 조영호
	 * @param groupNo
	 * @return 해당그룹의 모든 글
	 * 게시글 상세보기 (2023-08-09)
	 */
	public List<ArticleDTO> readArticle(int groupNo);
	
	/**
	 * 조영호
	 * @param passwd
	 * @param articleId
	 * @return 성공여부
	 * 게시글 삭제(댓글, 대댓글, 게시글) (2023-08-09)
	 */
	public boolean deleteArticle(@Param("passwd") String passwd, @Param("articleId") int articleId);
	
	
	
}
