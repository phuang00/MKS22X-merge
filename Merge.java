import java.util.*;

public class Merge{

  public static void mergesort(int[] data){
    int[] temp = new int[data.length];
    for (int i = 0; i < data.length; i++){
      temp[i] = data[i];
    }
    mergesort(data, temp, 0, data.length -1);
    //mergeH(data, 0, data.length - 1);
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
    //System.out.print(Arrays.toString(tempLo));
    //System.out.println(Arrays.toString(tempHi));
    mergeH(tempLo, lo, lo + tempLo.length - 1);
    mergeH(tempHi, lo + tempLo.length, hi);
    merge(data, tempLo, tempHi);
    //System.out.println(Arrays.toString(data));
  }

  private static void merge(int[] data, int[] tempLo, int[] tempHi){
    int l = 0;
    int h = 0;
    for (int i = 0; i < data.length; i++){
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
        else if (h >= tempLo.length || (l < tempHi.length && tempLo[l] <= tempHi[h])){
          //System.out.println("BOOOO");
          data[i] = tempLo[l];
          l++;
        }
        else{
          i--;
        }
    }
  }

  private static void mergesort(int[] data, int[] temp, int lo, int hi){
    if (lo >= hi){
      return;
    }
    int loLo = lo;
    int loHi = (hi - lo)/2 + lo;
    int hiLo = loHi + 1;
    int hiHi = hi;

    //System.out.println("lolo " + loLo);
    //System.out.println("lohi " + loHi);
    //System.out.println("hilo " + hiLo);
    //System.out.println("hihi " + hiHi);


    mergesort(temp, data, loLo, loHi);
    mergesort(temp, data, hiLo, hiHi);
    merge(data,temp, loLo, loHi, hiLo, hiHi);

  }

  private static void merge(int[] data, int[] temp, int loLo, int loHi, int hiLo, int hiHi){
    int l = loLo;
    int h = hiLo;
    for (int i = loLo; i < data.length; i++){
        if (l > loHi || (h <= hiHi && temp[l] > temp[h])){
          //System.out.println("AHHHH");
          data[i] = temp[h];
          h++;
          //System.out.println("dataa" + Arrays.toString(data));
        }
        else if (h > loHi || (l <= hiHi && temp[l] <= temp[h])){
          //System.out.println("BOOOO");
          data[i] = temp[l];
          l++;
        }
        else{
          i--;
        }
    }
  }

  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
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
