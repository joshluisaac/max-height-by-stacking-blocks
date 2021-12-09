package au.com.anz.codingchallenge.blockstacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MaxHeightStackingApplicationTest {

    @Test
    void shouldRun_Application(){
        String[] args = {"src/test/resources/TestCase2.txt"};
        assertDoesNotThrow(() -> MaxHeightStackingApplication.main(args));
    }

    @Test
    void shouldThrow_ExceptionWhen_ArgLengthIsZero(){
        String[] args = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MaxHeightStackingApplication.main(args));
        assertEquals("Please specify the file path in command line.", exception.getMessage());
    }

    @Test
    void shouldThrow_ExceptionWhen_PathIsEmptyString(){
        String[] args = {""};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MaxHeightStackingApplication.main(args));
        assertEquals("Please check and specify file path.", exception.getMessage());
    }

    @Test
    void shouldThrow_ExceptionWhen_PathIsNull(){
        String[] args = {null};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MaxHeightStackingApplication.main(args));
        assertEquals("Please check and specify file path.", exception.getMessage());
    }

}