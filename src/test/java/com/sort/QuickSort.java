package com.sort;

/**
 * 快速排序（三数取中实现）
 */
public class QuickSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArr(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    private static int partition(int[] arr, int left, int right) {
        // 采用三数中值分割法，就是比较最左边，最右边，以及中间位置的值的大小，然后把最小值放到中间位置
        int mid = left + (right - left) / 2;
        // 保证左端较小
        if (arr[left] > arr[right])
            swap(arr, left, right);
        // 保证中间较小
        if (arr[mid] > arr[right])
            swap(arr, mid, right);
        // 保证中间最小，左右最大
        if (arr[mid] > arr[left])
            swap(arr, left, mid);
        int pivot = arr[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较。从右至左找到第一个比基准数小的数
            while (pivot <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了，从左到右找到第一个比基准数大的数
            while (pivot >= arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = pivot;
        return left;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left >= right || arr.length <= 1)
            return;
        int mid = partition(arr, left, right);
        //对分界线左边的数据进行排序
        quickSort(arr, left, mid);
        //对分界线右边的数据进行排序
        quickSort(arr, mid + 1, right);
    }


    /**
     * 1．i = L; j = R; 将基准数挖出形成第一个坑 a[i]。
     * 2．j-- 由后向前找比它小的数，找到后挖出此数填前一个坑 a[i] 中。
     * 3．i++ 由前向后找比它大的数，找到后也挖出此数填到前一个坑 a[j] 中。
     * 4．再重复执行 2，3 二步，直到 i==j，将基准数填入 a[i] 中。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 2, 7, 9, 1, 8, 5, 10};
        quickSort(arr, 0, arr.length - 1);
        printArr(arr);
    }
}