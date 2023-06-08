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

        # 괄호 쌍을 딕셔너리로 표현 ( 아이디어 )
        bracket_map = {
            ')': '(',
            '}': '{',
            ']': '['
        }

        for char in s:
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
