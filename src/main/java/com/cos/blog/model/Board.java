package com.cos.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; //섬머노트 라이브러리 <HTML>태그가 섞여서 디자인이 됨.

	private int count; // 조회수
	
	// Many=board, one=user >>> board:user > N:1
	@ManyToOne(fetch = FetchType.EAGER) // 기본 패치전략이 가져와라. ( 어차피 FK이고 게시글을 작성 시 userID가 있을터이니.. )  
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 
	// mappedBy 연관관계의 주인이 아니다. (난 FK가 아니니 DB에 컬럼을 만들지 말아라 )
	
	// fetch = FetchType.LAZY -> 화면에서 같이 조회되는것이 아니고 상세보기처럼 다른화면으로 전환 시 보여줄때 지연로딩하라는 의미.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  // 한 화면에 리플데이터를 같이 보여줄 경우 같이 조회해라는 의미. 
	private List<Reply> reply; // left join 한다는 개념으로 보면 될듯.. 
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
}
