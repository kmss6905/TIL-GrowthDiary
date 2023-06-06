class Node:
    def __init__(self, value):
        self.value = value
        self.next = None


class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    # O(1)
    def append(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = self.tail = new_node
        else:
            self.tail.next = new_node
            self.tail = new_node

    # O(N)
    def get(self, idx):
        current = self.head
        for _ in range(idx):
            current = current.next
        return current.value

    # 문제는 첫번 째 노드 삭제시 에러 발생 -> 예외처리 필요
    def remove(self, idx):
        current = self.head
        before = None
        if idx == 0:
            self.head = current.next
        else:
            for _ in range(idx):
                before = current
                current = current.next
            before.next = current.next

    # O(1)
    def replace(self, idx, value):
        new_node = Node(value)
        current = self.head
        before = None
        if idx == 1:
            before = self.head
        for _ in range(idx):
            if _ == idx-2:
                before = current
            current = current.next
        new_node.next = current.next
        before.next = new_node

    # O(1) insert
    def insert(self, idx, value):
        new_node = Node(value)
        current = self.head
        before = None
        if idx == 1:
            before = self.head
        for _ in range(idx):
            current = current.next
            if idx != 1:
                before = current
        new_node.next = current
        before.next = new_node

    # O(N)
    def print_all_element(self):
        enum = []
        current = self.head
        enum.append(current.value)
        while current.next:
            current = current.next
            enum.append(current.value)
        print(enum)

ll = LinkedList()
ll.append(1)
ll.append(2)
ll.append(3)
ll.append(4)
ll.append(5)
ll.print_all_element()

ll.insert(1, 10)

ll.print_all_element()

ll.replace(1, 7)

ll.print_all_element()

ll.remove(1)

ll.print_all_element()

ll.remove(0)
ll.print_all_element()