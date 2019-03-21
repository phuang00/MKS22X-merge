import java.util.*;

public class Merge{

  public static void mergesort(int[] data){
    int[] temp = new int[data.length];
    for (int i = 0; i < data.length; i++){
      temp[i] = data[i];
    }
    // copy data into a temp array
    mergesort(data, temp, 0, data.length -1);
    // call helper method
    //mergeH(data, 0, data.length - 1);
  }

  private static void mergeH(int[] data, int lo, int hi){
    if (lo >= hi){
      // if the lower limit is less than upper bound, exit out of method
      return;
    }
    int[] tempLo = new int[(hi - lo + 1)/2];
    int[] tempHi = new int[hi - lo + 1 - tempLo.length];
    // create two temporary arrays by splitting into the middle
    for (int i = 0; i <= hi - lo; i++){
      if (i < tempLo.length){
        tempLo[i] = data[i];
      }
      else{
        tempHi[i - tempLo.length] = data[i];
      }
    }
    // copy values from data to the new arrays

    //System.out.print(Arrays.toString(tempLo));
    //System.out.println(Arrays.toString(tempHi));
    mergeH(tempLo, lo, lo + tempLo.length - 1);
    mergeH(tempHi, lo + tempLo.length, hi);
    // call method itself on both temp arrays
    merge(data, tempLo, tempHi);
    //merge the temp arrays together into data
    //System.out.println(Arrays.toString(data));
  }

  private static void merge(int[] data, int[] tempLo, int[] tempHi){
    int l = 0;
    // l = current index of tempLo
    int h = 0;
    // h = current index of tempHi
    for (int i = 0; i < data.length; i++){
      // for every index in data
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
          // if l is out of bounds or h is in bounds and
          // the value at index l of tempLo is less than the value at index h of tempHi,
          //System.out.println("AHHHH");
          data[i] = tempHi[h];
          // add value at index h of tempH to data and increase index h by one
          h++;
          //System.out.println("dataa" + Arrays.toString(data));
        }
        else{
          // else
          //System.out.println("BOOOO");
          data[i] = tempLo[l];
          // add value at index l of tempLo to data and increase index l by one
          l++;
        }
    }
  }

  private static void mergesort(int[] data, int[] temp, int lo, int hi){
    /*if (lo >= hi){
      // if lower bound is greater than or equal to upper bound, exit out of method
      return;
    }*/
    if (hi - lo < 43){
      insertionsort(data, lo, hi);
      return;
    }
    int loLo = lo;
    // loLo = lower bound of the first half of data
    int loHi = (hi - lo)/2 + lo;
    // loHi = upper bound of the first half of data
    int hiLo = loHi + 1;
    // hiLo = lower bound of second half of data
    int hiHi = hi;
    // hiHi = upper bound of second half of data

    //System.out.println("lolo " + loLo);
    //System.out.println("lohi " + loHi);
    //System.out.println("hilo " + hiLo);
    //System.out.println("hihi " + hiHi);

    mergesort(temp, data, loLo, loHi);
    mergesort(temp, data, hiLo, hiHi);
    // call method itself on both halves of data to split temp (by swapping temp and data)
    merge(data,temp, loLo, loHi, hiLo, hiHi);
    // merge the two halves from temp into data

  }

  private static void merge(int[] data, int[] temp, int loLo, int loHi, int hiLo, int hiHi){
    int l = loLo;
    // l = current index of lower half of temp
    int h = hiLo;
    // h = current index of upper half of temp
    for (int i = loLo; i <= hiHi; i++){
      // for every index between the lower bound of the first half and the upper bound of second half (inclusive)
        if (l > loHi || (h <= hiHi && temp[l] > temp[h])){
          // if l is out of bounds (greater than the upper bound of the first half),
          // or h is in bounds and value at index l of temp is less than index h of temp
          //System.out.println("AHHHH");
          data[i] = temp[h];
          // add value at index h of temp into data and increase index h by one
          h++;
          //System.out.println("dataa" + Arrays.toString(data));
        }
        else{
          // else
          //System.out.println("BOOOO");
          data[i] = temp[l];
          // add value at index l into data and increase l by one
          l++;
        }
    }
  }

  private static void insertionsort(int[] data, int lo, int hi){
    for (int i = lo + 1; i <= hi; i++){
      int temp = data[i];
      boolean fixed = false;
      int index = i - 1;
      while (!fixed){
        if (index < lo || data[index] < temp){
          data[index + 1] = temp;
          fixed = true;
        }
        else{
          data[index + 1] = data[index];
          index--;
        }
      }
    }
  }

  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tmerge /builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Merge.mergesort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
