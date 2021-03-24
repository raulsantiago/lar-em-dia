package app.br.laremdia.rest.exception;

public class UsuarioException extends RuntimeException  {

    public UsuarioException( String email ){
        super("Usuário já cadastrado para o email " + email);
    }
}
