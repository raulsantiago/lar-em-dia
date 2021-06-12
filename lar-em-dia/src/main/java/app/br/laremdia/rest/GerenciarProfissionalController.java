package app.br.laremdia.rest;

import app.br.laremdia.model.dto.LoginProfissionalDTO;
import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.model.repository.LoginProfissionalRepository;
import app.br.laremdia.service.GerenciarProfissionalService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/gerenciarprofissional")
@RequiredArgsConstructor
public class GerenciarProfissionalController {

    private final LoginProfissionalRepository loginProfissionalRepository;
    private final GerenciarProfissionalService gerenciarProfissionalService;

    @GetMapping("/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id){
        return ResponseEntity.ok(gerenciarProfissionalService.consultar(id));
    }

    @GetMapping("/{email}/email")
    public ResponseEntity consultarEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(gerenciarProfissionalService.consultarEmail(email));
    }

    @GetMapping("/{ativo}/ativo")
    public Boolean consultarAtivo(@PathVariable("ativo") Boolean ativo){
        return gerenciarProfissionalService.consultarAtivo(ativo);
    }

    @GetMapping()
    public ResponseEntity listar(){
        return ResponseEntity.ok(gerenciarProfissionalService.listar());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity inserir(@RequestBody @Valid LoginProfissionalEntity loginProfissionalEntity){
        return ResponseEntity.ok(gerenciarProfissionalService.inserir(loginProfissionalEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") Integer id, @RequestBody @Valid LoginProfissionalEntity loginProfissionalEntity){
        loginProfissionalEntity.setIdProfissional(id);
        LoginProfissionalDTO loginProfissionalDTO = gerenciarProfissionalService.alterar(loginProfissionalEntity, id);
        return loginProfissionalDTO != null ? ResponseEntity.ok(loginProfissionalDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id){
        gerenciarProfissionalService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/foto")
    public byte[] addFoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo){
        Optional<LoginProfissionalEntity> loginProfissional = loginProfissionalRepository.findById(id);
        return loginProfissional.map( lp -> {
            try{
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                lp.setFoto(bytes);
                loginProfissionalRepository.save(lp);
                is.close();
                return bytes;
            }catch (IOException e){
                return null;
            }
        }).orElse(null);
    }

}
