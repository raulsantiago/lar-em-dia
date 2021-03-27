package app.br.laremdia.service;

import app.br.laremdia.model.dto.LoginClienteDTO;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class GerenciarClienteService {

    @Autowired
    private LoginClienteRepository loginClienteRepository;

    public LoginClienteDTO consultarEmail(String email) {
        Optional<LoginClienteEntity> loginClienteEntity = loginClienteRepository.findByEmail(email);
        return loginClienteEntity.map(LoginClienteDTO::create).orElseThrow(() -> new BusinessException("Cliente não encontrado."));
    }

    public LoginClienteDTO alterar(LoginClienteEntity loginClienteEntity, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<LoginClienteEntity> cliente = loginClienteRepository.findById(id);
        if(cliente.isPresent()){
            LoginClienteEntity loginCliente = cliente.get();
            loginCliente.setNome(loginClienteEntity.getNome());
            loginCliente.setCpf(loginClienteEntity.getCpf());
            loginCliente.setEmail(loginClienteEntity.getEmail());
            loginCliente.setCelular(loginClienteEntity.getCelular());
            loginCliente.setSenha(loginClienteEntity.getSenha());
            loginCliente.setEndereco(loginClienteEntity.getEndereco());
            loginCliente.setNumero(loginClienteEntity.getNumero());
            loginCliente.setBairro(loginClienteEntity.getBairro());
            loginCliente.setComplemento(loginClienteEntity.getComplemento());
            loginCliente.setEstado(loginClienteEntity.getEstado());
            loginCliente.setMunicipio(loginClienteEntity.getMunicipio());
            loginCliente.setReferencia(loginClienteEntity.getReferencia());
            loginCliente.setFoto(loginClienteEntity.getFoto());
            loginCliente.setAtivo(loginClienteEntity.getAtivo());
            loginClienteRepository.save(loginCliente);
            return LoginClienteDTO.create(loginCliente);
        } else {
            return null;
        }
    }

}
