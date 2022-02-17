package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일) 
// @Controller


// 사용자가 요청 -> 응답(Data) String을 return 
@RestController
public class HttpControllerTest {

	// 인터넷 브라우저를 통한 요청은 get요청밖에 할 수 없다.  
	// http://localhost:8080/http/get (select )
	@GetMapping("/http/get")
	// 하나하나 지정할 수 있지만 생성자 메소드로 전체 수신할 수 있다. 
	//public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/post (insert)
	// 평문 >> text/plain >> @RequestBody String text
	@PostMapping("/http/post") 
	public String postTest(@RequestBody Member m) { // Json으로 전송 시 자동으로 MessageConverter 수행한다. (스프링부트)
		// return "post 요청 : " + text;
		return "post 요청 : "  + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "  + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
