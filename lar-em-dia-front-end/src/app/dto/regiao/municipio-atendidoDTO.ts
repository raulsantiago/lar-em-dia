import { EstadoAtendidoDTO } from "./estado-atendidoDTO"

export class MunicipioAtendidoDTO {

    idMunicipio: number;
    municipio: string;
    ativo: boolean;
    estadoAtendidoDTO: EstadoAtendidoDTO;

    ativoFmt: string;
    ativoClienteFmt: string;
    ativoEstadoMuniFmt: string;
    
}