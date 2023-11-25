package skiplist;

import java.util.LinkedList;
import java.util.Random;

/**
 * @Author: darrylsun
 * @Description: skip list 实现
 * @Date: 2023/9/21
 */
public class SkipList<K extends Comparable<K>, V> {

    // 当前最大的层数
    private int maxLevel = 12;
    // 头结点，down 使用down指向下一层，及下一个 level 的 index node
    private SkipListIndexNode<K, V> head;
    // 当前的level
    private int level = 1;
    private static Random random;
    //跳到下一层的概率
    private double probability = 0.5;

    /**
     * 构造函数
     * @param maxLevel
     * @param probability
     */
    public SkipList(int maxLevel, double probability) {
        random = new Random();
        this.maxLevel = maxLevel;
        this.probability = probability;
        SkipListDataNode data = new SkipListDataNode(Integer.MIN_VALUE, null);
        head = new SkipListIndexNode(data, 1);
    }


    /**
     * 获取 skip list 中 key 对应的 value 值
     * @param key
     * @return
     */
    public V get(K key) {
        SkipListDataNode<K, V> old = searchNode(key);
        if (old != null) {
            return old.value;
        } else {
            return null;
        }
    }


    /**
     * skip list 中新增一个 node 数据
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        SkipListDataNode old = searchNode(key);
        if (old != null) {
            old.value = value;
            return;
        }
        int newLevel = randomLevel();
        if(newLevel > maxLevel){
            newLevel = maxLevel;
        }
        if (newLevel > level ) {
            for (int i = level; i < newLevel; i++) {
                genNewHead();
            }
        }
        SkipListDataNode<K, V> data = new SkipListDataNode<K, V>(key, value);
        SkipListIndexNode<K, V> indexNode = new SkipListIndexNode<K, V>(data, newLevel);
        LinkedList<SkipListIndexNode> update = new LinkedList<SkipListIndexNode>();
        SkipListIndexNode<K, V> cur = head;
        while (cur != null && cur.level > newLevel) {
            cur = cur.down;
        }
        while (cur != null) {
            while (cur.right != null && cur.right.getKey().compareTo(key) < 0) {
                cur = cur.right;
            }
            update.add(cur);
            cur = cur.down;
        }
        SkipListIndexNode<K, V> bottom = null;
        while (!update.isEmpty()) {
            SkipListIndexNode prevNode = update.pollLast();
            SkipListIndexNode curLevelIndex = indexNode.genIndexNodeByLevel(prevNode.level);
            curLevelIndex.right = prevNode.right;
            prevNode.right = curLevelIndex;
            curLevelIndex.down = bottom;
            bottom = curLevelIndex;
        }
    }

    /**
     * 根据 key 获取 skip list 中 data node
     * @param key
     * @return
     */
    private SkipListDataNode searchNode(K key) {
        if (key == null) {
            return null;
        }
        SkipListIndexNode<K, V> cur = head;
        while (cur != null) {
            while (cur.right != null && cur.right.getKey().compareTo(key) < 0) {
                cur = cur.right;
            }
            if (cur.right != null && cur.right.getKey().compareTo(key) == 0) {
                return cur.right.dataNode;
            }
            cur = cur.down;
        }
        return null;
    }

    /**
     * 随机生成 level 信息
     * @return
     */
    private int randomLevel() {
        int level = 1;
        while (random.nextDouble() < probability && level < maxLevel) {
            level++;
        }
        return level;
    }

    /**
     * 生成新的 index node header for skip list
     */
    private void genNewHead() {
        SkipListIndexNode<K, V> skipListIndexNode = new SkipListIndexNode<K, V>(null, ++level);
        skipListIndexNode.down = head;
        head = skipListIndexNode;
    }

    /**
     * 打印输出 skip list
     */
    public void printList() {
        SkipListIndexNode bottom = head;
        LinkedList<SkipListIndexNode> stack = new LinkedList<SkipListIndexNode>();
        while (bottom.down != null) {
            bottom = bottom.down;
        }
        SkipListIndexNode printLevel = head;
        while (printLevel != null) {
            SkipListIndexNode printLeveltail = printLevel;
            System.out.printf("%-5s", "head->");
            SkipListIndexNode bottomTail = bottom;
            while (printLeveltail != null && bottomTail != null) {
                if (printLeveltail.right != null && printLeveltail.right.getKey().compareTo(bottomTail.right.getKey()) == 0) {
                    System.out.printf("%-5s", printLeveltail.right.getKey() + "->");
                    printLeveltail = printLeveltail.right;
                } else {
                    System.out.printf("%-5s", "");
                }
                bottomTail = bottomTail.right;
            }
            printLevel = printLevel.down;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipList<Integer, Integer> skipList = new SkipList<Integer, Integer>(12, 0.5);
        for (int i = 1; i <= 10; i++) {
            skipList.put(new Random().nextInt(10), 10 + i);
        }
        skipList.printList();
        System.out.println(skipList.get(3));
    }

    /**
     * skip list 含 level index node
     * @param <K>
     * @param <V>
     */
    class SkipListIndexNode<K extends Comparable<K>, V> {
        SkipListDataNode<K, V> dataNode;
        int level = 0;
        SkipListIndexNode<K, V> right;
        SkipListIndexNode<K, V> down;
        public SkipListIndexNode(SkipListDataNode<K, V> dataNode, int level) {
            this.dataNode = dataNode;
            this.level = level;
        }
        public SkipListIndexNode genIndexNodeByLevel(int level) {
            return new SkipListIndexNode(this.dataNode, level);
        }
        public V getValue() {
            return dataNode.value;
        }
        public K getKey() {
            return dataNode.key;
        }
    }

    /**
     * skip list data node
     * @param <K>
     * @param <V>
     */
    class SkipListDataNode<K extends Comparable<K>, V> {

        // 用于排序的，可以联想 redis zset 的 zadd set k v 中的 k，即为 score
        private K key;

        // 列表中的值
        private V value;

        SkipListDataNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
