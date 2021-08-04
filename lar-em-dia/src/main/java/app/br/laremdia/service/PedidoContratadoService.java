package app.br.laremdia.service;

import app.br.laremdia.model.dto.AlterarPedidoContratadoDTO;
import app.br.laremdia.model.dto.IncluirPedidoContratadoDTO;
import app.br.laremdia.model.dto.PedidoContratadoDTO;
import app.br.laremdia.model.entity.AgendaEntity;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.PedidoContratadoEntity;
import app.br.laremdia.model.entity.TipoServicoEntity;
import app.br.laremdia.model.projection.PedidoContratadoGraficoProjection;
import app.br.laremdia.model.projection.PedidoContratadoLucroHoraProjection;
import app.br.laremdia.model.projection.PedidoContratadoProfissionalProjection;
import app.br.laremdia.model.projection.PedidoContratadoProjection;
import app.br.laremdia.model.repository.AgendaRepository;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.model.repository.PedidoContratadoRepository;
import app.br.laremdia.model.repository.TipoServicoRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

    public PedidoContratadoDTO consultar(Integer id) {
        Optional< PedidoContratadoEntity > pedidoContratadoEntity = pedidoContratadoRepository.findById(id);
        return pedidoContratadoEntity.map(PedidoContratadoDTO::new).orElseThrow(() -> new BusinessException("Pedido não encontrada."));
    }

    public List< PedidoContratadoProjection > pedidosPorIdCliente(Integer idCliente) {
        return pedidoContratadoRepository.pedidosPorIdCliente(idCliente);
    }

    public List< PedidoContratadoProfissionalProjection > listaPedidosViewProfissional() {
        return pedidoContratadoRepository.listaPedidosViewProfissional();
    }

    public List< PedidoContratadoGraficoProjection > listaPedidosGrafico(String periodoInicial, String periodoFinal) {
        LocalDateTime inicio = LocalDateTime.parse(periodoInicial);
        LocalDateTime fim = LocalDateTime.parse(periodoFinal);
        return pedidoContratadoRepository.listaPedidosGrafico(inicio, fim);
    }

    public List< PedidoContratadoLucroHoraProjection > listaPedidosLucroHora(String periodoInicial, String periodoFinal) {
        LocalDateTime inicio = LocalDateTime.parse(periodoInicial);
        LocalDateTime fim = LocalDateTime.parse(periodoFinal);
        return pedidoContratadoRepository.listaPedidosLucroHora(inicio, fim);
    }

    public List< PedidoContratadoLucroHoraProjection > listaTodosPedidosLucroHora() {
        return pedidoContratadoRepository.listaTodosPedidosLucroHora();
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

    public void excluirProf(Integer id){
        Optional<PedidoContratadoEntity> pedidoContratadoEntity = pedidoContratadoRepository.findById(id);
        pedidoContratadoRepository.findById(id).map( pedido -> {
            pedidoContratadoRepository.delete(pedido);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));

    }

    public AlterarPedidoContratadoDTO alterar(Integer id, AlterarPedidoContratadoDTO alterarPedidoContratadoDTO){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<PedidoContratadoEntity> pedido = pedidoContratadoRepository.findById(id);
        if(pedido.isPresent()){
            PedidoContratadoEntity pedidoContratadoEntity = pedido.get();
            if(alterarPedidoContratadoDTO.getDespesas() != null){
                pedidoContratadoEntity.setDespesas(alterarPedidoContratadoDTO.getDespesas());
            }
            if(alterarPedidoContratadoDTO.getDataHoraInicio()  != null){
                pedidoContratadoEntity.setDataHoraInicio(LocalDateTime.parse(alterarPedidoContratadoDTO.getDataHoraInicio().toString()));
            }
            if(alterarPedidoContratadoDTO.getDataHoraFim() != null){
                pedidoContratadoEntity.setDataHoraFim(LocalDateTime.parse(alterarPedidoContratadoDTO.getDataHoraFim().toString()));
                pedidoContratadoEntity.setSituacao(alterarPedidoContratadoDTO.getSituacao());
            }
            pedidoContratadoRepository.save(pedidoContratadoEntity);
            return AlterarPedidoContratadoDTO.create(pedidoContratadoEntity);
        } else {
            return null;
        }

    }

}
