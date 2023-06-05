# Linked List 는 Node 라는 구조체가 연결되는 형식으로 데이터를 저장하는 자료구조 Node는 데이터 값과 next node의 주소값을 저장한다.
# Linked List 는 메모리상에서는 비연속적으로 저장이 되어 있지만, 각각의 node 가 next node의  메모리 주소값을 가리킴으로써 논리적인 연속성을 갖게 된다.
class Node:
    def __init__(self, value=0, next = None):
        self.value = value
        self.next = next


first = Node(1)
second = Node(2)
third = Node(3)

first.next = second
second.next = third
