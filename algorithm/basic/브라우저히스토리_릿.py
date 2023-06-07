class ListNode(object):
    def __init__(self, val=0, next=None, prev=None):
        self.next = next
        self.prev = prev
        self.val = val


class BrowserHistory(object):
    def __init__(self, homepage):
        self.head = self.current = ListNode(homepage)

    def visit(self, url):
        self.current.next = ListNode(url, prev=self.current)
        self.current = self.current.next
        return None

    def back(self, steps):
        while steps > 0 and self.current.prev is not None:
            steps -= 1
            self.current = self.current.prev
        return self.current.val

    def forward(self, steps):
        while steps > 0 and self.current.next is not None:
            steps -= 1
            self.current = self.current.next
        return self.current.val