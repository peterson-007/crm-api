package org.ultimacrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ultimacrm.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Verifica se o nome do produto já existe no banco de dados
    boolean existsByNome(String nome);
}
