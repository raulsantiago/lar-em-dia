package app.br.laremdia.service;

import app.br.laremdia.model.dto.AlterarTipoServicoDTO;
import app.br.laremdia.model.dto.IncluirTipoServicoDTO;
import app.br.laremdia.model.dto.TipoServicoDTO;
import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import app.br.laremdia.model.entity.TipoServicoEntity;
import app.br.laremdia.model.repository.ServicoProfissionalRepository;
import app.br.laremdia.model.repository.TipoServicoRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoServicoService {

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @Autowired
    private ServicoProfissionalRepository servicoProfissionalRepository;

    public List< TipoServicoDTO > listar(){
        return  tipoServicoRepository.listar().stream().map(TipoServicoDTO::new).collect(Collectors.toList());
    }

    public TipoServicoDTO consultar(Integer id){
        Optional< TipoServicoEntity > tipoServicoEntity = tipoServicoRepository.findById(id);
        return tipoServicoEntity.map(TipoServicoDTO::new).orElseThrow(() -> new BusinessException("Tipo serviço não encontrado."));
    }

    public IncluirTipoServicoDTO inserir(IncluirTipoServicoDTO tipoServicoDTO){
        TipoServicoEntity tipoServicoEntity = new TipoServicoEntity();
        tipoServicoEntity.setNome(tipoServicoDTO.getNome());
        tipoServicoEntity.setPreco(tipoServicoDTO.getPreco());
        Optional<ServicoProfissionalEntity> servico = servicoProfissionalRepository.findById(tipoServicoDTO.getIdServico());
        ServicoProfissionalEntity servicoProfissional = servico.get();
        tipoServicoEntity.setServicoProfissional(servicoProfissional);
        return IncluirTipoServicoDTO.create(tipoServicoRepository.save(tipoServicoEntity));
    }

    public AlterarTipoServicoDTO alterar(AlterarTipoServicoDTO tipoServicoDTO, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<TipoServicoEntity> tipo =  tipoServicoRepository.findById(id);
        if(tipo.isPresent()){
            TipoServicoEntity tipoServico = tipo.get();
            tipoServico.setNome(tipoServicoDTO.getNome());
            tipoServico.setPreco(tipoServicoDTO.getPreco());
            tipoServicoRepository.save(tipoServico);
            return AlterarTipoServicoDTO.create(tipoServico);
        }else {
            return null;
        }
    }

}
