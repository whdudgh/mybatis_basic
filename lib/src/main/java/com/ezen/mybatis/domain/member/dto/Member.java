package com.ezen.mybatis.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 김기정
 *
 */

//@Data(생성 전부해주지만 속성을 넣은 생성자는 생성없어서, AllArgsConstructor를 넣으면 또 일반생성자가 없어짐. 그래서 잘안씀.)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder//내가원하는 생성자가 없을 때(난 멤버에 id, passwd, name만 넣고 생성하고 싶은데 생성자가 없는 상황.)
public class Member {
	
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String regdate;
}













