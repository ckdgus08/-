package com.tistory.ckdgus0808;

import com.tistory.ckdgus0808.repository.MemberRepository;
import com.tistory.ckdgus0808.repository.MemoryMemberRepository;
import com.tistory.ckdgus0808.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
