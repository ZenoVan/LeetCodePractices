class Solution {
    /**
     * f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int fn1 = 1, fn2 = 1;
        int fn = 1;

        for (int i=1; i<n; i++) {
            fn = fn1 + fn2;
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }
}
