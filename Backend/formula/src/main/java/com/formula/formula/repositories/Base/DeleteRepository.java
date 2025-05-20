package com.formula.formula.repositories.Base;

public interface DeleteRepository<E, K> {
    void delete(E Entity);
}
