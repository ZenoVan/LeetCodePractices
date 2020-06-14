import java.util.Arrays;

class Solution {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int res = arr[arr.length-1];
        int sum = 0;
        for (int i = 0; i<arr.length; i++) {
            sum += arr[i];
        }

        if (sum <= target) {
            return res;
        }

        if (arr[0] * arr.length >= target) {
            res = arr[0];
            int dis = res * arr.length - target;
            while (res * arr.length >= target) {
                dis = res * arr.length - target;
                res--;
            }
            if (dis < target - res*arr.length) {
                res++;
            }
            return res;
        }

        int count = arr.length-1;
        for (int i = arr.length-1; i>=0; i--) {
            if(sum - target > 0) {
                int temp = arr[i];
                for (int j = arr.length-1; j>i; j--) {
                    sum -= arr[j] - temp;
                    arr[j] = temp;
                    res = temp;
                }
            } else if (sum - target == 0) {
                return res;
            } else {
                count = arr.length-2-i;
                break;
            }
        }

        int lastDis = sum - target;
        while (sum - target < 0) {
            lastDis = sum - target;
            sum += count;
            res++;
        }

        if (-lastDis <= sum - target) {
            res--;
        }

        return res;
    }
}
