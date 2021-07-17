import { ServicoProfissionalDTO } from "./servico-profissionalDTO"

export class TipoServicoProfissionalDTO {

    idTipo:                 number;
    nome:                   string;
    preco:                  number;    
    servicoProfissionalDTO: ServicoProfissionalDTO;

    precoFmt:               string;
    nomeServico:            string;
    ativoServicoFmt:        string;

}