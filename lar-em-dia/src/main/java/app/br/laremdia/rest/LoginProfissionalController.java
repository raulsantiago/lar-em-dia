package app.br.laremdia.rest;

import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.rest.exception.UsuarioException;
import app.br.laremdia.service.LoginProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/loginprofissional")
@RequiredArgsConstructor
public class LoginProfissionalController {

    private final LoginProfissionalService loginProfissionalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginProfissionalEntity incluir(@RequestBody @Valid LoginProfissionalEntity loginProfissional){
        try{
            return loginProfissionalService.incluir(loginProfissional);
        }catch (UsuarioException e){
            e.printStackTrace();
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }

}
