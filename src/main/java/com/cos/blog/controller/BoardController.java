package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {


	@Autowired
	private BoardService boardService;


	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) { // (@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션을 어떻게 찾는지.?
//		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());

		// 리스트 형태로 전송하고자 할때..
//		Page<Board> pagingBoard = boardService.list(pageable);
//		List<Board> listBoard = pagingBoard.getContent();
//
//		model.addAttribute("boards", listBoard);

		// 페이지 그대로 전송 해도 됨.
		model.addAttribute("boards", boardService.list(pageable));

		return "index";
	}

	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model){
		model.addAttribute("board", boardService.boardDetail(id));

		return "board/detail";
	}


	@GetMapping("/board/saveForm2")
	public String saveForm(){
		return "board/saveForm2";
	}


	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model){
		model.addAttribute("board", boardService.boardDetail(id));

		return "board/updateForm";
	}

}
