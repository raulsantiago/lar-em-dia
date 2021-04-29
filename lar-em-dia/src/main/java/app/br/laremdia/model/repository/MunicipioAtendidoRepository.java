package app.br.laremdia.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MunicipioAtendidoRepository extends JpaRepository<MunicipioAtendidoEntity, Integer> {

    Optional<MunicipioAtendidoEntity> findByMunicipio(String municipio);

    @Query(value = "SELECT * FROM estado_atendido ea INNER JOIN municipio_atendido ma ON  ea.id_estado = ma.id_municipio_id_estado \n" +
            "where  ( ea.ativo = true and ma.ativo = true and ea.id_estado = :id ) ORDER BY ma.municipio ASC;", nativeQuery = true)
    List< MunicipioAtendidoEntity > listarMunicipioAtivoPorUfAtivo(@Param("id") Integer id);


}
