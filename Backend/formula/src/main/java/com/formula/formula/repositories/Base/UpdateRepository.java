package com.formula.formula.repositories.Base;

public interface UpdateRepository<E, K> {
    void update(E entity);
}
