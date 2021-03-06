package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.PedidoContratadoEntity;
import app.br.laremdia.model.projection.PedidoContratadoProjection;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoContratadoDTO {

    private Integer         idPedido;
    private String          descricao;
    private String          local;
    private LocalDateTime   dataHoraInicio;
    private LocalDateTime   dataHoraFim;
    private BigDecimal      despesas;
    private Boolean         situacao;
    private BigDecimal      precoContratado;
    private AgendaDTO       agendaDTO;
    private TipoServicoDTO  tipoServicoDTO;
    private LoginClienteDTO loginClienteDTO;

    public PedidoContratadoDTO(PedidoContratadoProjection pedidoContratadoProjection){
        this.idPedido = pedidoContratadoProjection.getIdPedido();
        this.dataHoraFim = pedidoContratadoProjection.getDataFim();
        this.precoContratado = pedidoContratadoProjection.getPrecoContratado();

        LoginClienteDTO loginClienteDTO = new LoginClienteDTO();
        loginClienteDTO.setIdCliente(pedidoContratadoProjection.getIdCliente());

        ServicoProfissionalDTO servicoProfissionalDTO = new ServicoProfissionalDTO();
        servicoProfissionalDTO.setIdServico(pedidoContratadoProjection.getIdServico());
        servicoProfissionalDTO.setNome(pedidoContratadoProjection.getNomeServico());

        TipoServicoDTO tipoServicoDTO = new TipoServicoDTO();
        tipoServicoDTO.setIdTipo(pedidoContratadoProjection.getIdTipoServico());
        tipoServicoDTO.setNome(pedidoContratadoProjection.getNomeTipoServico());
        tipoServicoDTO.setServicoProfissionalDTO(servicoProfissionalDTO);

        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setIdAgenda(pedidoContratadoProjection.getIdAgenda());
        agendaDTO.setDia(pedidoContratadoProjection.getDia());
        agendaDTO.setTurno(pedidoContratadoProjection.getTurno());

    }

    public PedidoContratadoDTO(PedidoContratadoEntity pedidoContratadoEntity){
        BeanUtils.copyProperties(pedidoContratadoEntity, this);

        if(pedidoContratadoEntity.getAgenda() != null){
            this.agendaDTO = new AgendaDTO();
            this.agendaDTO.setIdAgenda(pedidoContratadoEntity.getAgenda().getIdAgenda());
            this.agendaDTO.setDia(pedidoContratadoEntity.getAgenda().getDia());
            this.agendaDTO.setTurno(pedidoContratadoEntity.getAgenda().getTurno());
            this.agendaDTO.setDisponivel(pedidoContratadoEntity.getAgenda().getDisponivel());
        }

        if(pedidoContratadoEntity.getTipoServico() != null){
            ServicoProfissionalDTO servicoProfissionalDTO = new ServicoProfissionalDTO();
            this.tipoServicoDTO = new TipoServicoDTO();
            this.tipoServicoDTO.setIdTipo(pedidoContratadoEntity.getTipoServico().getIdTipo());
            this.tipoServicoDTO.setNome(pedidoContratadoEntity.getTipoServico().getNome());
            this.tipoServicoDTO.setPreco(pedidoContratadoEntity.getTipoServico().getPreco());
            this.tipoServicoDTO.setServicoProfissionalDTO(servicoProfissionalDTO);
        }

        if(pedidoContratadoEntity.getLoginCliente() != null){
            EstadoAtendidoDTO estadoAtendidoDTO = new EstadoAtendidoDTO();
            MunicipioAtendidoDTO municipioAtendidoDTO = new MunicipioAtendidoDTO();
            this.loginClienteDTO = new LoginClienteDTO();
            this.loginClienteDTO.setIdCliente(pedidoContratadoEntity.getLoginCliente().getIdCliente());
            this.loginClienteDTO.setAtivo(pedidoContratadoEntity.getLoginCliente().getAtivo());
            this.loginClienteDTO.setBairro(pedidoContratadoEntity.getLoginCliente().getBairro());
            this.loginClienteDTO.setCelular(pedidoContratadoEntity.getLoginCliente().getCelular());
            this.loginClienteDTO.setComplemento(pedidoContratadoEntity.getLoginCliente().getComplemento());
            this.loginClienteDTO.setCpf(pedidoContratadoEntity.getLoginCliente().getCpf());
            this.loginClienteDTO.setEmail(pedidoContratadoEntity.getLoginCliente().getEmail());
            this.loginClienteDTO.setEndereco(pedidoContratadoEntity.getLoginCliente().getEndereco());
            this.loginClienteDTO.setNome(pedidoContratadoEntity.getLoginCliente().getNome());
            this.loginClienteDTO.setNumero(pedidoContratadoEntity.getLoginCliente().getNumero());
            this.loginClienteDTO.setReferencia(pedidoContratadoEntity.getLoginCliente().getReferencia());
            this.loginClienteDTO.setEstadoAtendidoDTO(estadoAtendidoDTO);
            this.loginClienteDTO.setMunicipioAtendidoDTO(municipioAtendidoDTO);
        }

    }

    public PedidoContratadoDTO(){}

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public BigDecimal getDespesas() {
        return despesas;
    }

    public void setDespesas(BigDecimal despesas) {
        this.despesas = despesas;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getPrecoContratado() {
        return precoContratado;
    }

    public void setPrecoContratado(BigDecimal precoContratado) {
        this.precoContratado = precoContratado;
    }

    public AgendaDTO getAgendaDTO() {
        return agendaDTO;
    }

    public void setAgendaDTO(AgendaDTO agendaDTO) {
        this.agendaDTO = agendaDTO;
    }

    public TipoServicoDTO getTipoServicoDTO() {
        return tipoServicoDTO;
    }

    public void setTipoServicoDTO(TipoServicoDTO tipoServicoDTO) {
        this.tipoServicoDTO = tipoServicoDTO;
    }

    public LoginClienteDTO getLoginClienteDTO() {
        return loginClienteDTO;
    }

    public void setLoginClienteDTO(LoginClienteDTO loginClienteDTO) {
        this.loginClienteDTO = loginClienteDTO;
    }
}
