package org.faya.sensei.behavioral.interpreter;

public interface ISQLExpression {

    /**
     * Interpret the sql context.
     *
     * @param context The sql context.
     * @return The result of interpret.
     */
    SQLTable interpret(SQLContext context);
}
