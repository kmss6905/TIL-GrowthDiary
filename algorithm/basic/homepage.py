class HomePage:
    def __init__(self, url):
        self.url = url
        self.next = None


class BrowserHistory(object):

    def __init__(self, homepage):
        """
        :type homepage: str
        """
        self.head = self.current = HomePage(homepage)
        self.idx = 0

    def visit(self, url):
        """
        :type url: str
        :rtype: None
        """
        homepage = HomePage(url)
        # 순서가 중요함
        self.current.next = homepage
        self.current = homepage
        self.idx += 1
        print('current page is', self.current.url)

    def back(self, steps):
        """
        :type steps: int
        :rtype: str
        """
        tmp = self.head
        if self.idx < steps:
            self.current = self.head
            self.idx = 0
            print('current page is', self.current.url)
            return self.current.url
        else:
            self.idx = self.idx - steps
            print(self.idx)
            for _ in range(self.idx):
                tmp = tmp.next
            self.current = tmp
            print('current page is', self.current.url)
            return self.current.url

    def get(self, idx):
        current = self.head
        for _ in range(idx):
            current = current.next

        print(current.url)

    def forward(self, steps):
        """
        :type steps: int
        :rtype: str
        """
        if self.current.next is None:
            pass
        else:
            self.current = self.current.next
            print(self.current.url)
            self.idx += 1
        return self.current.url

# Your BrowserHistory object will be instantiated and called as such:
# obj = BrowserHistory(homepage)
# obj.visit(url)
# param_2 = obj.back(steps)
# param_3 = obj.forward(steps)
# ["BrowserHistory","visit","forward","back","visit","forward","forward","visit","visit","visit","forward","visit","visit","visit","visit","visit","back"]
# [["qphpma.com"],["izdd.com"],[2],[8],["ke.com"],[4],[7],["ilfsmq.com"],["pwrrbnw.com"],["iuitdjd.com"],[6],["lvurjxv.com"],["bjqljz.com"],["gvcv.com"],["fefsizg.com"],["yaa.com"],[1]]
bh = BrowserHistory("qphpma.com")
bh.visit('izdd.com')
bh.forward(2)
bh.back(8)
bh.visit("ke.com")
bh.forward(4)
bh.forward(7)
bh.visit("ilfsmq.com")
bh.visit("pwrrbnw.com")
bh.visit("iuitdjd.com")
bh.forward(6)
# ["lvurjxv.com"],["bjqljz.com"],["gvcv.com"],["fefsizg.com"],["yaa.com"]
bh.visit("lvurjxv.com")
bh.visit("bjqljz.com")
bh.visit("gvcv.com")
bh.visit("fefsizg.com")
bh.visit("yaa.com")
bh.back(1)

