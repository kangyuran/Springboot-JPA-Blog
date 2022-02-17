package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입. (DI)
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당id는 db에 없습니다.";
		}
		return "삭제되었습니다. id : " + id ;
	}
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다. 
	// email, password
	@Transactional // 함수 종료시에 자동 commit이 됨. >> 영속화된 데이터(1차캐시에 적용된 데이터)가 db에 반영된다. 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // Json 데이터를 요청 -> Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아준다.)
		
		System.out.println("id  : " + id);
		System.out.println("password  : " + requestUser.getPassword());
		System.out.println("email  : " + requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(()-> {			// 영속화 된 시점. 
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());								// 영속화된 데이터를 변경했기때문에 @Transactional 로 인하여 변경을 자동으로 시켜준다.
		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);
		
//		더티 체킹.. : 변경을 감지하여 db에 적용시켜준다. ( 찌꺼기같은것을 체크하여 적용한 다. )
		
		return user;
	}
	
	// http://localhost:8080/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// http://localhost:8080/blog/dummy/user
	// http://localhost:8080/blog/dummy/user?page=1&size=2 >> 0부터 시작
	// 한페이지당 2건에 데이터를 리턴받는다. 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		if(pagingUser.isFirst()) {
			System.out.println("첫번째 페이지");
		} else if(pagingUser.isLast()) {
			System.out.println("마지막 페이지");
		}
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// http://localhost:8080/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//user/4를 찾으면 내가 데이터베이스에서 못찾아오게되면 user가 null이 될꺼아니야..?
		// 그럼 return null 이 리턴되잖아.. 그럼 오류가 발생하겠지.?
		// Optional로 나의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해.!!
		
		// User user = userRepository.findById(id).get(); // 꼭 있을 경우..
		
		
//		있을경우 있는 값이 반환됨. 없을경우 빈객체를 return 
// interface는 new 할 수 없다. ( new 하고싶거든.. 익명클래스를 만들어야한다. ) -> 함수를 오버라이딩해주면 객제생성이 가능하다. 
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				// TODO Auto-generated method stub
//				return new User();
//			}
//		});
		
//		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다.( id : " + id + ")");
//		});

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.( id : " + id + ")");
			}
		});
		
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트.
		// 변환 : 웹브라우저가 이해할 수 있는 데이터 -> json (Gson 라이브러리)
		// 스프링부트 : MessageConverter라는 애가 응답시에 자동작성한다. 
		// 만약에 자바 오브젝트를 리턴하게 되면  MessageConverter가 Jackson 라이브러리를 호출해서 
		// user 오브젝트를 json으로 변환해서 브라우저에서 확인 할 수 있다. 
		return user;
	}
	
	
	// http://localhost:8080/blog/dummy/join (요청) 
	@PostMapping("/dummy/join")
	public String join(User user) {
	
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		
		// user.setRole("user");
		
		user.setRole(RoleType.USER);
	
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
