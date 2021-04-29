import { EstadoAtendidoDTO } from "../regiao/estado-atendidoDTO";
import { MunicipioAtendidoDTO } from "../regiao/municipio-atendidoDTO";

export class LoginClienteDTO {
    
    nome: string;
    cpf: string;
    email: string;
    celular: string;
    senha: string;
    endereco: string;
    numero: string;
    bairro: string;
    complemento: string;    
    referencia: string;
    foto: any[];
    ativo: boolean;

    estadoAtendidoDTO: EstadoAtendidoDTO;
    MunicipioAtendidoDTO: MunicipioAtendidoDTO;

}