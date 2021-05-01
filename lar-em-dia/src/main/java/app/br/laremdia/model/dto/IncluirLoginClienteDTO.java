package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.LoginClienteEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class IncluirLoginClienteDTO {

    private String  nome;
    private String  cpf;
    private String  email;
    private String  celular;
    private String  senha;
    private String  endereco;
    private String  numero;
    private String  bairro;
    private String  complemento;
    private String  referencia;
    private Boolean ativo;
    private Integer idEstado;
    private Integer idMunicipio;

    public static IncluirLoginClienteDTO create(LoginClienteEntity loginClienteEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(loginClienteEntity, IncluirLoginClienteDTO.class);
    }

}
