package app.br.laremdia.rest.exception;

import lombok.Getter;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    // Padrao para gerar as mensagens de excessão no Front-End com JSON junto com a classe InternacionalizacaoConfig

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors){
        this.errors = errors;
    }

    public ApiErrors(String message){
        this.errors = Arrays.asList(message);
    }

}
