package org.ultimacrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ultimacrm.models.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
