package app.br.laremdia.rest;

import app.br.laremdia.model.dto.*;
import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import app.br.laremdia.service.RegiaoAtendidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/regiao")
@RequiredArgsConstructor
public class RegiaoAtendidaController {

    private final RegiaoAtendidaService regiaoAtendidaService;

    @GetMapping()
    public ResponseEntity listarUf() {
        return ResponseEntity.ok(regiaoAtendidaService.listarUf());
    }

    @GetMapping("/ufativo")
    public ResponseEntity listarUfAtivo() {
        return ResponseEntity.ok(regiaoAtendidaService.listarUfAtivo());
    }

    @GetMapping("/municipio")
    public ResponseEntity listarMunicipio() {
        return ResponseEntity.ok(regiaoAtendidaService.listarMunicipo());
    }

    @GetMapping("/{idUf}/uf")
    public ResponseEntity listarMunicipioAtivoPorUfAtivo(@PathVariable("idUf") Integer idUf) {
        return ResponseEntity.ok(regiaoAtendidaService.listarMunicipioAtivoPorUfAtivo(idUf));
    }

    @GetMapping("/{id}")
    public  ResponseEntity consultar(@PathVariable("id") Integer id){
        return ResponseEntity.ok(regiaoAtendidaService.consultar(id));
    }

    @PostMapping()
    public ResponseEntity inserir(@RequestBody @Valid IncluirMunicipioAtendidoDTO incluirMunicipioAtendidoDTO){
        return ResponseEntity.ok(regiaoAtendidaService.inserir(incluirMunicipioAtendidoDTO));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity alterarEstado(@PathVariable("id") Integer id, @RequestBody @Valid EstadoAtendidoEntity estadoAtendidoEntity){
        estadoAtendidoEntity.setIdEstado(id);
        AlterarEstadoAtendidoDTO alterarEstadoAtendidoDTO =  regiaoAtendidaService.alterarEstado(estadoAtendidoEntity, id);
        return alterarEstadoAtendidoDTO != null ? ResponseEntity.ok(alterarEstadoAtendidoDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/municipio")
    public ResponseEntity alterarMunicipio(@PathVariable("id") Integer id, @RequestBody @Valid MunicipioAtendidoEntity municipioAtendidoEntity){
        municipioAtendidoEntity.setIdMunicipio(id);
        AlterarMunicipioAtendidoDTO alterarMunicipioAtendidoDTO =  regiaoAtendidaService.alterarMunicipio(municipioAtendidoEntity, id);
        return alterarMunicipioAtendidoDTO != null ? ResponseEntity.ok(alterarMunicipioAtendidoDTO) : ResponseEntity.notFound().build();
    }


}
