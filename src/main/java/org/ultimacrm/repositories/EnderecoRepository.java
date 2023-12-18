package org.ultimacrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ultimacrm.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
