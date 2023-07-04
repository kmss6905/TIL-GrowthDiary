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