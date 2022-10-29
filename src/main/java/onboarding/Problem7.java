package onboarding;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem7 {

    public static List<String> solution(String user, List<List<String>> friends,
        List<String> visitors) {
        // 1. 친구 그래프 작성
        Map<String, Set<String>> friendGraph = getFriendGraph(friends);

        // 2. 사용자 친구 목록 작성
        Set<String> userFriends = getUserFriends(user, friendGraph);

        // 친구와 점수의 집합 생성
        Map<String, Integer> friendScore = new HashMap<>();

        // 3. 사용자 친구 목록을 통한 사용자와 함께 아는 친구의 수 계산
        addfFirstRulePoint(user, friendGraph, userFriends, friendScore);

        // 4. 사용자 친구 목록을 통해 친구를 제외하고 사용자의 타임 라인에 방문한 횟수 계산
        // 5. 추천 점수 내림차순으로 정렬하고, 같다면 이름순으로 정렬

        List<String> answer = Collections.emptyList();
        return answer;
    }

    // 1. 친구 그래프 작성
    private static Map<String, Set<String>> getFriendGraph(List<List<String>> friends) {
        Map<String, Set<String>> graph = new HashMap<>();

        for (List<String> friend : friends) {
            String A = friend.get(0);
            String B = friend.get(1);

            if (!graph.containsKey(A)) {
                graph.put(A, new HashSet<>());
            }
            if (!graph.containsKey(B)) {
                graph.put(B, new HashSet<>());
            }

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        return graph;
    }

    // 2. 사용자 친구 목록 작성
    private static Set<String> getUserFriends(String user, Map<String, Set<String>> friendGraph) {
        return friendGraph.get(user);
    }

    // 3. 사용자 친구 목록을 통한 사용자와 함께 아는 친구의 수 계산
    private static void addfFirstRulePoint(String user, Map<String, Set<String>> friendGraph,
        Set<String> userFriends, Map<String, Integer> friendScore) {
        for (String friend : friendGraph.keySet()) {
            if (friend.equals(user)) {
                continue;
            }

            // friend와 user의 friend를 비교
            for (String uf : friendGraph.get(friend)) {
                if (userFriends.contains(uf)) {
                    friendScore.put(friend, friendScore.getOrDefault(friend, 0) + 10);
                }
            }
        }
    }
}
