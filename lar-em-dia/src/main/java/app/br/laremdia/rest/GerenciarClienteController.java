package app.br.laremdia.rest;

import app.br.laremdia.model.dto.LoginClienteDTO;
import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.service.GerenciarClienteService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/gerenciarcliente")
@RequiredArgsConstructor
public class GerenciarClienteController {

    private final GerenciarClienteService gerenciarClienteService;
    private final LoginClienteRepository loginClienteRepository;

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

    @PutMapping("{id}/foto")
    public byte[] addFoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo){
        Optional< LoginClienteEntity > loginCliente = loginClienteRepository.findById(id);
        return loginCliente.map( lc -> {
            try{
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                lc.setFoto(bytes);
                loginClienteRepository.save(lc);
                is.close();
                return bytes;
            }catch (IOException e){
                return null;
            }
        }).orElse(null);
    }

}
