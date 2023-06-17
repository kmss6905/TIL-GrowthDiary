class Node(object):
    def __init__(self, value, prev = None, next = None):
        self.value = value
        self.prev = None
        self.next = None


class DobledLinkedList(object):
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0


    # def insert(self, idx, value):
    #     new_node = Node(value)
    #     if idx > self.size-1:
    #         return "Error Out of Index"
    #     # tail or head 이동





    # 추가
    def append(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = self.tail = new_node
        else:
            self.tail.next = new_node
            new_node.prev = self.tail
            self.tail = new_node
        self.size += 1

    # 삭제(at)
    def removeAt(self, idx):
        if idx < 0:
            return

        if idx == 0:
            if self.head is None:
                return
            else:
                if self.head.next is None:
                    self.head = self.tail = None
                else:
                    self.head = self.head.next
                    self.head.prev = None
            return

        current = self.head
        cnt = 0

        while current:
            if cnt == idx:
                if current.next is None:
                    self.tail = current.prev
                    self.tail.next = None
                else:
                    # 핵심
                    current.prev.next = current.next
                    current.next.prev = current.prev
                break
            else:
                cnt += 1
                # 한칸씩 이동
                current = current.next



    # 출력
    def __str__(self):
        if self.head is None:
            return print("리스트가 비어있습니다.")
        result = []
        current = self.head
        while current:
            result.append(str(current.value))
            current = current.next
        return '->'.join(result)


if __name__ == '__main__':
    ddl = DobledLinkedList()
    ddl.append(1)
    ddl.append(2)
    ddl.append(3)
    print(ddl)
    ddl.removeAt(idx=1)
    print(ddl)