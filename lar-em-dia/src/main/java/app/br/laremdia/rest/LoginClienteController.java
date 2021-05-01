package app.br.laremdia.rest;

import app.br.laremdia.model.dto.IncluirLoginClienteDTO;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.rest.exception.UsuarioException;
import app.br.laremdia.service.LoginClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity incluir(@RequestBody @Valid IncluirLoginClienteDTO incluirLoginClienteDTO){
        try{
            return ResponseEntity.ok(loginClienteService.incluir(incluirLoginClienteDTO));
        }catch (UsuarioException e){
            e.printStackTrace();
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }

}
