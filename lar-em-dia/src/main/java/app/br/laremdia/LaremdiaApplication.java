package app.br.laremdia;

import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import app.br.laremdia.model.entity.TipoServicoEntity;
import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import app.br.laremdia.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

@SpringBootApplication
public class LaremdiaApplication {

    @Bean
    public CommandLineRunner commandLineRunner(
            @Autowired LoginProfissionalRepository repository, LoginClienteRepository repositoryCliente,
            TipoServicoRepository tipoServicoRepository, ServicoProfissionalRepository servicoProfissionalRepository,
            EstadoAtendidoRepository estadoAtendidoRepository, MunicipioAtendidoRepository municipioAtendidoRepository){
        return args -> {
            LoginProfissionalEntity loginProfissional = new LoginProfissionalEntity();
            loginProfissional.setNome("Jaque");
            loginProfissional.setCpf("80860752062");
            loginProfissional.setEmail("jaq@gmail.com");
            loginProfissional.setCelular("21988671010");
            loginProfissional.setSenha("123");
            loginProfissional.setAtivo(true);
            repository.save(loginProfissional);

            ServicoProfissionalEntity servicoProfissional = new ServicoProfissionalEntity();
            servicoProfissional.setNome("Ar condicionado de janela");
            servicoProfissional.setAtivo(true);
            servicoProfissionalRepository.save(servicoProfissional);

            TipoServicoEntity tipoServico = new TipoServicoEntity();
            tipoServico.setNome("Limpeza");
            tipoServico.setPreco(new BigDecimal("123456.99"));
            tipoServico.setServicoProfissional(servicoProfissional);
            tipoServicoRepository.save(tipoServico);

            TipoServicoEntity tipoServico2 = new TipoServicoEntity();
            tipoServico2.setNome("Instalação");
            tipoServico2.setPreco(new BigDecimal("0.01"));
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

            MunicipioAtendidoEntity municipioAtendidoEntity2 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity2.setMunicipio("Teste 1");
            municipioAtendidoEntity2.setAtivo(true);
            municipioAtendidoEntity2.setEstado(estadoAtendidoEntity);
            municipioAtendidoRepository.save(municipioAtendidoEntity2);

            MunicipioAtendidoEntity municipioAtendidoEntity3 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity3.setMunicipio("Teste 2");
            municipioAtendidoEntity3.setAtivo(false);
            municipioAtendidoEntity3.setEstado(estadoAtendidoEntity);
            municipioAtendidoRepository.save(municipioAtendidoEntity3);

            MunicipioAtendidoEntity municipioAtendidoEntity4 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity4.setMunicipio("Teste 3");
            municipioAtendidoEntity4.setAtivo(true);
            municipioAtendidoEntity4.setEstado(estadoAtendidoEntity2);
            municipioAtendidoRepository.save(municipioAtendidoEntity4);

            MunicipioAtendidoEntity municipioAtendidoEntity5 = new MunicipioAtendidoEntity();
            municipioAtendidoEntity5.setMunicipio("Teste 4");
            municipioAtendidoEntity5.setAtivo(false);
            municipioAtendidoEntity5.setEstado(estadoAtendidoEntity2);
            municipioAtendidoRepository.save(municipioAtendidoEntity5);

            LoginClienteEntity loginCliente = new LoginClienteEntity();
            loginCliente.setNome("Marcia");
            loginCliente.setCpf("83918396053");
            loginCliente.setEmail("marcia@gmail.com");
            loginCliente.setCelular("21988671020");
            loginCliente.setSenha("123");
            loginCliente.setEndereco("Rua Rafa Audax");
            loginCliente.setNumero("1005");
            loginCliente.setBairro("Laranjeiras");
            loginCliente.setComplemento("próximo a coca-cola");
            loginCliente.setMunicipioAtendido(municipioAtendidoEntity);
            loginCliente.setEstadoAtendido(estadoAtendidoEntity);
//            loginCliente.setEstado("RJ");
//            loginCliente.setMunicipio("Nova Iguaçu");
            loginCliente.setReferencia("próximo a coca-cola");
            loginCliente.setAtivo(true);
            repositoryCliente.save(loginCliente);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(LaremdiaApplication.class, args);
    }

}
