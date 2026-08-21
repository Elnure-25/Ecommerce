package az.itbtechno.ecommerce.security;

import az.itbtechno.ecommerce.models.User;
import az.itbtechno.ecommerce.repostories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
User findUser=userRepository.findByEmail(username);
if(findUser!=null){
    org.springframework.security.core.userdetails.User loggedUser = new org.springframework.security.core.userdetails.User(
            findUser.getEmail(),
            findUser.getPassword(),
            findUser.isEnabled(),
            findUser.isAccountNonExpired(),
            findUser.isCredentialsNonExpired(),
            findUser.isAccountNonLocked(),
            findUser.getAuthorities());
            return loggedUser;
}
        throw new UsernameNotFoundException("Istifadeci tapilmadi");
    }

}
