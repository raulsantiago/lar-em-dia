package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.LoginClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginClienteRepository extends JpaRepository<LoginClienteEntity, Integer> {

    Optional<LoginClienteEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Integer id);

}
