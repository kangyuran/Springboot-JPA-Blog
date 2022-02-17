package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;

// 나중에 Spring Security가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 한다.
@Getter
public class PrincipalDetail implements UserDetails {

    private User user; // 객체를 품고있는것을 콤포지션.

    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴한다. (true : 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않았는지 리턴한다. (true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다. (true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 리턴한다. (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }


    // 계정이 갖고있는 권한목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야하는데 여기서는 일단 한개기때문에..)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
//      어차피 해당객체의 클래스가 하나밖에 없는 경우 하단 람다식으로 표한한다.
//        collectors.add(new GrantedAuthority() { // 익명클래스가 만들어지고
//
//            @Override
//            public String getAuthority() {
//                return "ROLE_" + user.getRole(); // 이 형식으로 해주어야 스프링이 인식을 한다. ([ROLE_] 은 PREFIX 형식으로 꼭 붙여주어야함.)
//            }
//        });
//
        collectors.add(()->{ return "ROLE_" + user.getRole();});
        return collectors;
    }

}
