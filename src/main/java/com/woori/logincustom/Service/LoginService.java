package com.woori.logincustom.Service;

import com.woori.logincustom.Constant.RoleType;
import com.woori.logincustom.DTO.LoginDTO;
import com.woori.logincustom.Entity.LoginEntity;
import com.woori.logincustom.Repository.LoginRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    //로그인 처리
    @Override
    public UserDetails loadUserByUsername(String loginid)
        throws UsernameNotFoundException {
            Optional<LoginEntity> loginEntity = loginRepository.findByLoginid(loginid);
            //Optional로 받은 값은 .get()로 불러와야한다.
            if (loginEntity.isPresent()) {
                return User.withUsername(loginEntity.get().getLoginid())
                        .password(loginEntity.get().getPassword())
                        .roles(loginEntity.get().getRoleType().name())
                        .build();
            } else {
                throw new UsernameNotFoundException(loginid+"오류!!!!!");
            }
        }

    //회원가입(가입폼->DTO->Entity->암호화->저장)
    public void saveUser(LoginDTO loginDTO){
        Optional<LoginEntity> read = loginRepository.findByLoginid(loginDTO.getLoginid());

        if (read.isPresent()) {  //해당 ID의 존재여부를 판단
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        //해당 ID가 존재하지 않으면
        String password = passwordEncoder.encode(loginDTO.getPassword());
        LoginEntity loginEntity = modelMapper.map(loginDTO, LoginEntity.class);
        //password에 암호화한 비밀번호로 다시 저장
        loginEntity.setPassword(password);
        //회원가입시 기본 권한(USER) 설정 -> 차후 관리자 페이지에서 관리자 등록 재조정
        loginEntity.setRoleType(RoleType.USER);

        loginRepository.save(loginEntity);
    }
}
