import java.util.Scanner;

public class Main {
    private static int ans = Integer.MAX_VALUE;
    private static int[] p = new int[32], w = new int[32];
    private static int[][] v = new int[32][32], f = new int[32][32], val = new int[32][32];
    private static int n, m, r, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt(); r = sc.nextInt(); c = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                val[i][j] = sc.nextInt();
            }
        }
        dfs(1, 1);
        System.out.print(ans);
    }

    private static void dp() {
        for(int i = 1;i <= m;i++)
            for(int j = 1;j <= m;j++)
                f[i][j] = Integer.MAX_VALUE;
        for(int i = 1;i <= m;i++){
            w[i] = 0;
            for(int j = 2;j <= r;j++)
                w[i] += Math.abs(val[p[j]][i] - val[p[j - 1]][i]);
        }
        for(int i = 1;i <= m;i++)
            for(int j = 1;j <= m;j++)
                v[i][j] = 0;
        for(int i = 1;i <= m;i++)
            for(int j = 1;j <= m;j++)
                for(int k = 1;k <= r;k++)
                    v[i][j] += Math.abs(val[p[k]][i] - val[p[k]][j]);
        for(int i = 1;i <= m;i++)
            for(int j = 1;j <= Math.min(i,c);j++){
                for(int k = j - 1;k < i;k++)
                    f[i][j] = Math.min(f[i][j],f[k][j - 1] + w[i] + v[k][i]);
                if(j == c)ans = Math.min(ans,f[i][j]);
            }
    }

    private static void dfs(int line, int step) {
        if (step == r + 1) {
            dp();
            return;
        }
        if (line > n)
            return;
        p[step] = line;
        dfs(line + 1, step + 1);
        dfs(line + 1, step);
    }
}
