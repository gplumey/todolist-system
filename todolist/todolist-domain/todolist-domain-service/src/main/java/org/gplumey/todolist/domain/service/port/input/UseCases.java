package org.gplumey.todolist.domain.service.port.input;

import org.gplumey.common.domain.core.usecase.CommandHandler;
import org.gplumey.common.domain.core.usecase.QueryHandler;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.input.query.GetTodoQuery;
import org.gplumey.todolist.domain.service.port.input.query.GetTodolistQuery;

import java.util.Collection;


public class UseCases {
    public static class Commands {

        public interface CreateTodolistUseCase extends CommandHandler<Todolist, CreateTodolistCommand> {
        }


        public interface CreateTodoUseCase extends CommandHandler<Todo, CreateTodoCommand> {
        }
    }

    public static class Queries {
        public interface GetAllTodolistUseCase extends QueryHandler<Collection<Todolist>, GetAllTodolistQuery> {
        }

        public interface GetTodoUsecase extends QueryHandler<Todo, GetTodoQuery> {
        }

        public interface GetTodolistUsecase extends QueryHandler<Todolist, GetTodolistQuery> {
        }
    }
}
