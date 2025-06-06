package likelion.portmate.domain.member.service;

import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository repo;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = repo.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));

        return User.withUsername(member.getLoginId())
                .password(member.getPassword())
                .roles("USER")
                .build();
    }
}