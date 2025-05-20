package com.formula.formula.repositories.Base;

import java.util.List;
import java.util.Optional;

public interface ReadRepository<E, K> {
    Optional<E> findById(K id);

    List<E> findAll();
}
