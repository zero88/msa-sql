package io.github.zero88.qwe.sql.workflow;

import java.util.function.BiFunction;

import org.jooq.Configuration;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.github.zero88.qwe.sql.workflow.step.DQLStep;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

import io.github.zero88.qwe.dto.msg.RequestData;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Accessors(fluent = true)
public final class DefaultDQLWorkflow<T extends VertxPojo> extends AbstractSQLWorkflow implements DQLWorkflow<T> {

    @NonNull
    private final DQLStep<T> sqlStep;
    @NonNull
    private final BiFunction<RequestData, T, Single<JsonObject>> transformer;

    @Override
    @SuppressWarnings("unchecked")
    protected @NonNull Single<JsonObject> run(@NonNull RequestData requestData, Configuration runtimeConfig) {
        final RequestData reqData = normalize().apply(requestData);
        return sqlStep().query(reqData, validator().andThen(afterValidation()))
                        .flatMap(pojo -> taskManager().postBlockingExecuter()
                                                      .execute(initSuccessData(reqData, pojo))
                                                      .switchIfEmpty(Single.just(pojo)))
                        .doOnSuccess(pojo -> taskManager().postAsyncExecuter().execute(initSuccessData(reqData, pojo)))
                        .doOnError(err -> taskManager().postAsyncExecuter().execute(initErrorData(reqData, err)))
                        .flatMap(pojo -> transformer().apply(reqData, (T) pojo));
    }

}
