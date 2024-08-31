package br.org.dieese.infogreves.repository;

import br.org.dieese.infogreves.domain.Greve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GreveRepository extends JpaRepository<Greve, String> {
}
