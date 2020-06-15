class Solution {
    public String longestCommonPrefix(String[] strs) {
        String res = "";
        if (strs.length > 0)
            res = strs[0];
        for (String str : strs) {
            if (res.equals(""))
                break;
            for (int i = 0; i<res.length(); i++) {
                if (i == str.length()) {
                    res = str;
                    break;
                }
                if (res.charAt(i) != str.charAt(i)) {
                    res = res.substring(0, i);
                }
            }
        }
        return res;
    }
}
