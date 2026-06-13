public class SegmentTree {

    static int[] tree = new int[100];
    static int[] arr = {1, 3, 5, 7, 9, 11};

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    static int query(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node, start, mid, l, r)
                + query(2 * node + 1, mid + 1, end, l, r);
    }

    static void update(int node, int start, int end,
                       int idx, int value) {

        if (start == end) {
            arr[idx] = value;
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid)
            update(2 * node, start, mid, idx, value);
        else
            update(2 * node + 1, mid + 1, end, idx, value);

        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    public static void main(String[] args) {

        build(1, 0, arr.length - 1);

        System.out.println("Sum(1,3) = "
                + query(1, 0, arr.length - 1, 1, 3));

        update(1, 0, arr.length - 1, 2, 10);

        System.out.println("After Update A[2]=10");

        System.out.println("New Sum(1,3) = "
                + query(1, 0, arr.length - 1, 1, 3));
    }
}