package org.faya.sensei.behavioral;

import org.faya.sensei.behavioral.interpreter.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterPatternTest {

    private static Class<? extends ISQLExpression> selectExpressionClass;
    private static List<SQLRow> userData = List.of(
            new SQLRow(Map.of("name", "Billy", "age", 48, "quote", "戒の心、赞の心、许の心")), // R.I.P
            new SQLRow(Map.of("name", "Van", "age", 51, "quote", "The deep dark fantasy!"))
    );

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.behavioral.interpreter";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends ISQLExpression>> expressionClasses = reflections.getSubTypesOf(ISQLExpression.class);

        assertFalse(expressionClasses.isEmpty());

        selectExpressionClass = expressionClasses.stream()
                .filter(clazz -> {
                    int counter = 0;

                    for (final Field field : clazz.getDeclaredFields()) {
                        if (ISQLExpression.class.isAssignableFrom(field.getType())) {
                            counter++;
                        }
                    }

                    return counter == 3;
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SelectExpression class not found"));
    }

    @Test
    public void testSelectExpression() throws Exception {
        final SQLContext context = new SQLContext("SELECT name, quote FROM users WHERE age > 30 AND name = 'Billy'");
        final SQLDatabase database = context.getDatabase();
        database.addTable("users", new SQLTable("users", Set.of("name", "age", "quote"), userData));

        final ISQLExpression selectExpression = selectExpressionClass.getConstructor().newInstance();
        final SQLTable result = selectExpression.interpret(context);
        final List<SQLRow> rows = result.getRows();

        assertEquals(1, rows.size());
        assertEquals(new SQLRow(Map.of("name", "Billy", "quote", "戒の心、赞の心、许の心")), rows.getFirst());
    }

    @Test
    public void testSelectExpression_WithoutWhere() throws Exception {
        final SQLContext context = new SQLContext("SELECT name FROM users");
        final SQLDatabase database = context.getDatabase();
        database.addTable("users", new SQLTable("users", Set.of("name", "age", "quote"), userData));

        final ISQLExpression selectExpression = selectExpressionClass.getConstructor().newInstance();
        final SQLTable result = selectExpression.interpret(context);
        final List<SQLRow> rows = result.getRows();

        assertEquals(2, rows.size());
        assertArrayEquals(
                List.of(new SQLRow(Map.of("name", "Billy")), new SQLRow(Map.of("name", "Van"))).toArray(),
                rows.toArray()
        );
    }
}
