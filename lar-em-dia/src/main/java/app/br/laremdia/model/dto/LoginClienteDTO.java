package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.LoginClienteEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class LoginClienteDTO {

    private Integer idCliente;
    private String  nome;
    private String  cpf;
    private String  email;
    private String  celular;
    private String  senha;
    private String  endereco;
    private String  numero;
    private String  bairro;
    private String  complemento;
    private String  estado;
    private String  municipio;
    private String  referencia;
    private byte[]  foto;
    private Boolean ativo;

    public static LoginClienteDTO create(LoginClienteEntity loginClienteEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(loginClienteEntity, LoginClienteDTO.class);
    }


}
