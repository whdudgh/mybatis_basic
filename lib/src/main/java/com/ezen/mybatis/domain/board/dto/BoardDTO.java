package com.ezen.mybatis.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Board DTO클래스
 * @author 조영호
 * build어노테이션도 추가
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDTO {
	
	private int boardId;
	private int category;
	private String title;
	private String description;
	
}
