package app.br.laremdia;

import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.LoginProfissionalEntity;
import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import app.br.laremdia.model.entity.TipoServicoEntity;
import app.br.laremdia.model.repository.LoginClienteRepository;
import app.br.laremdia.model.repository.LoginProfissionalRepository;
import app.br.laremdia.model.repository.ServicoProfissionalRepository;
import app.br.laremdia.model.repository.TipoServicoRepository;
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
            TipoServicoRepository tipoServicoRepository, ServicoProfissionalRepository servicoProfissionalRepository){
        return args -> {
            LoginProfissionalEntity loginProfissional = new LoginProfissionalEntity();
            loginProfissional.setNome("Jaque");
            loginProfissional.setCpf("80860752062");
            loginProfissional.setEmail("jaq@gmail.com");
            loginProfissional.setCelular("21988671010");
            loginProfissional.setSenha("123");
            loginProfissional.setAtivo(true);
            repository.save(loginProfissional);

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
            loginCliente.setEstado("RJ");
            loginCliente.setMunicipio("Nova Iguaçu");
            loginCliente.setReferencia("próximo a coca-cola");
            loginCliente.setAtivo(true);
            repositoryCliente.save(loginCliente);

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
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(LaremdiaApplication.class, args);
    }

}
