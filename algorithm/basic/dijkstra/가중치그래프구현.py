# 기본 다익스트라 구현
def dijkstra(graph, start, final, n):
    costs = {}
    pq = []
    heapq.heappush(pq, (0,start))
    while pq:
        cur_cost, cur_v = heapq.heappop(pq)
        if cur_v not in costs:
            costs[cur_v] = cur_cost
            for cost, next_v in graph[cur_v]:
                next_cost = cur_cost + cost
                heapq.heappush(pq, (next_cost, next_v)) # 알아서 정렬 해줌, 우선순위가 높은 것이 맨 앞으로
    return costs[final]
graph = { ... }
dijkstra(graph, 1, 8)