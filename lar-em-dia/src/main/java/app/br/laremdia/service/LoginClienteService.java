package app.br.laremdia.service;

import app.br.laremdia.model.dto.IncluirLoginClienteDTO;
import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import app.br.laremdia.model.repository.EstadoAtendidoRepository;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.model.repository.MunicipioAtendidoRepository;
import app.br.laremdia.rest.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginClienteService implements UserDetailsService  {

    @Autowired
    private LoginClienteRepository loginClienteRepository;

    @Autowired
    private EstadoAtendidoRepository estadoAtendidoRepository;

    @Autowired
    private MunicipioAtendidoRepository municipioAtendidoRepository;

    public LoginClienteService() {
    }

    public IncluirLoginClienteDTO incluir(IncluirLoginClienteDTO incluirLoginClienteDTO){
        boolean exists = loginClienteRepository.existsByEmail(incluirLoginClienteDTO.getEmail());
        if(exists){
            throw new UsuarioException(incluirLoginClienteDTO.getEmail());
        }
        LoginClienteEntity clienteEntity = new LoginClienteEntity();
        clienteEntity.setAtivo(incluirLoginClienteDTO.getAtivo());
        clienteEntity.setBairro(incluirLoginClienteDTO.getBairro());
        clienteEntity.setCelular(incluirLoginClienteDTO.getCelular());
        clienteEntity.setComplemento(incluirLoginClienteDTO.getComplemento());
        clienteEntity.setCpf(incluirLoginClienteDTO.getCpf());
        clienteEntity.setEmail(incluirLoginClienteDTO.getEmail());
        clienteEntity.setEndereco(incluirLoginClienteDTO.getEndereco());
        clienteEntity.setNome(incluirLoginClienteDTO.getNome());
        clienteEntity.setNumero(incluirLoginClienteDTO.getNumero());
        clienteEntity.setReferencia(incluirLoginClienteDTO.getReferencia());
        clienteEntity.setSenha(incluirLoginClienteDTO.getSenha());
        Optional< EstadoAtendidoEntity > entityEstado = estadoAtendidoRepository.findById(incluirLoginClienteDTO.getIdEstado());
        Optional< MunicipioAtendidoEntity > entityMunicipio = municipioAtendidoRepository.findById(incluirLoginClienteDTO.getIdMunicipio());
        clienteEntity.setEstadoAtendido(entityEstado.get());
        clienteEntity.setMunicipioAtendido(entityMunicipio.get());
        return IncluirLoginClienteDTO.create(loginClienteRepository.save(clienteEntity));
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
