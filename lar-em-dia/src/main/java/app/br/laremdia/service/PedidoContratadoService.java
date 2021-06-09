package app.br.laremdia.service;

import app.br.laremdia.model.dto.IncluirPedidoContratadoDTO;
import app.br.laremdia.model.entity.AgendaEntity;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.PedidoContratadoEntity;
import app.br.laremdia.model.entity.TipoServicoEntity;
import app.br.laremdia.model.projection.PedidoContratadoProfissionalProjection;
import app.br.laremdia.model.projection.PedidoContratadoProjection;
import app.br.laremdia.model.repository.AgendaRepository;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.model.repository.PedidoContratadoRepository;
import app.br.laremdia.model.repository.TipoServicoRepository;
import app.br.laremdia.rest.exception.ApiErrors;
import app.br.laremdia.rest.exception.BusinessException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List< PedidoContratadoProjection > pedidosPorIdCliente(Integer idCliente) {
        return pedidoContratadoRepository.pedidosPorIdCliente(idCliente);
    }

    public List< PedidoContratadoProfissionalProjection > listaPedidosViewProfissional() {
        return pedidoContratadoRepository.listaPedidosViewProfissional();
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

    public void excluir(Integer id){
        if( pedidoContratadoRepository.diaAgendado(id).equals(LocalDate.now()) || pedidoContratadoRepository.diaAgendado(id).equals(LocalDate.now().plusDays(1))  ){
            Assert.isTrue(false, "Só pode cancelar um pedido agendado com mais de um dia de antecedência.");
        }
        Optional<PedidoContratadoEntity> pedidoContratadoEntity = pedidoContratadoRepository.findById(id);
        pedidoContratadoRepository.findById(id).map( pedido -> {
            pedidoContratadoRepository.delete(pedido);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));

    }



}
