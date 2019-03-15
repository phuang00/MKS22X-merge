import java.util.*;

public class Merge{

  public static void mergesort(int[] data){
    mergeH(data, 0, data.length - 1);
  }

  private static void mergeH(int[] data, int lo, int hi){
    if (lo >= hi){
      return;
    }
    int[] tempLo = new int[(hi - lo + 1)/2];
    int[] tempHi = new int[hi - lo + 1 - tempLo.length];
    for (int i = 0; i <= hi - lo; i++){
      if (i < tempLo.length){
        tempLo[i] = data[i];
      }
      else{
        tempHi[i - tempLo.length] = data[i];
      }
    }
    System.out.print(Arrays.toString(tempLo));
    System.out.println(Arrays.toString(tempHi));
    mergeH(tempLo, lo, lo + tempLo.length - 1);
    mergeH(tempHi, lo + tempLo.length, hi);
  }

  public static void main(String[] args) {
    int[] ary = new int[] {3,5,2,6,31,6,23,2,54,64,25,4};
    System.out.println(Arrays.toString(ary));
    mergesort(ary);
  }

}
