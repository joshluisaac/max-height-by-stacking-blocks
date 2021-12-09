package au.com.anz.codingchallenge.blockstacks.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class CuboidStackTest {

  @Test
  void shouldAddToStack() {
    CuboidStack cuboidStack = new CuboidStack();
    cuboidStack.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    assertEquals(1, cuboidStack.getOngoingStack().size());
  }

  @Test
  void shouldAdd_TwoCuboidsOnStack() {
    CuboidStack cuboidStack = new CuboidStack();
    cuboidStack.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    cuboidStack.add(new Cuboid(1, 2, 3, UUID.randomUUID().toString()));
    assertEquals(2, cuboidStack.getOngoingStack().size());
  }

  @Test
  void shouldReturnFalse_WhenCuboid_CannotBeStacked() {
    CuboidStack cuboidStack = new CuboidStack();
    cuboidStack.add(new Cuboid(38, 25, 45, UUID.randomUUID().toString()));
    boolean expected = cuboidStack.isStackable(new Cuboid(76, 35, 3, UUID.randomUUID().toString()));
    assertFalse(expected);
  }
}
