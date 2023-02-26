package org.ru.nsu.mikhalev.task2.LoaderFactory;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.ru.nsu.mikhalev.task2.Operations.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class LoaderFactoryTest {

    private static @NotNull Stream<Arguments> argsRightProviderFactory() {
        return Stream.of(
                Arguments.of("DEFINE", Define.class),
                Arguments.of("DIVISION", Division.class),
                Arguments.of("MINUS", Minus.class),
                Arguments.of("MULT", Multiplication.class),
                Arguments.of("PLUS", Plus.class),
                Arguments.of("POP", Pop.class),
                Arguments.of("PRINT", Print.class),
                Arguments.of("PUSH", Push.class),
                Arguments.of("SQRT", Sqrt.class)
                );
    }

    @ParameterizedTest
    @MethodSource("argsRightProviderFactory")
    public void createInstanceClassTest(String nameClass, Class<? super Operation> other) throws Exception {
        LoaderFactory loaderFactory = new LoaderFactory ();
        Operation clazz = loaderFactory.createInstanceClass (nameClass);

        assertEquals (clazz.getClass (), other);
    }
    private static @NotNull Stream<Arguments> argsWrongProviderFactory() {
        return Stream.of (
                Arguments.of ("DEFINEE")
        );
    }

        @ParameterizedTest
        @MethodSource("argsWrongProviderFactory")
        public void createInstanceClassTestExcept(String nameClass) throws Exception {
            LoaderFactory loaderFactory = new LoaderFactory ();

            NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                    () ->loaderFactory.createInstanceClass (nameClass), "This command wasn't found null");
            assertEquals("This command wasn't found null", exception.getMessage ());
        }

    }
