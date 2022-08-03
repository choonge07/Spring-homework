//package com.sparta.project.security;
//
//import com.sparta.project.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class UserDetailsImpl implements UserDetails {
//    private final User user;
//
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    @Override // 계정의 이름을 리턴한다.
//    public String getUsername() {
//        return user.getNickname();
//    }
//
//    @Override // 계정의 패스워드를 리턴한다.
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override // 계정이 만료되지 않았는지를 리턴한다(true를 리턴하면 만료되지 않음을 의미)
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override //  계정이 잠겨잇지 않은지를 리턴한다(true를 리턴하면 계정이 잠겨있지 않음을 의미)
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override // 계정의 패스워드가 만료되지 않았는지를 리턴한다(true를 리턴하면 패스워드가 만료되지 않음을 의미)
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override // 계정이 사용가능한 계정인지를 리턴한다(true를 리턴하면 사용가능한 계정인지를 의미)
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override // 계정이 갖고 있는 권한 목록을 리턴한다.
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//    }
//}
