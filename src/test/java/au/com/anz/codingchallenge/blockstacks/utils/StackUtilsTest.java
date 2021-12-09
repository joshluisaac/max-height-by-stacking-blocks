package au.com.anz.codingchallenge.blockstacks.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import au.com.anz.codingchallenge.blockstacks.types.Cuboid;
import au.com.anz.codingchallenge.blockstacks.types.CuboidStack;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class StackUtilsTest {

  @Test
  void rotatePermutations_ShouldReturn_Two_Permutations() {
    List<Cuboid> cuboids =
        StackUtils.rotatePermutations(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    assertEquals(2, cuboids.size());
  }

  @Test
  void rotatePermutations_ShouldSwap_Width_For_Length() {
    List<Cuboid> cuboids =
        StackUtils.rotatePermutations(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    assertEquals(3, cuboids.get(1).getWidth());
    assertEquals(2, cuboids.get(1).getLength());
  }

  @Test
  void shouldReturn_TheMax_Between_TwoOrMore_Stacks() {
    CuboidStack cuboidStack0 = new CuboidStack();

    cuboidStack0.add(new Cuboid(95, 53, 37, UUID.randomUUID().toString()));
    cuboidStack0.add(new Cuboid(50, 45, 20, UUID.randomUUID().toString()));
    cuboidStack0.add(new Cuboid(45, 23, 12, UUID.randomUUID().toString()));

    CuboidStack cuboidStack1 = new CuboidStack();
    cuboidStack1.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    cuboidStack1.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));

    final Queue<CuboidStack> cuboidStacks = new ArrayDeque<>();
    cuboidStacks.add(cuboidStack0);
    cuboidStacks.add(cuboidStack1);
    int max = StackUtils.getMax(cuboidStacks);
    assertEquals(190, max);
  }

  @Test
  void shouldReturn_The_SumOfStack_Height() {
    CuboidStack cuboidStack = new CuboidStack();
    cuboidStack.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    cuboidStack.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    int sum = StackUtils.stackHeightSum(cuboidStack);
    assertEquals(2, sum);
  }
}
