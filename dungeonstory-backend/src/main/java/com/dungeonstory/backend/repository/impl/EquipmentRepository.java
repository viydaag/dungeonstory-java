package com.dungeonstory.backend.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Tool;
import com.dungeonstory.backend.repository.AbstractRepository;
import com.dungeonstory.backend.repository.JPAService;

public class EquipmentRepository extends AbstractRepository<Equipment, Long> {

    private static final long serialVersionUID = -602767063816004639L;

    @Override
    protected Class<Equipment> getEntityClass() {
        return Equipment.class;
    }

    public List<Equipment> findAllPurchasable() {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Equipment> query = entityManager.createQuery("SELECT e FROM Equipment e WHERE e.isPurchasable = 1", getEntityClass());
            return query.getResultList();
        });
    }

    public List<Equipment> findAllSellable() {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Equipment> query = entityManager.createQuery("SELECT e FROM Equipment e WHERE e.isSellable = 1", getEntityClass());
            return query.getResultList();
        });
    }

    public List<Tool> findAllTools() {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Tool> query = entityManager.createQuery("SELECT e FROM Tool e", Tool.class);
            return query.getResultList();
        });
    }

}
