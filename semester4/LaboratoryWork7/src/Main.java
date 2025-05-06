import java.util.Arrays;

public class Main {

    public record Result(int maxSum, int start, int end){}

    public static Result findMaxSum(int[] arr) {
        int maxSum = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            }else maxEndingHere += arr[i];

            if (maxEndingHere > maxSum) {
                maxSum = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }
        return new Result(maxSum, start, end);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, -3, 4, -5, 6, 7, 8, -9, 10};
        Result r = findMaxSum(arr);
        System.out.println(Arrays.toString(arr));
        System.out.printf("Max sum: %s; start: %s; end: %s%n", r.maxSum(), r.start(), r.end());
    }
}