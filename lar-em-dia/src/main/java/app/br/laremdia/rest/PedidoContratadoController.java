package app.br.laremdia.rest;

import app.br.laremdia.model.dto.IncluirPedidoContratadoDTO;
import app.br.laremdia.model.entity.PedidoContratadoEntity;
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity inserir(@RequestBody @Valid IncluirPedidoContratadoDTO incluirPedidoContratadoDTO){
        return ResponseEntity.ok(pedidoContratadoService.inserir(incluirPedidoContratadoDTO));
    }

}
