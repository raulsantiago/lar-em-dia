package app.br.laremdia.service;

import app.br.laremdia.model.dto.LoginProfissionalDTO;
import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.model.repository.LoginProfissionalRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GerenciarProfissionalService {

    @Autowired
    private LoginProfissionalRepository loginProfissionalRepository;

    public List<LoginProfissionalDTO> listar() {
        return loginProfissionalRepository.findAll().stream().map(LoginProfissionalDTO::create).collect(Collectors.toList());
    }

    public LoginProfissionalDTO consultar(Integer id) {
        Optional<LoginProfissionalEntity> loginProfissionalEntity = loginProfissionalRepository.findById(id);
        return loginProfissionalEntity.map(LoginProfissionalDTO::create).orElseThrow(() -> new BusinessException("Profissional não encontrado."));
    }

    public LoginProfissionalDTO inserir(LoginProfissionalEntity loginProfissionalEntity){
        Assert.isNull(loginProfissionalEntity.getIdProfissional(), "Não foi possível atualizar o registro");
        return LoginProfissionalDTO.create(loginProfissionalRepository.save(loginProfissionalEntity));
    }

    public LoginProfissionalDTO alterar(LoginProfissionalEntity loginProfissionalEntity, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<LoginProfissionalEntity> profissional = loginProfissionalRepository.findById(id);
        if(profissional.isPresent()){
            LoginProfissionalEntity loginProfissional = profissional.get();
            loginProfissional.setNome(loginProfissionalEntity.getNome());
            loginProfissional.setCpf(loginProfissionalEntity.getCpf());
            loginProfissional.setEmail(loginProfissionalEntity.getEmail());
            loginProfissional.setSenha(loginProfissionalEntity.getSenha());
            loginProfissional.setAtivo(loginProfissionalEntity.getAtivo());
            loginProfissional.setCelular(loginProfissionalEntity.getCelular());
            loginProfissional.setFoto(loginProfissionalEntity.getFoto());
            loginProfissionalRepository.save(loginProfissional);
            return LoginProfissionalDTO.create(loginProfissional);
        } else {
            return null;
        }
    }

    public void excluir(Integer id){
        Optional<LoginProfissionalEntity> loginProfissionalEntity = loginProfissionalRepository.findById(id);
        loginProfissionalRepository.findById(id).map( profissional -> {
            loginProfissionalRepository.delete(profissional);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado."));
    }



}
