package com.eauction.cqrs.core.infrastructure;

import com.eauction.cqrs.core.commands.BaseCommand;
import com.eauction.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
