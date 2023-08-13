package com.ezen.mybatis.domain.common.web.mapper;

import java.util.List;
import java.util.Map;

import com.ezen.mybatis.domain.article.dto.ArticleDTO;

public interface PaginationMapper {
	/** 게시판 전체 갯수 출력 */
	public int findAllCount();
	
	/** 검색된 게시판 갯수 출력 */
	public int findSearchCount(Map<String, Object> searchArticle);
	
	/** 검색된 게시판 게시글 출력 */
	public List<ArticleDTO> findSearchPage(Map<String, Object> searchArticle);

	/** 계층형 게시판 및 페이징 처리를 위한 게시글목록 조회 */
	public List<ArticleDTO> findSearchArticle(Map<String, Object> searchArticle);
}
