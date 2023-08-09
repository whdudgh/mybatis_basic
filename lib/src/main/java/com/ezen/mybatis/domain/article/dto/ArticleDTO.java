package com.ezen.mybatis.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Article DTO클래스
 * @author 조영호
 * build어노테이션 사용.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ArticleDTO {
	
	private int articleId;
	private int boardId;
	private String writer;
	private String subject;
	private String content;
	private String regdate;
	private int hitcount;
	private String passwd;
	private int groupNo;
	private int levelNo;
	private int orderNo;
	
}
