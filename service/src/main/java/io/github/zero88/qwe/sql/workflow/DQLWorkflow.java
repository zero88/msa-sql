package io.github.zero88.qwe.sql.workflow;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import io.github.zero88.qwe.sql.workflow.step.DQLStep;

import lombok.NonNull;

/**
 * DML workflow is used for querying one data from database.
 *
 * @param <T> Type of {@code VertxPojo}
 * @see SQLWorkflow
 * @since 1.0.0
 */
public interface DQLWorkflow<T extends VertxPojo> extends SQLWorkflow {

    /**
     * Declares {@code DQL step}
     *
     * @return the DQL step
     * @since 1.0.0
     */
    @NonNull DQLStep<T> sqlStep();

}
