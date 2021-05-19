package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.LoginClienteEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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
    private String  referencia;
    private byte[]  foto;
    private Boolean ativo;
    private EstadoAtendidoDTO    estadoAtendidoDTO;
    private MunicipioAtendidoDTO municipioAtendidoDTO;
    private PedidoContratadoDTO  pedidoContratadoDTO;

    public LoginClienteDTO(LoginClienteEntity loginClienteEntity){
        BeanUtils.copyProperties(loginClienteEntity, this);

        if(loginClienteEntity.getEstadoAtendido() != null){
            estadoAtendidoDTO = new EstadoAtendidoDTO();
            BeanUtils.copyProperties(loginClienteEntity.getEstadoAtendido(), estadoAtendidoDTO);
        }

        if(loginClienteEntity.getMunicipioAtendido() != null){
            municipioAtendidoDTO = new MunicipioAtendidoDTO();
            BeanUtils.copyProperties(loginClienteEntity.getMunicipioAtendido(), municipioAtendidoDTO);
        }

        if(loginClienteEntity.getPedidoContratados() != null){
            pedidoContratadoDTO = new PedidoContratadoDTO();
            BeanUtils.copyProperties(loginClienteEntity.getPedidoContratados(), pedidoContratadoDTO);
        }

    }

    public LoginClienteDTO(){}

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    public MunicipioAtendidoDTO getMunicipioAtendidoDTO() {
        return municipioAtendidoDTO;
    }

    public void setMunicipioAtendidoDTO(MunicipioAtendidoDTO municipioAtendidoDTO) {
        this.municipioAtendidoDTO = municipioAtendidoDTO;
    }

    public PedidoContratadoDTO getPedidoContratadoDTO() {
        return pedidoContratadoDTO;
    }

    public void setPedidoContratadoDTO(PedidoContratadoDTO pedidoContratadoDTO) {
        this.pedidoContratadoDTO = pedidoContratadoDTO;
    }
}
