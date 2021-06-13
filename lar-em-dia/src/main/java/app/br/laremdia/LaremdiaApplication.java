package app.br.laremdia;

import app.br.laremdia.model.entity.*;
import app.br.laremdia.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class LaremdiaApplication   {

    @Bean
    public CommandLineRunner commandLineRunner(
            @Autowired LoginProfissionalRepository repository, LoginClienteRepository repositoryCliente,
            TipoServicoRepository tipoServicoRepository, ServicoProfissionalRepository servicoProfissionalRepository,
            EstadoAtendidoRepository estadoAtendidoRepository, MunicipioAtendidoRepository municipioAtendidoRepository,
            PedidoContratadoRepository pedidoContratadoRepository, AgendaRepository agendaRepository){
        return args -> {
            LoginProfissionalEntity loginProfissional = new LoginProfissionalEntity();
            loginProfissional.setNome("Marcio dos Santos Souza");
            loginProfissional.setCpf("80860752062");
            loginProfissional.setEmail("raulfsantiago@outlook.com.br");
            loginProfissional.setCelular("(21)98867-1010");
            loginProfissional.setSenha("123");
            loginProfissional.setAtivo(true);
            repository.save(loginProfissional);

            ServicoProfissionalEntity servicoProfissional = new ServicoProfissionalEntity();
            servicoProfissional.setNome("Ar condicionado de janela");
            servicoProfissional.setAtivo(true);
            servicoProfissionalRepository.save(servicoProfissional);

            TipoServicoEntity tipoServico = new TipoServicoEntity();
            tipoServico.setNome("Limpeza");
            tipoServico.setPreco(new BigDecimal("300.00"));
            tipoServico.setServicoProfissional(servicoProfissional);
            tipoServicoRepository.save(tipoServico);

            TipoServicoEntity tipoServico2 = new TipoServicoEntity();
            tipoServico2.setNome("Instalação");
            tipoServico2.setPreco(new BigDecimal("500.00"));
            tipoServico2.setServicoProfissional(servicoProfissional);
            tipoServicoRepository.save(tipoServico2);

            EstadoAtendidoEntity estadoAtendidoEntity = new EstadoAtendidoEntity();
            estadoAtendidoEntity.setUf("RJ");
            estadoAtendidoEntity.setAtivo(true);
            estadoAtendidoRepository.save(estadoAtendidoEntity);

            EstadoAtendidoEntity estadoAtendidoEntity2 = new EstadoAtendidoEntity();
            estadoAtendidoEntity2.setUf("SP");
            estadoAtendidoEntity2.setAtivo(false);
            estadoAtendidoRepository.save(estadoAtendidoEntity2);

            MunicipioAtendidoEntity municipioAtendidoEntity = new MunicipioAtendidoEntity();
            municipioAtendidoEntity.setMunicipio("Nova Iguaçu");
            municipioAtendidoEntity.setAtivo(true);
            municipioAtendidoEntity.setEstado(estadoAtendidoEntity);
            municipioAtendidoRepository.save(municipioAtendidoEntity);

//            MunicipioAtendidoEntity municipioAtendidoEntity2 = new MunicipioAtendidoEntity();
//            municipioAtendidoEntity2.setMunicipio("São João de Meriti");
//            municipioAtendidoEntity2.setAtivo(true);
//            municipioAtendidoEntity2.setEstado(estadoAtendidoEntity);
//            municipioAtendidoRepository.save(municipioAtendidoEntity2);

            MunicipioAtendidoEntity municipioAtendidoEntity3 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity3.setMunicipio("Rio de Janeiro");
            municipioAtendidoEntity3.setAtivo(false);
            municipioAtendidoEntity3.setEstado(estadoAtendidoEntity);
            municipioAtendidoRepository.save(municipioAtendidoEntity3);

            MunicipioAtendidoEntity municipioAtendidoEntity4 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity4.setMunicipio("São Paulo");
            municipioAtendidoEntity4.setAtivo(true);
            municipioAtendidoEntity4.setEstado(estadoAtendidoEntity2);
            municipioAtendidoRepository.save(municipioAtendidoEntity4);

            MunicipioAtendidoEntity municipioAtendidoEntity5 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity5.setMunicipio("Campos dos Jordão");
            municipioAtendidoEntity5.setAtivo(false);
            municipioAtendidoEntity5.setEstado(estadoAtendidoEntity2);
            municipioAtendidoRepository.save(municipioAtendidoEntity5);

            LoginClienteEntity loginCliente = new LoginClienteEntity();
            loginCliente.setNome("Marcia da Silva Pereira");
            loginCliente.setCpf("83918396053");
            loginCliente.setEmail("marcia@gmail.com");
            loginCliente.setCelular("(21)98867-1020");
            loginCliente.setSenha("123");
            loginCliente.setEndereco("Rua Américo Rocha");
            loginCliente.setNumero("1005");
            loginCliente.setBairro("Honório Gurgel");
            loginCliente.setComplemento("Bloco 9 Apto 101");
            loginCliente.setMunicipioAtendido(municipioAtendidoEntity);
            loginCliente.setEstadoAtendido(estadoAtendidoEntity);
            loginCliente.setReferencia("Próximo a linha do trem");
            loginCliente.setAtivo(true);
            repositoryCliente.save(loginCliente);

            AgendaEntity agendaEntity = new AgendaEntity();
            agendaEntity.setDia(LocalDate.of(2021,6,11));
            agendaEntity.setDisponivel(false);
            agendaEntity.setTurno("Manhã");
            agendaRepository.save(agendaEntity);

            AgendaEntity agendaEntity2 = new AgendaEntity();
            agendaEntity2.setDia(LocalDate.of(2021,6,12));
            agendaEntity2.setDisponivel(false);
            agendaEntity2.setTurno("Tarde");
            agendaRepository.save(agendaEntity2);

            AgendaEntity agendaEntity3 = new AgendaEntity();
            agendaEntity3.setDia(LocalDate.of(2021,6,13));
            agendaEntity3.setDisponivel(false);
            agendaEntity3.setTurno("Manhã");
            agendaRepository.save(agendaEntity3);

            AgendaEntity agendaEntity4 = new AgendaEntity();
            agendaEntity4.setDia(LocalDate.of(2021,6,14));
            agendaEntity4.setDisponivel(true);
            agendaEntity4.setTurno("Tarde");
            agendaRepository.save(agendaEntity4);

            PedidoContratadoEntity pedidoContratadoEntity = new PedidoContratadoEntity();
            pedidoContratadoEntity.setDescricao("Casa alta de dois andares");
            pedidoContratadoEntity.setLocal("Casa");
            pedidoContratadoEntity.setDataHoraInicio(LocalDateTime.of(2021,6,11,10,20));
            pedidoContratadoEntity.setDataHoraFim(LocalDateTime.of(2021,6,11,12,05));
            pedidoContratadoEntity.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity.setSituacao(false);
            pedidoContratadoEntity.setTipoServico(tipoServico);
            pedidoContratadoEntity.setAgenda(agendaEntity);
            pedidoContratadoEntity.setLoginCliente(loginCliente);
            pedidoContratadoEntity.setPrecoContratado(new BigDecimal("300.00"));
            pedidoContratadoRepository.save(pedidoContratadoEntity);

            PedidoContratadoEntity pedidoContratadoEntity2 = new PedidoContratadoEntity();
            pedidoContratadoEntity2.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity2.setLocal("Apartamento");
            pedidoContratadoEntity2.setSituacao(true);
            pedidoContratadoEntity2.setTipoServico(tipoServico2);
            pedidoContratadoEntity2.setAgenda(agendaEntity2);
            pedidoContratadoEntity2.setLoginCliente(loginCliente);
            pedidoContratadoEntity2.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity2);

            PedidoContratadoEntity pedidoContratadoEntity3 = new PedidoContratadoEntity();
            pedidoContratadoEntity3.setDescricao("Sala com um banheiro");
            pedidoContratadoEntity3.setLocal("Sala comercial");
            pedidoContratadoEntity3.setSituacao(true);
            pedidoContratadoEntity3.setTipoServico(tipoServico2);
            pedidoContratadoEntity3.setAgenda(agendaEntity3);
            pedidoContratadoEntity3.setLoginCliente(loginCliente);
            pedidoContratadoEntity3.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity3);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(LaremdiaApplication.class, args);
    }


}
