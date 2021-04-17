package app.br.laremdia.rest;

import app.br.laremdia.model.dto.AlterarTipoServicoDTO;
import app.br.laremdia.model.dto.IncluirTipoServicoDTO;
import app.br.laremdia.model.dto.TipoServicoDTO;
import app.br.laremdia.service.TipoServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tiposervico")
@RequiredArgsConstructor
public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    @GetMapping()
    public ResponseEntity listar(){
        return  ResponseEntity.ok(tipoServicoService.listar());
    }

    @GetMapping("/{id}")
    public  ResponseEntity consultar(@PathVariable("id") Integer id){
        return ResponseEntity.ok(tipoServicoService.consultar(id));
    }

    @PostMapping()
    public ResponseEntity inserir(@RequestBody @Valid IncluirTipoServicoDTO tipoServicoDTO){
        return  ResponseEntity.ok(tipoServicoService.inserir(tipoServicoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") Integer id, @RequestBody @Valid AlterarTipoServicoDTO tipoServicoDTO){
        AlterarTipoServicoDTO tipoServico =  tipoServicoService.alterar(tipoServicoDTO, id);
        return tipoServicoDTO != null ? ResponseEntity.ok(tipoServico) :  ResponseEntity.notFound().build();
    }

}