package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MunicipioAtendidoDTO {

    private Integer             idMunicipio;
    private String              municipio;
    private Boolean             ativo;
    private EstadoAtendidoDTO   estadoAtendidoDTO;

    public MunicipioAtendidoDTO(MunicipioAtendidoEntity municipioAtendidoEntity){
        BeanUtils.copyProperties(municipioAtendidoEntity, this);

        if(municipioAtendidoEntity.getEstado() != null){
            estadoAtendidoDTO = new EstadoAtendidoDTO();
            BeanUtils.copyProperties(municipioAtendidoEntity.getEstado(), estadoAtendidoDTO);
        }

    }

    public MunicipioAtendidoDTO() {
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EstadoAtendidoDTO getEstadoAtendidoDTO() {
        return estadoAtendidoDTO;
    }

    public void setEstadoAtendidoDTO(EstadoAtendidoDTO estadoAtendidoDTO) {
        this.estadoAtendidoDTO = estadoAtendidoDTO;
    }

}
