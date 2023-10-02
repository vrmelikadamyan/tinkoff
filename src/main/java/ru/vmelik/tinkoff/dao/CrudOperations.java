package ru.vmelik.tinkoff.dao;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface CrudOperations<T, ID> {

    @Nonnull
    T create(@Nonnull T entity);

    @Nullable
    T findById(@Nonnull ID id);

    @Nonnull
    T update(@Nonnull T entity);

    @Nullable
    T delete(@Nonnull ID id);
}
