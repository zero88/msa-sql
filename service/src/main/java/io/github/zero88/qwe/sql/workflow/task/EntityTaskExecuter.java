package io.github.zero88.qwe.sql.workflow.task;

import java.util.Objects;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.github.zero88.qwe.workflow.TaskExecuter;

/**
 * Represents {@code Entity Task} executer.
 *
 * @see EntityTask
 * @see TaskExecuter
 * @since 1.0.0
 */
public interface EntityTaskExecuter extends TaskExecuter<EntityTask> {

    /**
     * Declares {@code Entity Task}, it can be {@code null} for skipping execution
     *
     * @return entity task
     * @see EntityTask
     * @since 1.0.0
     */
    EntityTask task();

    /**
     * Represents for {@code Entity Task blocking-executer}.
     *
     * @since 1.0.0
     */
    interface BlockingEntityTaskExecuter
        extends BlockingTaskExecuter<EntityTask, VertxPojo, VertxPojo>, EntityTaskExecuter {

        /**
         * {@code NONE} blocking-executer.
         */
        BlockingEntityTaskExecuter NONE = () -> null;

        /**
         * Create {@code Entity Task blocking-executer}
         *
         * @param task the task
         * @return blocking-executer
         * @since 1.0.0
         */
        static BlockingEntityTaskExecuter create(EntityTask task) {
            return Objects.isNull(task) ? NONE : () -> task;
        }

    }


    /**
     * Represents for {@code Entity Task async-executer}.
     *
     * @since 1.0.0
     */
    interface AsyncEntityTaskExecuter extends AsyncTaskExecuter<EntityTask, VertxPojo>, EntityTaskExecuter {

        /**
         * {@code NONE} async-executer.
         */
        AsyncEntityTaskExecuter NONE = () -> null;

        /**
         * Create {@code Entity Task async-executer}
         *
         * @param task the task
         * @return async-executer
         * @since 1.0.0
         */
        static AsyncEntityTaskExecuter create(EntityTask task) {
            return Objects.isNull(task) ? NONE : () -> task;
        }

    }

}
