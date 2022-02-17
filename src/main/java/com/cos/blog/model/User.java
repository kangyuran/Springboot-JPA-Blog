package com.cos.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Table화 시키기 위하여 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술. 
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert insert시에 null인 필드를 제외시켜준다. 
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 
	private int id; // 시퀀스, auto_increment
	  
	@Column(nullable = false, length = 30) 
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") // 홑따옴표를 반드시 붙여주어야한다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. ( 데이터의 도메인을 만들어줄 수 있다. ) // ADMIN, USER  
	
	@CreationTimestamp // 시간이 자동으로 입력이 된다. 
	private LocalDateTime createDate;
	
}