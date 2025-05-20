package com.formula.formula.repositories.impl;

import java.util.List;
import java.util.Optional;

import com.formula.formula.repositories.Base.CreateRepository;
import com.formula.formula.repositories.Base.DeleteRepository;
import com.formula.formula.repositories.Base.ReadRepository;
import com.formula.formula.repositories.Base.UpdateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class BaseRepository<E, K>
        implements CreateRepository<E, K>, ReadRepository<E, K>, UpdateRepository<E, K>, DeleteRepository<E, K> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<E> entityClass;

    protected BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    @Override
    public void save(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Transactional
    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery("select e from " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }
}
