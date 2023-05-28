package todo;

public class test {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        int[] arr = {30, 20, 40, 35, 5, 10, 45, 50, 25, 15};
//        int[] arr = {0,9,4,7,3,1,5,8,6,2};
        System.out.print("arr 초기값 : ");
        printArr(arr);
        quickSort(arr);
        System.out.println(arr);
        printArr(arr);

    }

    private static void printArr(int[] arr) {
        for (int a : arr) {
            System.out.print(a + ", ");
        }
        System.out.println();
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int start,int end) {
        int part2_start = partition(arr, start, end);
        if (part2_start - start > 1) {
            quickSort(arr, start, part2_start - 1);
        }
        if (end - part2_start > 0) {
            quickSort(arr, part2_start, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start + end) / 2];
        System.out.println();
        System.out.println("start : " +start +  " , end : " + end);
        System.out.println("pivot idx: " + (start + end) / 2);
        System.out.println("pivot value: " + pivot);
        while(start <= end){
            while(arr[start] < pivot) start++;
            while(arr[end] > pivot) end--;
            if (start <= end) { //역전이 되지 않았을 때
                swap(arr, start, end);
                start++;
                end--;
            }
            System.out.println(start+", "+end);
        }
        printArr(arr);
        System.out.println( "start : " + start + "   end : " +  end );
        return start;
    }

    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }


}
