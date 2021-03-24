package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.LoginProfissionalEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class LoginProfissionalDTO {

    private Integer idProfissional;
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private String senha;
    private byte[] foto;
    private Boolean ativo;

    public static LoginProfissionalDTO create(LoginProfissionalEntity loginProfissionalEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(loginProfissionalEntity, LoginProfissionalDTO.class);
    }

}
