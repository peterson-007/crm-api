package org.ultimacrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ultimacrm.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    // Verifica se o e-mail já existe no banco de dados
    boolean existsByEmail(String email);
}