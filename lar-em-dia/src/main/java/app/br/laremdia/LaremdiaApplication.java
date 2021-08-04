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

            ServicoProfissionalEntity servicoProfissional2 = new ServicoProfissionalEntity();
            servicoProfissional2.setNome("Ventilador de teto");
            servicoProfissional2.setAtivo(true);
            servicoProfissionalRepository.save(servicoProfissional2);

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

            TipoServicoEntity tipoServico3 = new TipoServicoEntity();
            tipoServico3.setNome("Troca de rolamento");
            tipoServico3.setPreco(new BigDecimal("250.00"));
            tipoServico3.setServicoProfissional(servicoProfissional2);
            tipoServicoRepository.save(tipoServico3);

            TipoServicoEntity tipoServico4 = new TipoServicoEntity();
            tipoServico4.setNome("Instalação");
            tipoServico4.setPreco(new BigDecimal("400.00"));
            tipoServico4.setServicoProfissional(servicoProfissional2);
            tipoServicoRepository.save(tipoServico4);

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

            MunicipioAtendidoEntity municipioAtendidoEntity2 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity2.setMunicipio("São João de Meriti");
            municipioAtendidoEntity2.setAtivo(true);
            municipioAtendidoEntity2.setEstado(estadoAtendidoEntity);
            municipioAtendidoRepository.save(municipioAtendidoEntity2);

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
            loginCliente.setEndereco("Rua Enéas Miranda");
            loginCliente.setNumero("35");
            loginCliente.setBairro("Grande Rio");
            loginCliente.setComplemento("Casa");
            loginCliente.setMunicipioAtendido(municipioAtendidoEntity2);
            loginCliente.setEstadoAtendido(estadoAtendidoEntity);
            loginCliente.setReferencia("Próximo a linha do trem");
            loginCliente.setAtivo(true);
            repositoryCliente.save(loginCliente);

            LoginClienteEntity loginCliente2 = new LoginClienteEntity();
            loginCliente2.setNome("Sandra Pereria Novaes");
            loginCliente2.setCpf("15147883061");
            loginCliente2.setEmail("sandra@gmail.com");
            loginCliente2.setCelular("(21)98867-9999");
            loginCliente2.setSenha("123");
            loginCliente2.setEndereco("Rua Enéas Miranda");
            loginCliente2.setNumero("300");
            loginCliente2.setBairro("Grande Rio");
            loginCliente2.setComplemento("Casa");
            loginCliente2.setMunicipioAtendido(municipioAtendidoEntity2);
            loginCliente2.setEstadoAtendido(estadoAtendidoEntity);
            loginCliente2.setReferencia("Próximo a linha do trem");
            loginCliente2.setAtivo(true);
            repositoryCliente.save(loginCliente2);

            AgendaEntity agendaEntity = new AgendaEntity();
            agendaEntity.setDia(LocalDate.of(2021,7,1));
            agendaEntity.setDisponivel(false);
            agendaEntity.setTurno("Manhã");
            agendaRepository.save(agendaEntity);

            AgendaEntity agendaEntity2 = new AgendaEntity();
            agendaEntity2.setDia(LocalDate.of(2021,7,1));
            agendaEntity2.setDisponivel(false);
            agendaEntity2.setTurno("Tarde");
            agendaRepository.save(agendaEntity2);

            AgendaEntity agendaEntity3 = new AgendaEntity();
            agendaEntity3.setDia(LocalDate.of(2021,7,2));
            agendaEntity3.setDisponivel(false);
            agendaEntity3.setTurno("Manhã");
            agendaRepository.save(agendaEntity3);

            AgendaEntity agendaEntity4 = new AgendaEntity();
            agendaEntity4.setDia(LocalDate.of(2021,7,2));
            agendaEntity4.setDisponivel(false);
            agendaEntity4.setTurno("Tarde");
            agendaRepository.save(agendaEntity4);

            AgendaEntity agendaEntity5 = new AgendaEntity();
            agendaEntity5.setDia(LocalDate.of(2021,7,3));
            agendaEntity5.setDisponivel(false);
            agendaEntity5.setTurno("Tarde");
            agendaRepository.save(agendaEntity5);

            AgendaEntity agendaEntity6 = new AgendaEntity();
            agendaEntity6.setDia(LocalDate.of(2021,7,4));
            agendaEntity6.setDisponivel(false);
            agendaEntity6.setTurno("Tarde");
            agendaRepository.save(agendaEntity6);

            AgendaEntity agendaEntity7 = new AgendaEntity();
            agendaEntity7.setDia(LocalDate.of(2021,7,5));
            agendaEntity7.setDisponivel(false);
            agendaEntity7.setTurno("Tarde");
            agendaRepository.save(agendaEntity7);

            AgendaEntity agendaEntity8 = new AgendaEntity();
            agendaEntity8.setDia(LocalDate.of(2021,7,6));
            agendaEntity8.setDisponivel(false);
            agendaEntity8.setTurno("Tarde");
            agendaRepository.save(agendaEntity8);

            AgendaEntity agendaEntity9 = new AgendaEntity();
            agendaEntity9.setDia(LocalDate.of(2021,7,7));
            agendaEntity9.setDisponivel(false);
            agendaEntity9.setTurno("Tarde");
            agendaRepository.save(agendaEntity9);

            AgendaEntity agendaEntity10 = new AgendaEntity();
            agendaEntity10.setDia(LocalDate.of(2021,7,8));
            agendaEntity10.setDisponivel(false);
            agendaEntity10.setTurno("Tarde");
            agendaRepository.save(agendaEntity10);

            AgendaEntity agendaEntity11 = new AgendaEntity();
            agendaEntity11.setDia(LocalDate.of(2021,7,9));
            agendaEntity11.setDisponivel(false);
            agendaEntity11.setTurno("Tarde");
            agendaRepository.save(agendaEntity11);

            AgendaEntity agendaEntity12 = new AgendaEntity();
            agendaEntity12.setDia(LocalDate.of(2021,7,11));
            agendaEntity12.setDisponivel(false);
            agendaEntity12.setTurno("Tarde");
            agendaRepository.save(agendaEntity12);

            AgendaEntity agendaEntity13 = new AgendaEntity();
            agendaEntity13.setDia(LocalDate.of(2021,7,12));
            agendaEntity13.setDisponivel(false);
            agendaEntity13.setTurno("Tarde");
            agendaRepository.save(agendaEntity13);

            AgendaEntity agendaEntity14 = new AgendaEntity();
            agendaEntity14.setDia(LocalDate.of(2021,7,13));
            agendaEntity14.setDisponivel(false);
            agendaEntity14.setTurno("Tarde");
            agendaRepository.save(agendaEntity14);

            AgendaEntity agendaEntity15 = new AgendaEntity();
            agendaEntity15.setDia(LocalDate.of(2021,7,14));
            agendaEntity15.setDisponivel(false);
            agendaEntity15.setTurno("Tarde");
            agendaRepository.save(agendaEntity15);

            AgendaEntity agendaEntity16 = new AgendaEntity();
            agendaEntity16.setDia(LocalDate.of(2021,7,15));
            agendaEntity16.setDisponivel(false);
            agendaEntity16.setTurno("Tarde");
            agendaRepository.save(agendaEntity16);

            AgendaEntity agendaEntity17 = new AgendaEntity();
            agendaEntity17.setDia(LocalDate.of(2021,7,16));
            agendaEntity17.setDisponivel(false);
            agendaEntity17.setTurno("Tarde");
            agendaRepository.save(agendaEntity17);

            AgendaEntity agendaEntity18 = new AgendaEntity();
            agendaEntity18.setDia(LocalDate.of(2021,7,17));
            agendaEntity18.setDisponivel(false);
            agendaEntity18.setTurno("Tarde");
            agendaRepository.save(agendaEntity18);

            AgendaEntity agendaEntity19 = new AgendaEntity();
            agendaEntity19.setDia(LocalDate.of(2021,7,18));
            agendaEntity19.setDisponivel(false);
            agendaEntity19.setTurno("Tarde");
            agendaRepository.save(agendaEntity19);

            AgendaEntity agendaEntity20 = new AgendaEntity();
            agendaEntity20.setDia(LocalDate.of(2021,7,19));
            agendaEntity20.setDisponivel(false);
            agendaEntity20.setTurno("Tarde");
            agendaRepository.save(agendaEntity20);

            AgendaEntity agendaEntity21 = new AgendaEntity();
            agendaEntity21.setDia(LocalDate.of(2021,7,21));
            agendaEntity21.setDisponivel(false);
            agendaEntity21.setTurno("Tarde");
            agendaRepository.save(agendaEntity21);

            AgendaEntity agendaEntity22 = new AgendaEntity();
            agendaEntity22.setDia(LocalDate.of(2021,7,22));
            agendaEntity22.setDisponivel(false);
            agendaEntity22.setTurno("Tarde");
            agendaRepository.save(agendaEntity22);

            AgendaEntity agendaEntity23 = new AgendaEntity();
            agendaEntity23.setDia(LocalDate.of(2021,7,23));
            agendaEntity23.setDisponivel(false);
            agendaEntity23.setTurno("Tarde");
            agendaRepository.save(agendaEntity23);

            AgendaEntity agendaEntity24 = new AgendaEntity();
            agendaEntity24.setDia(LocalDate.of(2021,7,24));
            agendaEntity24.setDisponivel(false);
            agendaEntity24.setTurno("Tarde");
            agendaRepository.save(agendaEntity24);

            AgendaEntity agendaEntity25 = new AgendaEntity();
            agendaEntity25.setDia(LocalDate.of(2021,7,25));
            agendaEntity25.setDisponivel(false);
            agendaEntity25.setTurno("Tarde");
            agendaRepository.save(agendaEntity25);

            AgendaEntity agendaEntity26 = new AgendaEntity();
            agendaEntity26.setDia(LocalDate.of(2021,7,26));
            agendaEntity26.setDisponivel(false);
            agendaEntity26.setTurno("Tarde");
            agendaRepository.save(agendaEntity26);

            AgendaEntity agendaEntity27 = new AgendaEntity();
            agendaEntity27.setDia(LocalDate.of(2021,7,27));
            agendaEntity27.setDisponivel(false);
            agendaEntity27.setTurno("Tarde");
            agendaRepository.save(agendaEntity27);

            AgendaEntity agendaEntity28 = new AgendaEntity();
            agendaEntity28.setDia(LocalDate.of(2021,7,28));
            agendaEntity28.setDisponivel(false);
            agendaEntity28.setTurno("Tarde");
            agendaRepository.save(agendaEntity28);

            AgendaEntity agendaEntity29 = new AgendaEntity();
            agendaEntity29.setDia(LocalDate.of(2021,7,29));
            agendaEntity29.setDisponivel(false);
            agendaEntity29.setTurno("Tarde");
            agendaRepository.save(agendaEntity29);

            AgendaEntity agendaEntity30 = new AgendaEntity();
            agendaEntity30.setDia(LocalDate.of(2021,7,30));
            agendaEntity30.setDisponivel(false);
            agendaEntity30.setTurno("Tarde");
            agendaRepository.save(agendaEntity30);

            AgendaEntity agendaEntity31 = new AgendaEntity();
            agendaEntity31.setDia(LocalDate.of(2021,7,31));
            agendaEntity31.setDisponivel(false);
            agendaEntity31.setTurno("Tarde");
            agendaRepository.save(agendaEntity31);

            AgendaEntity agendaEntity32 = new AgendaEntity();
            agendaEntity32.setDia(LocalDate.of(2021,7,31));
            agendaEntity32.setDisponivel(false);
            agendaEntity32.setTurno("Tarde");
            agendaRepository.save(agendaEntity32);

            AgendaEntity agendaEntity33 = new AgendaEntity();
            agendaEntity33.setDia(LocalDate.of(2021,7,31));
            agendaEntity33.setDisponivel(false);
            agendaEntity33.setTurno("Tarde");
            agendaRepository.save(agendaEntity33);

            AgendaEntity agendaEntity34 = new AgendaEntity();
            agendaEntity34.setDia(LocalDate.of(2021,7,31));
            agendaEntity34.setDisponivel(false);
            agendaEntity34.setTurno("Tarde");
            agendaRepository.save(agendaEntity34);

            AgendaEntity agendaEntity35 = new AgendaEntity();
            agendaEntity35.setDia(LocalDate.of(2021,7,31));
            agendaEntity35.setDisponivel(true);
            agendaEntity35.setTurno("Manhã");
            agendaRepository.save(agendaEntity35);

            AgendaEntity agendaEntity36 = new AgendaEntity();
            agendaEntity36.setDia(LocalDate.of(2021,8,30));
            agendaEntity36.setDisponivel(true);
            agendaEntity36.setTurno("Manhã");
            agendaRepository.save(agendaEntity36);

            AgendaEntity agendaEntity37 = new AgendaEntity();
            agendaEntity37.setDia(LocalDate.of(2021,8,2));
            agendaEntity37.setDisponivel(false);
            agendaEntity37.setTurno("Manhã");
            agendaRepository.save(agendaEntity37);

            AgendaEntity agendaEntity38 = new AgendaEntity();
            agendaEntity38.setDia(LocalDate.of(2021,8,3));
            agendaEntity38.setDisponivel(false);
            agendaEntity38.setTurno("Manhã");
            agendaRepository.save(agendaEntity38);

            AgendaEntity agendaEntity39 = new AgendaEntity();
            agendaEntity39.setDia(LocalDate.of(2021,8,4));
            agendaEntity39.setDisponivel(false);
            agendaEntity39.setTurno("Manhã");
            agendaRepository.save(agendaEntity39);

            AgendaEntity agendaEntity40 = new AgendaEntity();
            agendaEntity40.setDia(LocalDate.of(2021,6,30));
            agendaEntity40.setDisponivel(false);
            agendaEntity40.setTurno("Manhã");
            agendaRepository.save(agendaEntity40);

            AgendaEntity agendaEntity41 = new AgendaEntity();
            agendaEntity41.setDia(LocalDate.of(2021,6,29));
            agendaEntity41.setDisponivel(false);
            agendaEntity41.setTurno("Manhã");
            agendaRepository.save(agendaEntity41);

            PedidoContratadoEntity pedidoContratadoEntity = new PedidoContratadoEntity();
            pedidoContratadoEntity.setDescricao("Casa alta de dois andares");
            pedidoContratadoEntity.setLocal("Casa");
            pedidoContratadoEntity.setDataHoraInicio(LocalDateTime.of(2021,7,1,9,20));
            pedidoContratadoEntity.setDataHoraFim(LocalDateTime.of(2021,7,1,12,05));
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
            pedidoContratadoEntity2.setDataHoraInicio(LocalDateTime.of(2021,7,1,13,00));
            pedidoContratadoEntity2.setDataHoraFim(LocalDateTime.of(2021,7,1,18,00));
            pedidoContratadoEntity2.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity2.setSituacao(false);
            pedidoContratadoEntity2.setTipoServico(tipoServico2);
            pedidoContratadoEntity2.setAgenda(agendaEntity2);
            pedidoContratadoEntity2.setLoginCliente(loginCliente);
            pedidoContratadoEntity2.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity2);

            PedidoContratadoEntity pedidoContratadoEntity3 = new PedidoContratadoEntity();
            pedidoContratadoEntity3.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity3.setLocal("Apartamento");
            pedidoContratadoEntity3.setDataHoraInicio(LocalDateTime.of(2021,7,2,9,00));
            pedidoContratadoEntity3.setDataHoraFim(LocalDateTime.of(2021,7,2,11,30));
            pedidoContratadoEntity3.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity3.setSituacao(false);
            pedidoContratadoEntity3.setTipoServico(tipoServico);
            pedidoContratadoEntity3.setAgenda(agendaEntity3);
            pedidoContratadoEntity3.setLoginCliente(loginCliente);
            pedidoContratadoEntity3.setPrecoContratado(tipoServico.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity3);

            PedidoContratadoEntity pedidoContratadoEntity4 = new PedidoContratadoEntity();
            pedidoContratadoEntity4.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity4.setLocal("Apartamento");
            pedidoContratadoEntity4.setDataHoraInicio(LocalDateTime.of(2021,7,2,12,00));
            pedidoContratadoEntity4.setDataHoraFim(LocalDateTime.of(2021,7,2,16,00));
            pedidoContratadoEntity4.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity4.setSituacao(false);
            pedidoContratadoEntity4.setTipoServico(tipoServico3);
            pedidoContratadoEntity4.setAgenda(agendaEntity4);
            pedidoContratadoEntity4.setLoginCliente(loginCliente);
            pedidoContratadoEntity4.setPrecoContratado(tipoServico3.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity4);

            PedidoContratadoEntity pedidoContratadoEntity5 = new PedidoContratadoEntity();
            pedidoContratadoEntity5.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity5.setLocal("Apartamento");
            pedidoContratadoEntity5.setDataHoraInicio(LocalDateTime.of(2021,7,3,12,30));
            pedidoContratadoEntity5.setDataHoraFim(LocalDateTime.of(2021,7,3,16,00));
            pedidoContratadoEntity5.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity5.setSituacao(false);
            pedidoContratadoEntity5.setTipoServico(tipoServico);
            pedidoContratadoEntity5.setAgenda(agendaEntity5);
            pedidoContratadoEntity5.setLoginCliente(loginCliente);
            pedidoContratadoEntity5.setPrecoContratado(tipoServico.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity5);

            PedidoContratadoEntity pedidoContratadoEntity6 = new PedidoContratadoEntity();
            pedidoContratadoEntity6.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity6.setLocal("Apartamento");
            pedidoContratadoEntity6.setDataHoraInicio(LocalDateTime.of(2021,7,4,12,30));
            pedidoContratadoEntity6.setDataHoraFim(LocalDateTime.of(2021,7,4,17,30));
            pedidoContratadoEntity6.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity6.setSituacao(false);
            pedidoContratadoEntity6.setTipoServico(tipoServico2);
            pedidoContratadoEntity6.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity6.setAgenda(agendaEntity6);
            pedidoContratadoEntity6.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity6);

            PedidoContratadoEntity pedidoContratadoEntity7 = new PedidoContratadoEntity();
            pedidoContratadoEntity7.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity7.setLocal("Apartamento");
            pedidoContratadoEntity7.setDataHoraInicio(LocalDateTime.of(2021,7,5,12,30));
            pedidoContratadoEntity7.setDataHoraFim(LocalDateTime.of(2021,7,5,16,00));
            pedidoContratadoEntity7.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity7.setSituacao(false);
            pedidoContratadoEntity7.setTipoServico(tipoServico4);
            pedidoContratadoEntity7.setPrecoContratado(tipoServico4.getPreco());
            pedidoContratadoEntity7.setAgenda(agendaEntity7);
            pedidoContratadoEntity7.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity7);

            PedidoContratadoEntity pedidoContratadoEntity8 = new PedidoContratadoEntity();
            pedidoContratadoEntity8.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity8.setLocal("Apartamento");
            pedidoContratadoEntity8.setDataHoraInicio(LocalDateTime.of(2021,7,6,15,30));
            pedidoContratadoEntity8.setDataHoraFim(LocalDateTime.of(2021,7,6,16,00));
            pedidoContratadoEntity8.setDespesas(new BigDecimal("40.00"));
            pedidoContratadoEntity8.setSituacao(false);
            pedidoContratadoEntity8.setTipoServico(tipoServico4);
            pedidoContratadoEntity8.setPrecoContratado(tipoServico4.getPreco());
            pedidoContratadoEntity8.setAgenda(agendaEntity8);
            pedidoContratadoEntity8.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity8);

            PedidoContratadoEntity pedidoContratadoEntity9 = new PedidoContratadoEntity();
            pedidoContratadoEntity9.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity9.setLocal("Apartamento");
            pedidoContratadoEntity9.setDataHoraInicio(LocalDateTime.of(2021,7,7,12,30));
            pedidoContratadoEntity9.setDataHoraFim(LocalDateTime.of(2021,7,7,16,00));
            pedidoContratadoEntity9.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity9.setSituacao(false);
            pedidoContratadoEntity9.setTipoServico(tipoServico);
            pedidoContratadoEntity9.setPrecoContratado(tipoServico.getPreco());
            pedidoContratadoEntity9.setAgenda(agendaEntity9);
            pedidoContratadoEntity9.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity9);

            PedidoContratadoEntity pedidoContratadoEntity10 = new PedidoContratadoEntity();
            pedidoContratadoEntity10.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity10.setLocal("Apartamento");
            pedidoContratadoEntity10.setDataHoraInicio(LocalDateTime.of(2021,7,8,15,30));
            pedidoContratadoEntity10.setDataHoraFim(LocalDateTime.of(2021,7,8,18,00));
            pedidoContratadoEntity10.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity10.setSituacao(false);
            pedidoContratadoEntity10.setTipoServico(tipoServico3);
            pedidoContratadoEntity10.setPrecoContratado(tipoServico3.getPreco());
            pedidoContratadoEntity10.setAgenda(agendaEntity10);
            pedidoContratadoEntity10.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity10);

            PedidoContratadoEntity pedidoContratadoEntity11 = new PedidoContratadoEntity();
            pedidoContratadoEntity11.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity11.setLocal("Apartamento");
            pedidoContratadoEntity11.setDataHoraInicio(LocalDateTime.of(2021,7,9,12,30));
            pedidoContratadoEntity11.setDataHoraFim(LocalDateTime.of(2021,7,9,19,00));
            pedidoContratadoEntity11.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity11.setSituacao(false);
            pedidoContratadoEntity11.setTipoServico(tipoServico3);
            pedidoContratadoEntity11.setPrecoContratado(tipoServico3.getPreco());
            pedidoContratadoEntity11.setAgenda(agendaEntity11);
            pedidoContratadoEntity11.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity11);

            PedidoContratadoEntity pedidoContratadoEntity12 = new PedidoContratadoEntity();
            pedidoContratadoEntity12.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity12.setLocal("Apartamento");
            pedidoContratadoEntity12.setDataHoraInicio(LocalDateTime.of(2021,7,11,12,30));
            pedidoContratadoEntity12.setDataHoraFim(LocalDateTime.of(2021,7,11,16,30));
            pedidoContratadoEntity12.setDespesas(new BigDecimal("40.00"));
            pedidoContratadoEntity12.setSituacao(false);
            pedidoContratadoEntity12.setTipoServico(tipoServico2);
            pedidoContratadoEntity12.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity12.setAgenda(agendaEntity12);
            pedidoContratadoEntity12.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity12);

            PedidoContratadoEntity pedidoContratadoEntity13 = new PedidoContratadoEntity();
            pedidoContratadoEntity13.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity13.setLocal("Apartamento");
            pedidoContratadoEntity13.setDataHoraInicio(LocalDateTime.of(2021,7,12,12,30));
            pedidoContratadoEntity13.setDataHoraFim(LocalDateTime.of(2021,7,12,16,00));
            pedidoContratadoEntity13.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity13.setSituacao(false);
            pedidoContratadoEntity13.setTipoServico(tipoServico2);
            pedidoContratadoEntity13.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity13.setAgenda(agendaEntity13);
            pedidoContratadoEntity13.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity13);

            PedidoContratadoEntity pedidoContratadoEntity14 = new PedidoContratadoEntity();
            pedidoContratadoEntity14.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity14.setLocal("Apartamento");
            pedidoContratadoEntity14.setDataHoraInicio(LocalDateTime.of(2021,7,13,12,30));
            pedidoContratadoEntity14.setDataHoraFim(LocalDateTime.of(2021,7,13,18,00));
            pedidoContratadoEntity14.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity14.setSituacao(false);
            pedidoContratadoEntity14.setTipoServico(tipoServico2);
            pedidoContratadoEntity14.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity14.setAgenda(agendaEntity14);
            pedidoContratadoEntity14.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity14);

            PedidoContratadoEntity pedidoContratadoEntity15 = new PedidoContratadoEntity();
            pedidoContratadoEntity15.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity15.setLocal("Apartamento");
            pedidoContratadoEntity15.setDataHoraInicio(LocalDateTime.of(2021,7,14,12,30));
            pedidoContratadoEntity15.setDataHoraFim(LocalDateTime.of(2021,7,14,18,00));
            pedidoContratadoEntity15.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity15.setSituacao(false);
            pedidoContratadoEntity15.setTipoServico(tipoServico2);
            pedidoContratadoEntity15.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity15.setAgenda(agendaEntity15);
            pedidoContratadoEntity15.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity15);

            PedidoContratadoEntity pedidoContratadoEntity16 = new PedidoContratadoEntity();
            pedidoContratadoEntity16.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity16.setLocal("Apartamento");
            pedidoContratadoEntity16.setDataHoraInicio(LocalDateTime.of(2021,7,15,12,30));
            pedidoContratadoEntity16.setDataHoraFim(LocalDateTime.of(2021,7,15,19,00));
            pedidoContratadoEntity16.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity16.setSituacao(false);
            pedidoContratadoEntity16.setTipoServico(tipoServico2);
            pedidoContratadoEntity16.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity16.setAgenda(agendaEntity16);
            pedidoContratadoEntity16.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity16);

            PedidoContratadoEntity pedidoContratadoEntity17 = new PedidoContratadoEntity();
            pedidoContratadoEntity17.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity17.setLocal("Apartamento");
            pedidoContratadoEntity17.setDataHoraInicio(LocalDateTime.of(2021,7,16,12,30));
            pedidoContratadoEntity17.setDataHoraFim(LocalDateTime.of(2021,7,16,18,00));
            pedidoContratadoEntity17.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity17.setSituacao(false);
            pedidoContratadoEntity17.setTipoServico(tipoServico2);
            pedidoContratadoEntity17.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity17.setAgenda(agendaEntity17);
            pedidoContratadoEntity17.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity17);

            PedidoContratadoEntity pedidoContratadoEntity18 = new PedidoContratadoEntity();
            pedidoContratadoEntity18.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity18.setLocal("Apartamento");
            pedidoContratadoEntity18.setDataHoraInicio(LocalDateTime.of(2021,7,17,12,30));
            pedidoContratadoEntity18.setDataHoraFim(LocalDateTime.of(2021,7,17,17,00));
            pedidoContratadoEntity18.setDespesas(new BigDecimal("20.00"));
            pedidoContratadoEntity18.setSituacao(false);
            pedidoContratadoEntity18.setTipoServico(tipoServico2);
            pedidoContratadoEntity18.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity18.setAgenda(agendaEntity18);
            pedidoContratadoEntity18.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity18);

            PedidoContratadoEntity pedidoContratadoEntity19 = new PedidoContratadoEntity();
            pedidoContratadoEntity19.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity19.setLocal("Apartamento");
            pedidoContratadoEntity19.setDataHoraInicio(LocalDateTime.of(2021,7,18,12,30));
            pedidoContratadoEntity19.setDataHoraFim(LocalDateTime.of(2021,7,18,16,00));
            pedidoContratadoEntity19.setDespesas(new BigDecimal("25.00"));
            pedidoContratadoEntity19.setSituacao(false);
            pedidoContratadoEntity19.setTipoServico(tipoServico2);
            pedidoContratadoEntity19.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity19.setAgenda(agendaEntity19);
            pedidoContratadoEntity19.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity19);

            PedidoContratadoEntity pedidoContratadoEntity20 = new PedidoContratadoEntity();
            pedidoContratadoEntity20.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity20.setLocal("Apartamento");
            pedidoContratadoEntity20.setDataHoraInicio(LocalDateTime.of(2021,7,19,12,30));
            pedidoContratadoEntity20.setDataHoraFim(LocalDateTime.of(2021,7,19,16,00));
            pedidoContratadoEntity20.setDespesas(new BigDecimal("20.00"));
            pedidoContratadoEntity20.setSituacao(false);
            pedidoContratadoEntity20.setTipoServico(tipoServico2);
            pedidoContratadoEntity20.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity20.setAgenda(agendaEntity20);
            pedidoContratadoEntity20.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity20);

            PedidoContratadoEntity pedidoContratadoEntity21 = new PedidoContratadoEntity();
            pedidoContratadoEntity21.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity21.setLocal("Apartamento");
            pedidoContratadoEntity21.setDataHoraInicio(LocalDateTime.of(2021,7,21,12,30));
            pedidoContratadoEntity21.setDataHoraFim(LocalDateTime.of(2021,7,21,16,00));
            pedidoContratadoEntity21.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity21.setSituacao(false);
            pedidoContratadoEntity21.setTipoServico(tipoServico2);
            pedidoContratadoEntity21.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity21.setAgenda(agendaEntity21);
            pedidoContratadoEntity21.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity21);

            PedidoContratadoEntity pedidoContratadoEntity22 = new PedidoContratadoEntity();
            pedidoContratadoEntity22.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity22.setLocal("Apartamento");
            pedidoContratadoEntity22.setDataHoraInicio(LocalDateTime.of(2021,7,22,12,30));
            pedidoContratadoEntity22.setDataHoraFim(LocalDateTime.of(2021,7,22,19,00));
            pedidoContratadoEntity22.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity22.setSituacao(false);
            pedidoContratadoEntity22.setTipoServico(tipoServico2);
            pedidoContratadoEntity22.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity22.setAgenda(agendaEntity22);
            pedidoContratadoEntity22.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity22);

            PedidoContratadoEntity pedidoContratadoEntity23 = new PedidoContratadoEntity();
            pedidoContratadoEntity23.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity23.setLocal("Apartamento");
            pedidoContratadoEntity23.setDataHoraInicio(LocalDateTime.of(2021,7,23,12,30));
            pedidoContratadoEntity23.setDataHoraFim(LocalDateTime.of(2021,7,23,17,00));
            pedidoContratadoEntity23.setDespesas(new BigDecimal("35.00"));
            pedidoContratadoEntity23.setSituacao(false);
            pedidoContratadoEntity23.setTipoServico(tipoServico2);
            pedidoContratadoEntity23.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity23.setAgenda(agendaEntity23);
            pedidoContratadoEntity23.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity23);

            PedidoContratadoEntity pedidoContratadoEntity24 = new PedidoContratadoEntity();
            pedidoContratadoEntity24.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity24.setLocal("Apartamento");
            pedidoContratadoEntity24.setDataHoraInicio(LocalDateTime.of(2021,7,24,12,30));
            pedidoContratadoEntity24.setDataHoraFim(LocalDateTime.of(2021,7,24,18,00));
            pedidoContratadoEntity24.setDespesas(new BigDecimal("45.00"));
            pedidoContratadoEntity24.setSituacao(false);
            pedidoContratadoEntity24.setTipoServico(tipoServico2);
            pedidoContratadoEntity24.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity24.setAgenda(agendaEntity24);
            pedidoContratadoEntity24.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity24);

            PedidoContratadoEntity pedidoContratadoEntity25 = new PedidoContratadoEntity();
            pedidoContratadoEntity25.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity25.setLocal("Apartamento");
            pedidoContratadoEntity25.setDataHoraInicio(LocalDateTime.of(2021,7,25,12,30));
            pedidoContratadoEntity25.setDataHoraFim(LocalDateTime.of(2021,7,25,19,00));
            pedidoContratadoEntity25.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity25.setSituacao(false);
            pedidoContratadoEntity25.setTipoServico(tipoServico2);
            pedidoContratadoEntity25.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity25.setAgenda(agendaEntity25);
            pedidoContratadoEntity25.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity25);

            PedidoContratadoEntity pedidoContratadoEntity26 = new PedidoContratadoEntity();
            pedidoContratadoEntity26.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity26.setLocal("Apartamento");
            pedidoContratadoEntity26.setDataHoraInicio(LocalDateTime.of(2021,7,26,12,30));
            pedidoContratadoEntity26.setDataHoraFim(LocalDateTime.of(2021,7,26,18,00));
            pedidoContratadoEntity26.setDespesas(new BigDecimal("40.00"));
            pedidoContratadoEntity26.setSituacao(false);
            pedidoContratadoEntity26.setTipoServico(tipoServico2);
            pedidoContratadoEntity26.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity26.setAgenda(agendaEntity26);
            pedidoContratadoEntity26.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity26);

            PedidoContratadoEntity pedidoContratadoEntity27 = new PedidoContratadoEntity();
            pedidoContratadoEntity27.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity27.setLocal("Apartamento");
            pedidoContratadoEntity27.setDataHoraInicio(LocalDateTime.of(2021,7,27,12,30));
            pedidoContratadoEntity27.setDataHoraFim(LocalDateTime.of(2021,7,27,18,00));
            pedidoContratadoEntity27.setDespesas(new BigDecimal("20.00"));
            pedidoContratadoEntity27.setSituacao(false);
            pedidoContratadoEntity27.setTipoServico(tipoServico2);
            pedidoContratadoEntity27.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity27.setAgenda(agendaEntity27);
            pedidoContratadoEntity27.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity27);

            PedidoContratadoEntity pedidoContratadoEntity28 = new PedidoContratadoEntity();
            pedidoContratadoEntity28.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity28.setLocal("Apartamento");
            pedidoContratadoEntity28.setDataHoraInicio(LocalDateTime.of(2021,7,28,12,30));
            pedidoContratadoEntity28.setDataHoraFim(LocalDateTime.of(2021,7,28,18,00));
            pedidoContratadoEntity28.setDespesas(new BigDecimal("20.00"));
            pedidoContratadoEntity28.setSituacao(false);
            pedidoContratadoEntity28.setTipoServico(tipoServico2);
            pedidoContratadoEntity28.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity28.setAgenda(agendaEntity28);
            pedidoContratadoEntity28.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity28);

            PedidoContratadoEntity pedidoContratadoEntity29 = new PedidoContratadoEntity();
            pedidoContratadoEntity29.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity29.setLocal("Apartamento");
            pedidoContratadoEntity29.setDataHoraInicio(LocalDateTime.of(2021,7,29,12,30));
            pedidoContratadoEntity29.setDataHoraFim(LocalDateTime.of(2021,7,29,19,00));
            pedidoContratadoEntity29.setDespesas(new BigDecimal("55.00"));
            pedidoContratadoEntity29.setSituacao(false);
            pedidoContratadoEntity29.setTipoServico(tipoServico2);
            pedidoContratadoEntity29.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity29.setAgenda(agendaEntity29);
            pedidoContratadoEntity29.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity29);

            PedidoContratadoEntity pedidoContratadoEntity30 = new PedidoContratadoEntity();
            pedidoContratadoEntity30.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity30.setLocal("Apartamento");
            pedidoContratadoEntity30.setDataHoraInicio(LocalDateTime.of(2021,7,30,12,30));
            pedidoContratadoEntity30.setDataHoraFim(LocalDateTime.of(2021,7,30,17,00));
            pedidoContratadoEntity30.setDespesas(new BigDecimal("25.00"));
            pedidoContratadoEntity30.setSituacao(false);
            pedidoContratadoEntity30.setTipoServico(tipoServico2);
            pedidoContratadoEntity30.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity30.setAgenda(agendaEntity30);
            pedidoContratadoEntity30.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity30);

            PedidoContratadoEntity pedidoContratadoEntity31 = new PedidoContratadoEntity();
            pedidoContratadoEntity31.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity31.setLocal("Apartamento");
            pedidoContratadoEntity31.setDataHoraInicio(LocalDateTime.of(2021,7,31,12,15));
            pedidoContratadoEntity31.setDataHoraFim(LocalDateTime.of(2021,7,31,18,55));
            pedidoContratadoEntity31.setDespesas(new BigDecimal("22.00"));
            pedidoContratadoEntity31.setSituacao(false);
            pedidoContratadoEntity31.setTipoServico(tipoServico2);
            pedidoContratadoEntity31.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity31.setAgenda(agendaEntity31);
            pedidoContratadoEntity31.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity31);

            PedidoContratadoEntity pedidoContratadoEntity32 = new PedidoContratadoEntity();
            pedidoContratadoEntity32.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity32.setLocal("Apartamento");
            pedidoContratadoEntity32.setDataHoraInicio(LocalDateTime.of(2021,7,31,14,30));
            pedidoContratadoEntity32.setDataHoraFim(LocalDateTime.of(2021,7,31,20,00));
            pedidoContratadoEntity32.setDespesas(new BigDecimal("45.00"));
            pedidoContratadoEntity32.setSituacao(false);
            pedidoContratadoEntity32.setTipoServico(tipoServico2);
            pedidoContratadoEntity32.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity32.setAgenda(agendaEntity32);
            pedidoContratadoEntity32.setLoginCliente(loginCliente2);
            pedidoContratadoRepository.save(pedidoContratadoEntity32);

            PedidoContratadoEntity pedidoContratadoEntity33 = new PedidoContratadoEntity();
            pedidoContratadoEntity33.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity33.setLocal("Apartamento");
            pedidoContratadoEntity33.setDataHoraInicio(LocalDateTime.of(2021,7,31,12,30));
            pedidoContratadoEntity33.setDataHoraFim(LocalDateTime.of(2021,7,31,17,00));
            pedidoContratadoEntity33.setDespesas(new BigDecimal("20.00"));
            pedidoContratadoEntity33.setSituacao(false);
            pedidoContratadoEntity33.setTipoServico(tipoServico2);
            pedidoContratadoEntity33.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity33.setAgenda(agendaEntity33);
            pedidoContratadoEntity33.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity33);

            PedidoContratadoEntity pedidoContratadoEntity34 = new PedidoContratadoEntity();
            pedidoContratadoEntity34.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity34.setLocal("Apartamento");
            pedidoContratadoEntity34.setSituacao(true);
            pedidoContratadoEntity34.setTipoServico(tipoServico2);
            pedidoContratadoEntity34.setAgenda(agendaEntity34);
            pedidoContratadoEntity34.setLoginCliente(loginCliente);
            pedidoContratadoEntity34.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoRepository.save(pedidoContratadoEntity34);

            PedidoContratadoEntity pedidoContratadoEntity35 = new PedidoContratadoEntity();
            pedidoContratadoEntity35.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity35.setLocal("Apartamento");
            pedidoContratadoEntity35.setDataHoraInicio(LocalDateTime.of(2021,8,2,12,30));
            pedidoContratadoEntity35.setDataHoraFim(LocalDateTime.of(2021,8,2,17,00));
            pedidoContratadoEntity35.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity35.setSituacao(false);
            pedidoContratadoEntity35.setTipoServico(tipoServico2);
            pedidoContratadoEntity35.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity35.setAgenda(agendaEntity37);
            pedidoContratadoEntity35.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity35);

            PedidoContratadoEntity pedidoContratadoEntity36 = new PedidoContratadoEntity();
            pedidoContratadoEntity36.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity36.setLocal("Apartamento");
            pedidoContratadoEntity36.setDataHoraInicio(LocalDateTime.of(2021,8,3,12,30));
            pedidoContratadoEntity36.setDataHoraFim(LocalDateTime.of(2021,8,3,19,00));
            pedidoContratadoEntity36.setDespesas(new BigDecimal("25.00"));
            pedidoContratadoEntity36.setSituacao(false);
            pedidoContratadoEntity36.setTipoServico(tipoServico2);
            pedidoContratadoEntity36.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity36.setAgenda(agendaEntity38);
            pedidoContratadoEntity36.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity36);

            PedidoContratadoEntity pedidoContratadoEntity37 = new PedidoContratadoEntity();
            pedidoContratadoEntity37.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity37.setLocal("Apartamento");
            pedidoContratadoEntity37.setDataHoraInicio(LocalDateTime.of(2021,8,4,12,30));
            pedidoContratadoEntity37.setDataHoraFim(LocalDateTime.of(2021,8,4,18,00));
            pedidoContratadoEntity37.setDespesas(new BigDecimal("50.00"));
            pedidoContratadoEntity37.setSituacao(false);
            pedidoContratadoEntity37.setTipoServico(tipoServico2);
            pedidoContratadoEntity37.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity37.setAgenda(agendaEntity39);
            pedidoContratadoEntity37.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity37);

            PedidoContratadoEntity pedidoContratadoEntity38 = new PedidoContratadoEntity();
            pedidoContratadoEntity38.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity38.setLocal("Apartamento");
            pedidoContratadoEntity38.setDataHoraInicio(LocalDateTime.of(2021,6,30,12,30));
            pedidoContratadoEntity38.setDataHoraFim(LocalDateTime.of(2021,6,30,16,00));
            pedidoContratadoEntity38.setDespesas(new BigDecimal("30.00"));
            pedidoContratadoEntity38.setSituacao(false);
            pedidoContratadoEntity38.setTipoServico(tipoServico2);
            pedidoContratadoEntity38.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity38.setAgenda(agendaEntity40);
            pedidoContratadoEntity38.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity38);

            PedidoContratadoEntity pedidoContratadoEntity39 = new PedidoContratadoEntity();
            pedidoContratadoEntity39.setDescricao("Apartamento de um quarto");
            pedidoContratadoEntity39.setLocal("Apartamento");
            pedidoContratadoEntity39.setDataHoraInicio(LocalDateTime.of(2021,6,29,12,30));
            pedidoContratadoEntity39.setDataHoraFim(LocalDateTime.of(2021,6,29,17,00));
            pedidoContratadoEntity39.setDespesas(new BigDecimal("25.00"));
            pedidoContratadoEntity39.setSituacao(false);
            pedidoContratadoEntity39.setTipoServico(tipoServico2);
            pedidoContratadoEntity39.setPrecoContratado(tipoServico2.getPreco());
            pedidoContratadoEntity39.setAgenda(agendaEntity41);
            pedidoContratadoEntity39.setLoginCliente(loginCliente);
            pedidoContratadoRepository.save(pedidoContratadoEntity39);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(LaremdiaApplication.class, args);
    }


}
