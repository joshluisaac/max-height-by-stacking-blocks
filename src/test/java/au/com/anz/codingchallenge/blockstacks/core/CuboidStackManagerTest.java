package au.com.anz.codingchallenge.blockstacks.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import au.com.anz.codingchallenge.blockstacks.requesthandler.FileInputHandler;
import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CuboidStackManagerTest {

  @ParameterizedTest
  @MethodSource("argsProvider")
  void testCase_ShouldReturn_MaxHeight(int expected, File file) {
    CuboidStackManager stackManager = new CuboidStackManager();
    stackManager.setHandler(new FileInputHandler(file));
    assertEquals(expected, stackManager.getMaxHeight());
  }

  static Stream<Arguments> argsProvider() {
    return Stream.of(
        arguments(18, new File("src/test/resources/TestCase1.txt")),
        arguments(45, new File("src/test/resources/TestCase2.txt")),
        arguments(236, new File("src/test/resources/TestCase3.txt")),
        arguments(102, new File("src/test/resources/TestCase4.txt")),
        arguments(76, new File("src/test/resources/TestCase5.txt")));
  }
}
