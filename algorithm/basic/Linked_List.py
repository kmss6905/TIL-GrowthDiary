# Linked List 는 Node 라는 구조체가 연결되는 형식으로 데이터를 저장하는 자료구조 Node는 데이터 값과 next node의 주소값을 저장한다.
# Linked List 는 메모리상에서는 비연속적으로 저장이 되어 있지만, 각각의 node 가 next node의  메모리 주소값을 가리킴으로써 논리적인 연속성을 갖게 된다.
class LinkedList(object):
    def __init__(self):
        self.head = None

    # APPEND
    def append(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while(current.next):
                current = current.next
            # 리스트 마지막 노드에 추가할 노드의 주소를 저장
            current.next = new_node

    # GET O(N)
    def get(self, idx):
        current = self.head
        for _ in range(idx):
            current = current.next
        return current.value

    # INSERT O(N)
    def insert(self, idx, value):
        # 새로운 노드 생성
        new_node = Node(value)
        current = self.head
        before = None
        # idx 째 있는 노드 찾기
        for _ in range(idx+1):
            # idx 앞에 있는 노드 저장
            if _ == idx-1:
                before = current
            current = current.next

        # print(current.value)
        # 방금 만든 새로운 노드에 추가할 노드의 주소 저장
        new_node.next = current

        # 이전 노드에 새로만든 노드의 주소 저장
        before.next = new_node

    # O(N)
    def remove(self, idx):
        current = self.head
        before = None
        for _ in range(idx):
            before = current
            current = current.next  # 최종적으로 삭제할 노드를 가리킴

        before.next = current.next

    # O(1)
    def insert_back(self):
        # tail 이용 ( append 시점에 tail 을 저장 )
        pass






class Node:
    def __init__(self, value=0, next=None):
        self.value = value
        self.next = next

ll = LinkedList()
ll.append(1)
ll.append(2)
ll.append(3)
ll.append(4)

print(ll.get(0))
print(ll.get(1))
print(ll.get(2))
print(ll.get(3))
print('*' * 100)
ll.remove(2)
print(ll.get(0))
print(ll.get(1))
print(ll.get(2))
