package org.faya.sensei.behavioral;

import org.faya.sensei.behavioral.command.Database;
import org.faya.sensei.behavioral.command.ICommand;
import org.faya.sensei.behavioral.command.ITransaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandPatternTest {

    private static Class<? extends ICommand> insertCommandClass;
    private static Class<? extends ICommand> updateCommandClass;
    private static Class<? extends ICommand> deleteCommandClass;
    private static Class<? extends ITransaction> transactionClass;

    private Database database;
    private ITransaction transaction;

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.behavioral.command";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends ITransaction>> transactionClasses = reflections.getSubTypesOf(ITransaction.class);
        final Set<Class<? extends ICommand>> commandClasses = reflections.getSubTypesOf(ICommand.class);

        assertFalse(transactionClasses.isEmpty());
        assertFalse(commandClasses.isEmpty());

        transactionClass = transactionClasses.iterator().next();
        insertCommandClass = commandClasses.stream()
                .filter(cls -> {
                    try {
                        cls.getConstructor(Database.class, String.class);
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("InsertCommand class not found"));
        updateCommandClass = commandClasses.stream()
                .filter(cls -> {
                    try {
                        cls.getConstructor(Database.class, int.class, String.class);
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UpdateCommand class not found"));
        deleteCommandClass = commandClasses.stream()
                .filter(cls -> {
                    try {
                        cls.getConstructor(Database.class, int.class);
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("DeleteCommand class not found"));
    }

    @BeforeEach
    void setUp() throws Exception {
        transaction = transactionClass.getConstructor().newInstance();
        database = new Database();
    }

    @Test
    void testInsertCommand() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");

        transaction.executeCommand(insertCommand);

        assertTrue(database.getRows().contains("foo"));
    }

    @Test
    void testUpdateCommand() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final Constructor<? extends ICommand> updateConstructor = updateCommandClass.getConstructor(Database.class, int.class, String.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");
        final ICommand updateCommand = updateConstructor.newInstance(database, 0, "bar");

        transaction.executeCommand(insertCommand);
        transaction.executeCommand(updateCommand);

        assertFalse(database.getRows().contains("foo"));
        assertTrue(database.getRows().contains("bar"));
    }

    @Test
    void testDeleteCommand() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final Constructor<? extends ICommand> deleteConstructor = deleteCommandClass.getConstructor(Database.class, int.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");
        final ICommand deleteCommand = deleteConstructor.newInstance(database, 0);

        transaction.executeCommand(insertCommand);
        transaction.executeCommand(deleteCommand);

        assertFalse(database.getRows().contains("foo"));
    }

    @Test
    void testInsertCommand_Rollback() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");

        transaction.executeCommand(insertCommand);
        transaction.rollback();

        assertFalse(database.getRows().contains("foo"));
    }

    @Test
    void testUpdateCommand_Rollback() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final Constructor<? extends ICommand> updateConstructor = updateCommandClass.getConstructor(Database.class, int.class, String.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");
        final ICommand updateCommand = updateConstructor.newInstance(database, 0, "bar");

        transaction.executeCommand(insertCommand);
        transaction.executeCommand(updateCommand);
        transaction.rollback();

        assertTrue(database.getRows().contains("foo"));
        assertFalse(database.getRows().contains("bar"));
    }

    @Test
    void testDeleteCommand_Rollback() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final Constructor<? extends ICommand> deleteConstructor = deleteCommandClass.getConstructor(Database.class, int.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");
        final ICommand deleteCommand = deleteConstructor.newInstance(database, 0);

        transaction.executeCommand(insertCommand);
        transaction.executeCommand(deleteCommand);
        transaction.rollback();

        assertTrue(database.getRows().contains("foo"));
    }

    @Test
    void testCommands() throws Exception {
        final Constructor<? extends ICommand> insertConstructor = insertCommandClass.getConstructor(Database.class, String.class);
        final Constructor<? extends ICommand> updateConstructor = updateCommandClass.getConstructor(Database.class, int.class, String.class);
        final Constructor<? extends ICommand> deleteConstructor = deleteCommandClass.getConstructor(Database.class, int.class);
        final ICommand insertCommand = insertConstructor.newInstance(database, "foo");
        final ICommand updateCommand = updateConstructor.newInstance(database, 0, "bar");
        final ICommand deleteCommand = deleteConstructor.newInstance(database, 0);

        transaction.executeCommand(insertCommand);
        transaction.executeCommand(updateCommand);
        transaction.executeCommand(deleteCommand);

        transaction.rollback();
        assertTrue(database.getRows().contains("bar"));

        transaction.rollback();
        assertTrue(database.getRows().contains("foo"));
        assertFalse(database.getRows().contains("bar"));

        transaction.rollback();
        assertFalse(database.getRows().contains("foo"));
    }
}
