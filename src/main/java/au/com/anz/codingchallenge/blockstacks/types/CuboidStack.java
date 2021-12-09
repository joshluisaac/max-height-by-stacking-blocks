package au.com.anz.codingchallenge.blockstacks.types;

import java.util.ArrayDeque;
import java.util.Deque;

public class CuboidStack {

  private final Deque<Cuboid> ongoingStack = new ArrayDeque<>();

  public void add(Cuboid newCuboid) {
    if (ongoingStack.contains(newCuboid)) return;
    if (tryStacking(newCuboid)) return;
    tryStacking(newCuboid);
  }

  private boolean tryStacking(Cuboid newCuboid) {
    boolean isPlaceable = isStackable(newCuboid);
    if (!isPlaceable) return false;
    return ongoingStack.add(newCuboid);
  }

  public boolean isStackable(Cuboid newCuboid) {
    if (ongoingStack.isEmpty()) return true;
    Cuboid lastCuboid = ongoingStack.peekLast();
    return hasEqualVolumes(newCuboid, lastCuboid) || satisfiesInvariant(newCuboid, lastCuboid);
  }

  private boolean satisfiesInvariant(Cuboid newCuboid, Cuboid lastCuboid) {
    return checkWidth(newCuboid, lastCuboid)
        && checkLength(newCuboid, lastCuboid)
        && checkHeight(newCuboid, lastCuboid);
  }

  private boolean checkHeight(Cuboid newCuboid, Cuboid lastCuboid) {
    return newCuboid.height <= lastCuboid.height;
  }

  private boolean checkLength(Cuboid newCuboid, Cuboid lastCuboid) {
    return newCuboid.width <= lastCuboid.width;
  }

  private boolean checkWidth(Cuboid newCuboid, Cuboid lastCuboid) {
    return newCuboid.length <= lastCuboid.length;
  }

  private boolean hasEqualVolumes(Cuboid newCuboid, Cuboid lastCuboid) {
    return newCuboid.getVolume() == lastCuboid.getVolume();
  }

  public Deque<Cuboid> getOngoingStack() {
    return ongoingStack;
  }
}
