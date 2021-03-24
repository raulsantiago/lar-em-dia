package app.br.laremdia.service;

import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.model.repository.LoginProfissionalRepository;
import app.br.laremdia.rest.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginProfissionalService implements UserDetailsService {

    @Autowired
    private LoginProfissionalRepository loginProfissionalRepository;

    public LoginProfissionalEntity incluir(LoginProfissionalEntity loginProfissional){
        boolean exists = loginProfissionalRepository.existsByEmail(loginProfissional.getEmail());
        if(exists){
            throw new UsuarioException(loginProfissional.getEmail());
        }
        return loginProfissionalRepository.save(loginProfissional);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoginProfissionalEntity loginProfissional = loginProfissionalRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email para login não encontrado."));
        return User
                .builder()
                .username(loginProfissional.getEmail())
                .password(loginProfissional.getSenha())
                .roles("USER")
                .build()
                ;
    }

}
