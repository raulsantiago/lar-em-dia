package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.TipoServicoEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
public class TipoServicoDTO {

    private Integer                idTipo;
    private String                 nome;
    private BigDecimal             preco;
    private ServicoProfissionalDTO servicoProfissionalDTO;
    private PedidoContratadoDTO    pedidoContratadoDTO;

    public TipoServicoDTO(TipoServicoEntity tipoServicoEntity){
        BeanUtils.copyProperties(tipoServicoEntity, this);

        if(tipoServicoEntity.getServicoProfissional() != null){
            servicoProfissionalDTO = new ServicoProfissionalDTO();
            BeanUtils.copyProperties(tipoServicoEntity.getServicoProfissional(), servicoProfissionalDTO);
        }

        if(tipoServicoEntity.getPedidoContratados() != null){
            pedidoContratadoDTO = new PedidoContratadoDTO();
            BeanUtils.copyProperties(tipoServicoEntity.getPedidoContratados(), pedidoContratadoDTO);
        }
    }

    public TipoServicoDTO() {
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ServicoProfissionalDTO getServicoProfissionalDTO() {
        return servicoProfissionalDTO;
    }

    public void setServicoProfissionalDTO(ServicoProfissionalDTO servicoProfissionalDTO) {
        this.servicoProfissionalDTO = servicoProfissionalDTO;
    }

    public PedidoContratadoDTO getPedidoContratadoDTO() {
        return pedidoContratadoDTO;
    }

    public void setPedidoContratadoDTO(PedidoContratadoDTO pedidoContratadoDTO) {
        this.pedidoContratadoDTO = pedidoContratadoDTO;
    }

}