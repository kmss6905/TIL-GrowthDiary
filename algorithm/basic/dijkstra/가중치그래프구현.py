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
                heapq.heappush(pq, (next_cost, next_v))
    return costs[final]
graph = { ... }
dijkstra(graph, 1, 8)