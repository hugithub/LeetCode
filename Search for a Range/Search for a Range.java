public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] range = { -1, -1 };

        int start = 0, end = A.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                if (mid == 0 || A[mid - 1] < target) {
                    range[0] = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            } else if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        end = A.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                if (mid == A.length - 1 || A[mid] < A[mid + 1]) {
                    range[1] = mid;
                    break;
                } else {
                    start = mid + 1;
                }
            } else if (A[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return range;
    }
}
