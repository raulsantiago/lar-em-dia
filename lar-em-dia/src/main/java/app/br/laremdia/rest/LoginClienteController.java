package app.br.laremdia.rest;

import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.rest.exception.UsuarioException;
import app.br.laremdia.service.LoginClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/logincliente")
@RequiredArgsConstructor
public class LoginClienteController {

    private final LoginClienteService loginClienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginClienteEntity incluir(@RequestBody @Valid LoginClienteEntity loginClienteEntity){
        try{
            return loginClienteService.incluir(loginClienteEntity);
        }catch (UsuarioException e){
            e.printStackTrace();
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }

}
