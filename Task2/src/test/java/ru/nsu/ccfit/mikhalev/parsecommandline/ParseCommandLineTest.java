package ru.nsu.ccfit.mikhalev.parsecommandline;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ParseCommandLineTest {

    private static @NotNull Stream<Arguments> argsRightProviderFactoryRightTest() {
        return Stream.of(
                Arguments.of(new String[]{"-i=../Task2/src/main/resources/inputFile.txt"},
                             "../Task2/src/main/resources/inputFile.txt"),
                Arguments.of(new String[]{"-i=file.txt"}, "file.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("argsRightProviderFactoryRightTest")
    void searchCommandLineRightTest(String @NotNull [] testLine, String nameFile) throws ParseException {
        ParseCommandLine.searchCommandLine (testLine);
        assertEquals (ParseCommandLine.getFileInput (), nameFile);
    }

     private static @NotNull Stream<Arguments> argsRightProviderFactoryExceptParse() {
        return Stream.of(
                    Arguments.of(new String[]{"-i=../Task2/src/main/resources/inputFile.doc"},
                                 "Incorrect file name"),
                    Arguments.of(new String[]{"-i=file.rtf"}, "Incorrect file name"),
                    Arguments.of(new String[]{"file.txt"}, "Not found command -i"),
                    Arguments.of(new String[]{"-o=file.txt"}, "Unrecognized option: -o=file.txt"),
                    Arguments.of(new String[]{"-help"}, "call -help")
        );
     }

    @ParameterizedTest
    @MethodSource("argsRightProviderFactoryExceptParse")
        void searchCommandLineExceptParseExceptParse(String @NotNull [] testLine, String messageExc)  throws ParseException {
        ParseException exception = Assertions.assertThrows(ParseException.class,
                                   () -> ParseCommandLine.searchCommandLine (testLine));
        assertEquals (messageExc, exception.getMessage ());
    }
}