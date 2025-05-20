package com.formula.formula.repositories.Base;

public interface CreateRepository<E, K> {
    void save(E entity);
}
