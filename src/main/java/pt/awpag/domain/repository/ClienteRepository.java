package pt.awpag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.awpag.domain.models.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findAllByNome(String nome);
    List<Cliente> findAllByNomeContaining(String nome);
    boolean existsByEmail(String email);
}
