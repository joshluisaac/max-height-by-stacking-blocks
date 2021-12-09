package au.com.anz.codingchallenge.blockstacks;

import au.com.anz.codingchallenge.blockstacks.core.CuboidStackManager;
import au.com.anz.codingchallenge.blockstacks.core.LongestIncreasingSubsequence;
import au.com.anz.codingchallenge.blockstacks.requesthandler.FileInputHandler;
import java.io.File;

public class MaxHeightStackingApplication {

  public static void main(String[] args) {
    if (args.length < 1)
      throw new IllegalArgumentException("Please specify the file path in command line.");

    String filePath = args[0];
    if (filePath == null || "".equals(filePath))
      throw new IllegalArgumentException("Please check and specify file path.");

    int max = invoke(filePath);
    System.out.println(max);
  }

  public static int invoke(String filePath){
    LongestIncreasingSubsequence lis = new CuboidStackManager();
    lis.setHandler(new FileInputHandler(new File(filePath)));
    return lis.getMaxHeight();
  }



}


