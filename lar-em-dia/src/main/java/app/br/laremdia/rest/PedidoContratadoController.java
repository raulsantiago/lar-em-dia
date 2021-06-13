package app.br.laremdia.rest;

import app.br.laremdia.model.dto.AlterarPedidoContratadoDTO;
import app.br.laremdia.model.dto.IncluirPedidoContratadoDTO;
import app.br.laremdia.service.PedidoContratadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoContratadoController {

    private final PedidoContratadoService pedidoContratadoService;

    @GetMapping("/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(pedidoContratadoService.consultar(id));
    }

    @GetMapping("/{id}/cliente")
    public ResponseEntity pedidosPorIdCliente(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(pedidoContratadoService.pedidosPorIdCliente(id));
    }

    @GetMapping("/profissional")
    public ResponseEntity listaPedidosViewProfissional() {
        return ResponseEntity.ok(pedidoContratadoService.listaPedidosViewProfissional());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity inserir(@RequestBody @Valid IncluirPedidoContratadoDTO incluirPedidoContratadoDTO){
        return ResponseEntity.ok(pedidoContratadoService.inserir(incluirPedidoContratadoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id){
        pedidoContratadoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/prof")
    public ResponseEntity excluirProf(@PathVariable("id") Integer id){
        pedidoContratadoService.excluirProf(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") Integer id, @RequestBody @Valid AlterarPedidoContratadoDTO alterarPedidoContratadoDTO){
        AlterarPedidoContratadoDTO alterarPedido =  pedidoContratadoService.alterar(id, alterarPedidoContratadoDTO);
        return alterarPedidoContratadoDTO != null ? ResponseEntity.ok(alterarPedido) :  ResponseEntity.notFound().build();
    }

}
