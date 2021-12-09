package au.com.anz.codingchallenge.blockstacks.requesthandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import org.junit.jupiter.api.Test;

class FileInputHandlerTest {

  @Test
  void shouldThrowRuntimeException_IfFileNotFound() {
    InputHandler inputHandler =
        new FileInputHandler(new File("src/test/resources/TestCase_File_Not_Exists.txt"));
    assertThrows(RuntimeException.class, inputHandler::handleInput);
  }

  @Test
  void shouldThrowIllegalArgException_IfHeight_IsLessThan1() {
    InputHandler inputHandler =
        new FileInputHandler(new File("src/test/resources/TestCase_bad.txt"));
    assertThrows(IllegalArgumentException.class, inputHandler::handleInput);
  }

  @Test
  void shouldThrowIllegalArgException_IfHeight_IsGreaterThan100() {
    InputHandler inputHandler =
            new FileInputHandler(new File("src/test/resources/TestCase_bad.txt"));
    assertThrows(IllegalArgumentException.class, inputHandler::handleInput);
  }

  @Test
  void shouldThrowIllegalArgException_IfWidth_IsLessThan1() {
    InputHandler inputHandler =
            new FileInputHandler(new File("src/test/resources/TestCase_bad.txt"));
    assertThrows(IllegalArgumentException.class, inputHandler::handleInput);
  }

  @Test
  void shouldThrowIllegalArgException_IfWidth_IsGreaterThan100() {
    InputHandler inputHandler =
            new FileInputHandler(new File("src/test/resources/TestCase_bad.txt"));
    assertThrows(IllegalArgumentException.class, inputHandler::handleInput);
  }


  @Test
  void shouldThrowIllegalArgException_IfLength_IsLessThan1() {
    InputHandler inputHandler =
            new FileInputHandler(new File("src/test/resources/TestCase_bad.txt"));
    assertThrows(IllegalArgumentException.class, inputHandler::handleInput);
  }

  @Test
  void shouldThrowIllegalArgException_IfLength_IsGreaterThan100() {
    InputHandler inputHandler =
            new FileInputHandler(new File("src/test/resources/TestCase_bad.txt"));
    assertThrows(IllegalArgumentException.class, inputHandler::handleInput);
  }


  @Test
  void shouldReturn_ListWith_3_Cuboids() {
    InputHandler inputHandler = new FileInputHandler(new File("src/test/resources/TestCase1.txt"));
    assertEquals(3, inputHandler.handleInput().size());
  }
}
