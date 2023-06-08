# Leet Code
# 2
class Solution(object):
    def isValid(self, s):
        l = []
        for a in s:
            if len(l) == 0 and (a == ')' or a == '}' or a == ']'):
                return False

            if a == '(' or a == '{' or a == '[':
                l.append(a)
            elif a == ')':
                if l.pop() == '(':
                    pass
                else:
                    return False
            elif a == ']':
                if l.pop() == '[':
                    pass
                else:
                    return False
            elif a == '}':
                if l.pop() == '{':
                    pass
                else:
                    return False

        if len(l) != 0:
            return False

# 2
class Solution_Two(object):
    def isValid(self, s):
        stack = []

        # 괄호 쌍을 딕셔너리로 표현 ( idea 1 )
        bracket_map = {
            ')': '(',
            '}': '{',
            ']': '['
        }

        # O(N)
        for char in s:
            # 문자열 포함 여부 확인 ( idea 2 ), 검사하려는 문자 char 가 '({['에 존재하는 지 확인하기 위해 문자열의
            # 각 문자를 순회하며 동등성을 비교함 따라서 시간복잡도 O(N)
            # 하지만 사실상 여기서는 O(1) 로 보고 풀어도 무관
            if char in '({[':
                stack.append(char)
            elif char in ')}]':
                if not stack or stack.pop() != bracket_map[char]:
                    return False

        return not stack


# 3
def isValid(s):
    stack = []
    for p in s:
        if p == "(":
            stack.append(")")
        elif p == "{":
            stack.append("}")
        elif p == "[":
            stack.append("]")
        elif not stack or stack() != p:
            return False
    return len(stack) != 0 # not stack
