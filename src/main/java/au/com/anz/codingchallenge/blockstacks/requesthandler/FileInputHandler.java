package au.com.anz.codingchallenge.blockstacks.requesthandler;

import static au.com.anz.codingchallenge.blockstacks.utils.StackUtils.rotatePermutations;

import au.com.anz.codingchallenge.blockstacks.types.Cuboid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileInputHandler implements InputHandler {

  private final File file;

  public FileInputHandler(File file) {
    this.file = file;
  }

  @Override
  public List<List<Cuboid>> handleInput() {
    List<String> lines;

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      lines = bufferedReader.lines().collect(Collectors.toList());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(
          String.format("File %s cannot be found", file.getAbsoluteFile()), e);
    } catch (IOException e) {
      throw new RuntimeException("IOException Occurred file processing file", e);
    }

    return lines
        .stream()
        .map(
            line -> {
              String[] tokens = line.split(",");
              int height = Integer.parseInt(tokens[0].trim());
              int width = Integer.parseInt(tokens[1].trim());
              int length = Integer.parseInt(tokens[2].trim());
              validateTokens(height, width, length);
              List<Integer> ints = Arrays.asList(height, width, length);
              ints.sort(Comparator.comparing(Integer::intValue).reversed());
              return rotatePermutations(
                  new Cuboid(ints.get(0), ints.get(1), ints.get(2), UUID.randomUUID().toString()));
            })
        .collect(Collectors.toList());
  }

  private static void validateTokens(int height, int width, int length) {
    if (height < 1 || height > 100)
      throw new IllegalArgumentException(
          String.format("%d is not within specification (1 <= height <= 100)", height));
    if (width < 1 || width > 100)
      throw new IllegalArgumentException(
          String.format("%d is not within specification (1 <= width <= 100)", width));
    if (length < 1 || length > 100)
      throw new IllegalArgumentException(
          String.format("%d is not within specification (1 <= length <= 100)", length));
  }
}
