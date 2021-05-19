package app.br.laremdia.rest;

import app.br.laremdia.model.entity.AgendaEntity;
import app.br.laremdia.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping("/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(agendaService.consultar(id));
    }

    @GetMapping()
    public ResponseEntity listar(){
        return ResponseEntity.ok(agendaService.listar());
    }

    @PostMapping()
    public ResponseEntity inserir(@RequestBody @Valid AgendaEntity agendaEntity){
        return ResponseEntity.ok(agendaService.inserir(agendaEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id){
        agendaService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
