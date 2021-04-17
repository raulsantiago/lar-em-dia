package app.br.laremdia.rest;

import app.br.laremdia.model.dto.ServicoProfissionalDTO;
import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import app.br.laremdia.service.ServicoProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoProfissionalController {

    private final ServicoProfissionalService servicoProfissionalService;

    @GetMapping()
    public ResponseEntity listar(){
        return  ResponseEntity.ok(servicoProfissionalService.listar());
    }

    @GetMapping("/{id}")
    public  ResponseEntity consultar(@PathVariable("id") Integer id){
        return ResponseEntity.ok(servicoProfissionalService.consultar(id));
    }

    @GetMapping("/{nome}/nome")
    public ResponseEntity consultarNome(@PathVariable("nome") String nome){
        return ResponseEntity.ok(servicoProfissionalService.consultarNome(nome));
    }

    @PostMapping()
    public ResponseEntity inserir(@RequestBody @Valid ServicoProfissionalEntity servicoProfissionalEntity){
        return ResponseEntity.ok(servicoProfissionalService.inserir(servicoProfissionalEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") Integer id, @RequestBody @Valid ServicoProfissionalEntity servicoProfissionalEntity){
        servicoProfissionalEntity.setIdServico(id);
        ServicoProfissionalDTO servicoProfissionalDTO = servicoProfissionalService.alterar(servicoProfissionalEntity, id);
        return servicoProfissionalDTO != null ? ResponseEntity.ok(servicoProfissionalDTO) : ResponseEntity.notFound().build();
    }

}
