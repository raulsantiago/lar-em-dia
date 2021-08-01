package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.LoginClienteEntity;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoginClienteRepository extends JpaRepository<LoginClienteEntity, Integer> {

    Optional<LoginClienteEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Integer id);

    @Query(value = "select count(ativo) from login_cliente where ativo = true", nativeQuery = true)
    String quantidadeClientesAtivos();

}
