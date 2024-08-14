package org.faya.sensei.behavioral.command;

public interface ITransaction {

    void executeCommand(ICommand command);

    void rollback();
}
