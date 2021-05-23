import { AgendaDTO } from "../agenda/agendaDTO";
import { GerenciarClienteDTO } from "../login-cliente/gerenciar-clienteDTO";
import { TipoServicoProfissionalDTO } from "../servico-profissional/tipo-servico-profissionalDTO";

export class PedidoContratadoDTO {

    idPedido:        number;
    descricao:       string;
    local:           string;
    dataHoraInicio:  string;
    dataHoraFim:     string;
    despesas:        number;
    situacao:        boolean;
    precoContratado: number;
    agendaDTO:       AgendaDTO;
    tipoServicoDTO:  TipoServicoProfissionalDTO;
    loginClienteDTO: GerenciarClienteDTO;

}