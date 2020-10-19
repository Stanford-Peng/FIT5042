package org.fit5042.pokemon.repository;

import org.fit5042.pokemon.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
