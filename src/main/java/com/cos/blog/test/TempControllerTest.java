package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 파일을 리턴
// Spring은 JSP를 
@Controller
public class TempControllerTest {

	// application.yml >> server:port:8000 >> server:servlet:context-path:/blog
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		
		// application.yml 의 spring:mvc:view:prefix 설정값인 [/WEB-INF/views/] 를 붙여 /WEB-INF/views/home.html 이곳으로 찾아간다. 
		// application.yml 의 spring:mvc:view:suffix: .jsp 이렇게 되어있어 return 경로의 .jsp 파일을 찾아간다. 
		// Controller Annotation 을 사용하면 파일리턴 기본경로 : src/main/resources/static >> 때문에 return 경로 앞에 /를 붙여주어야 찾아간다.
		// static 디렉토리 경로 아래에는 정적파일 만 놓아야한다. ( 브라우저가 인식할 수 있는 파일 )
		// jsp파일같은 동적인파일 (컴파일이 일어나야하는 파일)의 경우 
		return "/home.html"; 
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/best-invoice-plugins-6.png";
	}

	@GetMapping("/temp/jsp")
	public String tempJsp() {
		
		return "Test";
	}
	
	@GetMapping("/example/flexbox")
	public String exampleFlexbox() {

		return "exampleFlexbox";
	}
}
