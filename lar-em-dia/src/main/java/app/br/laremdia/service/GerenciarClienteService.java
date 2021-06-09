package app.br.laremdia.service;

import app.br.laremdia.model.dto.AgendaDTO;
import app.br.laremdia.model.dto.IncluirLoginClienteDTO;
import app.br.laremdia.model.dto.LoginClienteDTO;
import app.br.laremdia.model.entity.AgendaEntity;
import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import app.br.laremdia.model.repository.EstadoAtendidoRepository;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.model.repository.MunicipioAtendidoRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class GerenciarClienteService {

    @Autowired
    private LoginClienteRepository loginClienteRepository;

    @Autowired
    private EstadoAtendidoRepository estadoAtendidoRepository;

    @Autowired
    private MunicipioAtendidoRepository municipioAtendidoRepository;

    public LoginClienteDTO consultarEmail(String email) {
        Optional<LoginClienteEntity> loginClienteEntity = loginClienteRepository.findByEmail(email);
        return loginClienteEntity.map(LoginClienteDTO::new).orElseThrow(() -> new BusinessException("Cliente não encontrado."));
    }

    public LoginClienteDTO consultar(Integer id) {
        Optional< LoginClienteEntity > loginClienteEntity = loginClienteRepository.findById(id);
        return loginClienteEntity.map(LoginClienteDTO::new).orElseThrow(() -> new BusinessException("Cliente não encontrado."));
    }

    public IncluirLoginClienteDTO alterar(IncluirLoginClienteDTO incluirLoginClienteDTO, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<LoginClienteEntity> cliente = loginClienteRepository.findById(id);
        if(cliente.isPresent()){
            LoginClienteEntity loginCliente = cliente.get();
            loginCliente.setNome(incluirLoginClienteDTO.getNome());
            loginCliente.setCpf(incluirLoginClienteDTO.getCpf());
            loginCliente.setEmail(incluirLoginClienteDTO.getEmail());
            loginCliente.setCelular(incluirLoginClienteDTO.getCelular());
            loginCliente.setSenha(incluirLoginClienteDTO.getSenha());
            loginCliente.setEndereco(incluirLoginClienteDTO.getEndereco());
            loginCliente.setNumero(incluirLoginClienteDTO.getNumero());
            loginCliente.setBairro(incluirLoginClienteDTO.getBairro());
            loginCliente.setComplemento(incluirLoginClienteDTO.getComplemento());
            loginCliente.setReferencia(incluirLoginClienteDTO.getReferencia());
            loginCliente.setAtivo(incluirLoginClienteDTO.getAtivo());
            Optional< EstadoAtendidoEntity > entityEstado = estadoAtendidoRepository.findById(incluirLoginClienteDTO.getIdEstado());
            Optional< MunicipioAtendidoEntity > entityMunicipio = municipioAtendidoRepository.findById(incluirLoginClienteDTO.getIdMunicipio());
            loginCliente.setEstadoAtendido(entityEstado.get());
            loginCliente.setMunicipioAtendido(entityMunicipio.get());
            return IncluirLoginClienteDTO.create(loginClienteRepository.save(loginCliente));
        } else {
            return null;
        }
    }

}
