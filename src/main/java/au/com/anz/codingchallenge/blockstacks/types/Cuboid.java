package au.com.anz.codingchallenge.blockstacks.types;

public class Cuboid {
  int height;
  int width;
  int length;
  String groupId;

  public Cuboid(int height, int width, int length, String groupId) {
    this.height = height;
    this.width = width;
    this.length = length;
    this.groupId = groupId;
  }

  public int getHeight() {
    return height;
  }


  public int getWidth() {
    return width;
  }


  public int getLength() {
    return length;
  }


  public int getVolume() {
    return height * width * length;
  }

  public String getGroupId() {
    return groupId;
  }
}
