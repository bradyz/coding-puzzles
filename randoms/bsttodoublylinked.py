class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __str__(self):
        return str(self.val)


def inorder(r):
    s = [r]

    while s:
        cur = s.pop()
        if cur:
            s.append(cur.right)
            s.append(cur)
            s.append(cur.left)
        else:
            if s:
                yield s.pop()


def linkedlisttraversal(head):
    res = []
    while head:
        res.append(head)
        head = head.right
    print(" ".join(map(str, res)))


def bsttodoublylinked(r):
    prev = None
    start = None

    for cur in inorder(r):
        if not prev:
            start = cur
            cur.left = prev
        else:
            cur.left = prev
            prev.right = cur
        prev = cur

    return start

if __name__ == "__main__":
    bst = Node(4, Node(2, Node(1), Node(3)), Node(6, Node(5), Node(7)))
    print(" ".join(map(str, inorder(bst))))
    a = bsttodoublylinked(bst)
    linkedlisttraversal(a)
