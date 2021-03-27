package app.br.laremdia.service;

import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.rest.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginClienteService implements UserDetailsService  {

    @Autowired
    private LoginClienteRepository loginClienteRepository;

    public LoginClienteEntity incluir(LoginClienteEntity loginClienteEntity){
        boolean exists = loginClienteRepository.existsByEmail(loginClienteEntity.getEmail());
        if(exists){
            throw new UsuarioException(loginClienteEntity.getEmail());
        }
        return loginClienteRepository.save(loginClienteEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoginClienteEntity loginClienteEntity = loginClienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email para login não encontrado."));
        return User
                .builder()
                .username(loginClienteEntity.getEmail())
                .password(loginClienteEntity.getSenha())
                .roles("USER") // posso salvar roles no banco e pegar com .getRoles()
                .build()
                ;
    }


}
