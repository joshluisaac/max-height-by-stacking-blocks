package au.com.anz.codingchallenge.blockstacks.utils;

import au.com.anz.codingchallenge.blockstacks.types.Cuboid;
import au.com.anz.codingchallenge.blockstacks.types.CuboidStack;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class StackUtils {

  private StackUtils() {}

  public static List<Cuboid> rotatePermutations(Cuboid cuboid) {
    Cuboid cuboid0 =
        new Cuboid(cuboid.getHeight(), cuboid.getWidth(), cuboid.getLength(), cuboid.getGroupId());
    Cuboid cuboid1 =
        new Cuboid(cuboid.getHeight(), cuboid.getLength(), cuboid.getWidth(), cuboid.getGroupId());
    return List.of(cuboid0, cuboid1);
  }

  public static List<Cuboid> sortCuboids(List<List<Cuboid>> cuboids) {
    return cuboids
        .stream()
        .flatMap(Collection::stream)
        .sorted(Comparator.comparing(Cuboid::getHeight).reversed())
        .collect(Collectors.toList());
  }

  public static Integer getMax(Queue<CuboidStack> cuboidStacks) {
    return cuboidStacks
        .stream()
        .map(
            cuboidStack ->
                cuboidStack
                    .getOngoingStack()
                    .stream()
                    .map(Cuboid::getHeight)
                    .reduce(0, Integer::sum))
        .max(Comparator.comparing(Integer::intValue))
        .orElse(-1);
  }

  public static int stackHeightSum(CuboidStack cuboidStack) {
    return cuboidStack.getOngoingStack().stream().map(Cuboid::getHeight).reduce(0, Integer::sum);
  }
}
