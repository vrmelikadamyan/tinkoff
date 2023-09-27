package ru.vmelik.tinkoff.dao.fake;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.vmelik.tinkoff.dao.CrudOperations;
import ru.vmelik.tinkoff.model.Identity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class FakeCrudOperations<T extends Identity<UUID>> implements CrudOperations<T, UUID> {
    protected final Map<UUID, T> entityMap = new HashMap<>();

    @Nonnull
    @Override
    public T create(@Nonnull T entity) {
        entity.setId(UUID.randomUUID());
        entityMap.put(entity.getId(), entity);

        return entity;
    }

    @Nullable
    @Override
    public T findById(@Nonnull UUID id) {
        return entityMap.get(id);
    }

    @Nonnull
    @Override
    public T update(@Nonnull T entity) {
        entityMap.put(entity.getId(), entity);

        return entity;
    }

    @Nullable
    @Override
    public T delete(@Nonnull UUID id) {
        return entityMap.remove(id);
    }
}
