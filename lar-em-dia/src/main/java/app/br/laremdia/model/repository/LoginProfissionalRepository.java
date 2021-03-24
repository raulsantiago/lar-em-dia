package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.LoginProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginProfissionalRepository extends JpaRepository<LoginProfissionalEntity, Integer> {

    Optional<LoginProfissionalEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Integer id);

}
