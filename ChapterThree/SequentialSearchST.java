package ChapterThree;

/**
 * 3.1 顺序查找，（基于无序链表）
 * 2020/8/1 14：47
 *
 */
public class SequentialSearchST<Key,Value> {
    private Node first; //链表首节点

    private class Node{
        // 链表
        Key key;
        Value value;
        Node next;
        public Node(Key key,Value value,Node next){
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }

    /**
     * 传入key,查找给定的键，获得相应的 value，没有返回null
     * @param key 传入
     * @return value or null
     */
    public Value getKey(Key key){
        for (Node it=first;it!=null;it=it.next)
            if(key.equals(it.key)) return it.value;
        return null;
    }

    /**
     * 查找列表中的key,有则更换传入的Value,没有则新加节点，放入列表
     * @param key
     * @param value
     */
    public void putKey(Key key,Value value){
        // 查找
        for(Node it=first;it!=null;it=it.next)
            if(key.equals(it.key)) {
                it.value = value;
                return;
            }
        // 没找到 新增
        first = new Node(key, value, first);
    }



}

/*
// how you're supposed to implement equals
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Person that = (Person) other;
        return (this.name.equals(that.name)) && (this.info == that.info);
    }
 */