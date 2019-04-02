package cn.tjucic.st;

public class Lab1 {
	int coins[] = {50, 20, 5, 1};
	int count[] = {1, 1, 2, 3};
	int sum = 50+20+5*2+3*1;
	public boolean Can(int amount) {
		if (amount > sum || amount < 0) return false;
		//dp[i][j] = n  用i种面值的纸币的凑j的方法有n种
		int[][] dp = new int[coins.length+1][amount+1];
		//初始化
		for (int i = 0; i < coins.length; i++) dp[i][0] = 1;
		for (int i = 1; i <= coins.length; i++) {//每种硬币
			for (int j = 1; j <= amount; j++) {//每种金额
				for(int k = 0; k <= count[i-1]; k++) {//因为逻辑上把币值为0也算作了一种货币，所以此处为i-1
					//d[i,j] = d[i-1,j] + d[i-1,j-1*v[i]] + d[i-1,j-2*v[i]] + ... + d[i-1,j-k[i]*v[i]]
					if (k*coins[i-1] > j) break;
					dp[i][j] += dp[i-1][j-k*coins[i-1]];
				}
			}
		}
		if (dp[coins.length][amount] > 0) {
			return true;
		} else {
			return false;
		}
	}
}
