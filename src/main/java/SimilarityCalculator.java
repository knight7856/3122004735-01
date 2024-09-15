public class SimilarityCalculator {
    public double calculateSimilarity(String text1, String text2) {
        int editDistance = calculateEditDistance(text1, text2);
        int maxLength = Math.max(text1.length(), text2.length());

        // 相似度 = (1 - (编辑距离 / 最大长度)) * 100
        return (1 - (double) editDistance / maxLength) * 100;
    }

    private int calculateEditDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[len1][len2];
    }
}