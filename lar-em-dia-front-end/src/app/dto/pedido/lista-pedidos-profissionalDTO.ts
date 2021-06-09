export interface ListaPedidosProfissionalDTO {

    idPedido:        number;
    despesas:        number;
    descricao:       string;    
    local:           string;    
    precoContratado: number;

    nomeTipoServico: string;
    
    nomeServico:     string;
    
    idCliente:       number;    
    
    dia:             string;
    turno:           string;

}