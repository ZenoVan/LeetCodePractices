class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] nums = new int[100];

        for (int i = 0; i<dominoes.length; i++) {
            if (dominoes[i][0] > dominoes[i][1]) {
                int temp = dominoes[i][0];
                dominoes[i][0] = dominoes[i][1];
                dominoes[i][1] = temp;
            }
            nums[dominoes[i][0]*10 + dominoes[i][1]]++;
        }

        int count = 0;
        for (int i = 0; i<nums.length; i++) {
            if (nums[i] != 0 && nums[i] != 1)
                count += (nums[i]*(nums[i]-1))/2;
        }

        return count;
    }
}
