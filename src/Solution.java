import java.util.*;

class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        List<Integer> team = new ArrayList<>();
        Map<String, Integer> reqMap = new HashMap<>();
        for (String req : req_skills) {
            reqMap.put(req, 0);
        }

        Map<List<String>, Integer> peoMap = new HashMap<>();
        for (int i = 0; i<people.size(); i++) {
            peoMap.put(people.get(i), i + people.get(i).size()*100);
        }

        people.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                int diff = o1.size() - o2.size();
                if (diff == 0)
                    return 0;
                return diff>0 ? -1 : 1;
            }
        });

        int need = req_skills.length;
        for (List<String> skills : people) {
            if (need == 0)
                break;
            boolean isNeed = false;
            for (String skill : skills) {
                if (reqMap.containsKey(skill) && reqMap.get(skill) == 0) {
                    isNeed = true;
                    break;
                }
            }
            if (isNeed) {
                team.add(peoMap.get(skills)%100);
                for (String skill : skills) {
                    if (reqMap.containsKey(skill) && reqMap.get(skill) == 0) {
                        reqMap.put(skill, 1);
                        need--;
                    }
                }
            }
        }

        int[] arr = new int[team.size()];
        for (int i = 0; i<team.size(); i++)
            arr[i] = team.get(i);

        return arr;
    }

    public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {
        List<Integer> res = new ArrayList<>(), team = new ArrayList<>();
        int m = req_skills.length, n = people.size();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i<m; i++) {
            map.put(req_skills[i], i);
        }
        int[] skills = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < people.get(i).size(); j++) {
                skills[i] |= 1 << map.get(people.get(i).get(j));
            }
        }

        dfs(0, m, skills, team, res);

        int[] arr = new int[res.size()];
        for (int i = 0; i<res.size(); i++)
            arr[i] = res.get(i);
        return arr;
    }

    private void dfs(int cur, int m, int[] skills, List<Integer> team, List<Integer> res) {
        if (cur == (1 << m) - 1) {
            if (res.size() == 0 || res.size() > team.size())
                res = team;
            return;
        }

        if (res.size() != 0 && res.size() <= team.size())
            return;

        int bitct = 0;
        while (((cur >> bitct) & 1) == 1)
            ++bitct;

        for (int i = 0; i<skills.length; ++i) {
            if (((skills[i] >> bitct) & 1) == 1) {
                team.add(i);
                dfs(cur | skills[i], m, skills, team, res);
                team.remove(team.size()-1);
            }
        }
    }
}
