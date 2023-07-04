# 기본 postorder 활용
# 하위로 부터 정보 취합
def LCA(root, p, q):
    if root is None:
        return None

    # 선 취합
    left = LCA(root.left, p, q)
    right = LCA(root.right, p, q)

    # 후 판단
    if root == p or root == q:
        return root
    elif left and right:
        return root
    return left or right

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        if root is None:
            return None

        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)

        if root == p or root == q:
            return root
        elif left and right:
            return root
        return left or right
