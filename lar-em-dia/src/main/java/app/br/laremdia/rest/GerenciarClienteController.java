package app.br.laremdia.rest;

import app.br.laremdia.model.dto.LoginClienteDTO;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.service.GerenciarClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/gerenciarcliente")
@RequiredArgsConstructor
public class GerenciarClienteController {

    private final GerenciarClienteService gerenciarClienteService;

    @GetMapping("/{email}/email")
    public ResponseEntity consultarEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(gerenciarClienteService.consultarEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") Integer id, @RequestBody @Valid LoginClienteEntity loginClienteEntity){
        loginClienteEntity.setIdCliente(id);
        LoginClienteDTO loginClienteDTO = gerenciarClienteService.alterar(loginClienteEntity, id);
        return loginClienteDTO != null ? ResponseEntity.ok(loginClienteDTO) : ResponseEntity.notFound().build();
    }

}
