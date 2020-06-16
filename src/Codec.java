import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String res = dfsS(root, "");
        return res.substring(0, res.length()-1);
    }

    private String dfsS(TreeNode root, String res) {
        if (root == null) {
            res += "null,";
        } else {
            res += root.val + ",";
            res = dfsS(root.left, res);
            res = dfsS(root.right, res);
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split(",");
        return dfsD(new ArrayList<>(Arrays.asList(datas)));
    }

    private TreeNode dfsD(List<String> datas) {
        if (datas.get(0).equals("null")) {
            datas.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(datas.get(0)));
        datas.remove(0);
        root.left = dfsD(datas);
        root.right = dfsD(datas);

        return root;
    }

}
