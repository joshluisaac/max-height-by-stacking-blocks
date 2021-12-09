package au.com.anz.codingchallenge.blockstacks.core;

import static au.com.anz.codingchallenge.blockstacks.utils.StackUtils.getMax;
import static au.com.anz.codingchallenge.blockstacks.utils.StackUtils.sortCuboids;
import static au.com.anz.codingchallenge.blockstacks.utils.StackUtils.stackHeightSum;

import au.com.anz.codingchallenge.blockstacks.requesthandler.InputHandler;
import au.com.anz.codingchallenge.blockstacks.types.Cuboid;
import au.com.anz.codingchallenge.blockstacks.types.CuboidStack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CuboidStackManager implements LongestIncreasingSubsequence {

  private InputHandler inputHandler;

  @Override
  public void setHandler(InputHandler inputHandler) {
    this.inputHandler = inputHandler;
  }

  @Override
  public int getMaxHeight() {
    final List<List<Cuboid>> cuboids = inputHandler.handleInput();

    final List<Integer> maximums = new ArrayList<>();

    final AtomicInteger leaderHeight = new AtomicInteger();

    final Queue<CuboidStack> cuboidStacks = new ArrayDeque<>();

    final List<Cuboid> sortedCuboids = sortCuboids(cuboids);

    sortedCuboids.forEach(stackUpOneLevel(cuboidStacks, sortedCuboids));

    if (cuboidStacks.isEmpty()) stackEachCuboid(cuboidStacks, sortedCuboids);

    final List<Integer> cuboidVolumes = getCuboidVolumes(cuboidStacks);

    if (cuboidVolumes.size() == 1) return getMaxForHomogeneousStack(cuboids.size(), sortedCuboids);

    final Deque<CuboidStack> trackingQueue = new ArrayDeque<>(cuboidStacks);

    while (!trackingQueue.isEmpty()) {

      CuboidStack head = trackingQueue.pollFirst();
      Cuboid currTail = head.getOngoingStack().peekLast();
      cuboidStacks
          .stream()
          .filter(
              cuboidStack -> {
                Cuboid headCuboid = cuboidStack.getOngoingStack().peekFirst();
                return currTail == headCuboid;
              })
          .forEach(forEachQueueEntry(maximums, leaderHeight, trackingQueue, head));
    }
    return maximums
        .stream()
        .max(Comparator.comparing(Integer::intValue))
        .orElse(getMax(cuboidStacks));
  }

  private int getMaxForHomogeneousStack(int cuboidsSize, List<Cuboid> sortedCuboids) {
    int maxPointVal =
        Stream.of(
                sortedCuboids.get(0).getHeight(),
                sortedCuboids.get(0).getWidth(),
                sortedCuboids.get(0).getLength())
            .max(Comparator.comparing(Integer::intValue))
            .orElse(-1);
    return maxPointVal * cuboidsSize;
  }

  private Consumer<CuboidStack> forEachQueueEntry(
      List<Integer> maximums,
      AtomicInteger leaderHeight,
      Deque<CuboidStack> trackingQueue,
      CuboidStack head) {
    return cuboidStack -> {
      CuboidStack mergedStack = new CuboidStack();
      head.getOngoingStack().forEach(mergedStack::add);
      cuboidStack.getOngoingStack().forEach(mergedStack::add);
      int mergeStackSize = mergedStack.getOngoingStack().size();
      int pointStackSize = cuboidStack.getOngoingStack().size();
      if (mergeStackSize > head.getOngoingStack().size() && mergeStackSize > pointStackSize) {
        int stackMaxHeight = stackHeightSum(mergedStack);
        maximums.add(stackMaxHeight);
        if (stackMaxHeight > leaderHeight.get()) {
          leaderHeight.set(stackMaxHeight);
          trackingQueue.addFirst(mergedStack);
          return;
        }
        trackingQueue.addLast(mergedStack);
      }
    };
  }

  private List<Integer> getCuboidVolumes(Queue<CuboidStack> cuboidStacks) {
    return cuboidStacks
        .stream()
        .map(
            cuboidStack ->
                cuboidStack
                    .getOngoingStack()
                    .stream()
                    .map(this::getVolume)
                    .collect(Collectors.toList()))
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }

  private void stackEachCuboid(Queue<CuboidStack> cuboidStacks, List<Cuboid> sortedCuboids) {
    sortedCuboids.forEach(
        baseCuboid -> {
          CuboidStack newPStack = new CuboidStack();
          newPStack.add(baseCuboid);
          cuboidStacks.add(newPStack);
        });
  }

  private Consumer<Cuboid> stackUpOneLevel(
      Queue<CuboidStack> cuboidStacks, List<Cuboid> sortedCuboids) {
    return baseCuboid -> {
      CuboidStack cuboidStack = new CuboidStack();
      cuboidStack.add(baseCuboid);

      sortedCuboids
          .stream()
          .filter(entryCuboid -> baseCuboid != entryCuboid)
          .filter(entryCuboid -> !Objects.equals(baseCuboid.getGroupId(), entryCuboid.getGroupId()))
          .forEach(
              entryCuboid -> {
                if (cuboidStack.isStackable(entryCuboid)) {
                  CuboidStack newPStack = new CuboidStack();
                  newPStack.add(baseCuboid);
                  newPStack.add(entryCuboid);
                  cuboidStacks.add(newPStack);
                }
              });
    };
  }

  private int getVolume(Cuboid cuboid) {
    return cuboid.getHeight() * cuboid.getWidth() * cuboid.getLength();
  }
}
