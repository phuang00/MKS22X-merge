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
    merge(data, tempLo, tempHi);
    System.out.println(Arrays.toString(data));
  }

  private static void merge(int[] data, int[] tempLo, int[] tempHi){
    int l = 0;
    int h = 0;
    for (int i = 0; i < data.length; i++){
      if (!(l >= tempLo.length && h >= tempHi.length)){
        /*System.out.println("lo" + Arrays.toString(tempLo));
        System.out.println("hi" + Arrays.toString(tempHi));
        System.out.println("l" + l);
        System.out.println("h" + h);
        System.out.println("i" + i);
        System.out.println(data.length);
        System.out.println("dat" + Arrays.toString(data));
        System.out.println(l >= tempLo.length || (h < tempHi.length && tempLo[l] > tempHi[h]));
        */
        if (l >= tempLo.length || (h < tempHi.length && tempLo[l] > tempHi[h])){
          //System.out.println("AHHHH");
          data[i] = tempHi[h];
          h++;
          //System.out.println("dataa" + Arrays.toString(data));
        }
        else{
          //System.out.println("BOOOO");
          data[i] = tempLo[l];
          l++;
        }
      }
      else{
        i--;
      }
    }
  }

  public static void main(String[] args) {
    int[] ary = new int[] {3,5,2,6,31,6,23,2,54,64,25,4};
    int[] arys = new int[] {3,5,2,6,31,6,23,2,54,64,25,4};
    System.out.println(Arrays.toString(ary));
    mergesort(ary);
    System.out.println(Arrays.toString(ary));
    Arrays.sort(arys);
    System.out.println(Arrays.toString(arys));
  }

}
