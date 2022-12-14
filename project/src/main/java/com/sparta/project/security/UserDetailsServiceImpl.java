//package com.sparta.project.security;
//
//import com.sparta.project.entity.User;
//import com.sparta.project.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
//        User user = userRepository.findByNickname(nickname)
//                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + nickname));
//
//        return new UserDetailsImpl(user);
//    }
//}
