package app.br.laremdia.service;

import app.br.laremdia.model.dto.IncluirPedidoContratadoDTO;
import app.br.laremdia.model.dto.PedidoContratadoDTO;
import app.br.laremdia.model.entity.AgendaEntity;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.PedidoContratadoEntity;
import app.br.laremdia.model.entity.TipoServicoEntity;
import app.br.laremdia.model.projection.PedidoContratadoProjection;
import app.br.laremdia.model.repository.AgendaRepository;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.model.repository.PedidoContratadoRepository;
import app.br.laremdia.model.repository.TipoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoContratadoService {

    @Autowired
    private PedidoContratadoRepository pedidoContratadoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private LoginClienteRepository loginClienteRepository;

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

//    public List< PedidoContratadoDTO > pedidosPorIdCliente(Integer idCliente){
//        return pedidoContratadoRepository.pedidosPorIdCliente(idCliente).stream().map(PedidoContratadoDTO::new).collect(Collectors.toList());
//    }

    public List< PedidoContratadoProjection > pedidosPorIdCliente(Integer idCliente) {
        return pedidoContratadoRepository.pedidosPorIdCliente(idCliente);
    }

    public IncluirPedidoContratadoDTO inserir(IncluirPedidoContratadoDTO incluirPedidoContratadoDTO){
        PedidoContratadoEntity pedidoContratadoEntity = new PedidoContratadoEntity();
        pedidoContratadoEntity.setDescricao(incluirPedidoContratadoDTO.getDescricao());
        pedidoContratadoEntity.setLocal(incluirPedidoContratadoDTO.getLocal());
        pedidoContratadoEntity.setPrecoContratado(incluirPedidoContratadoDTO.getPrecoContratado());
        pedidoContratadoEntity.setSituacao(incluirPedidoContratadoDTO.getSituacao());
        Optional< AgendaEntity > agendaEntity = agendaRepository.findById(incluirPedidoContratadoDTO.getIdAgenda());
        pedidoContratadoEntity.setAgenda(agendaEntity.get());
        Optional< LoginClienteEntity > loginClienteEntity =  loginClienteRepository.findById(incluirPedidoContratadoDTO.getIdCliente());
        pedidoContratadoEntity.setLoginCliente(loginClienteEntity.get());
        Optional< TipoServicoEntity > tipoServicoEntity = tipoServicoRepository.findById(incluirPedidoContratadoDTO.getIdTipo());
        pedidoContratadoEntity.setTipoServico(tipoServicoEntity.get());
        return IncluirPedidoContratadoDTO.create(pedidoContratadoRepository.save(pedidoContratadoEntity));
    }

}
