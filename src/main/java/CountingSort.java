/**
 * Project name(项目名称)：算法_计数排序
 * Package(包名): PACKAGE_NAME
 * Class(类名): CountingSort
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/11/20
 * Time(创建时间)： 14:46
 * Version(版本): 1.0
 * Description(描述)： 通过统计序列中各个元素出现的次数，完成对整个序列的升序或降序排序，这样的排序算法称为计数排序算法。
 * 计数排序算法的实现思路
 * 假设待排序序列为 {4, 2, 2, 8, 3, 3, 1}，使用计数排序算法完成升序排序的过程为：
 * 1) 找到序列中的最大值（用 max 表示）。对于 {4, 2, 2, 8, 3, 3, 1} 序列来说，最大值是 8。
 * 2) 创建一个长度为 max+1、元素初值全部为 0 的数组（Python 中可以使用列表），为数组中 [1,max] 区域内的各个空间建立索引：
 * 找到序列中的最小值（用 min 表示），作为数组下标为 1 的存储空间的索引；
 * 将 max 作为数组下标为 max 的存储空间的索引；
 * 将 max-1 作为数组下标为 max-1 的存储空间的索引；
 * 将 max-2 作为数组下标为 max-2 的存储空间的索引；
 * ......
 * 为某个存储空间建立索引，其实就是为这个存储空间贴上一个独一无二的标签，借助索引（标签），我们可以快速地找到此空间并访问内部的数据。
 * 对于长度为 max+1 的数组，计数排序算法的实现过程不会用到下标为 0 的数组空间。
 * 3) 统计待排序序列中各个元素的出现次数，存储到以该元素为索引的数组空间中。例如，待排序序列中元素 2 出现了两次，所以索引（下标）为 2 的数组空间中存储 2 。
 * 4) 进一步加工数组中存储的数据。从数组下标为 1 的位置开始，按照如下公式修改数组中存储的元素：
 * array[i] = array[i-1] + array[i]
 * 其中 i 的取值范围是 [1, max]
 * 5) 遍历待排序列中的元素，以该元素为索引获取数组中存储的值，此值即为序列排序后元素应处的位置。
 * 举个例子，序列中第一个元素是 4，数组中索引 4 对应的值为 6，因此序列排序后元素 4 位于第 6 的位置处
 * 6) 当确定了一个元素排序后的位置，需要将数组中该元素为索引对应的值减去 1。
 * 例如，我们已经确定了第二个元素 2 在有序序列中位于第 2 个位置，此时将数组下标为 2 处的值减去 1（3-1=2），则数组中第 3 个元素 2 位于有序序列中第 1 的位置上。
 * 以上 6 步就是计数排序算法的整个实现思路，对应的时间复杂度为O(n)。
 */

public class CountingSort
{
    public static void sort(int[] arr)
    {
        int length = arr.length;
        //第 1 步，找到序列中的最大值
        int max = getMax(arr);
        System.out.println("最大值：" + max);
        //第 2 步，初始化一个 array[max+1]
        int[] array = new int[max + 1];
        int[] output = new int[length];
        //第 3 步，统计各个元素的出现次数，并存储在相应的位置上
        for (int i = 0; i < length; i++)
        {
            array[arr[i]] = array[arr[i]] + 1;
        }
        System.out.println("各个元素的出现次数：");
        for (int s : array)
        {
            System.out.print(s + " ");
        }
        System.out.println();
        // 第 4 步，累加 array 数组中的出现次数
        for (int i = 1; i <= max; i++)
        {
            array[i] = array[i] + array[i - 1];
        }
        System.out.println("累加：");
        for (int s : array)
        {
            System.out.print(s + " ");
        }
        System.out.println();
        // 第 5 步，根据 array 数组中的信息，找到各个元素排序后所在位置，存储在 output 数组中
        for (int i = length - 1; i >= 0; i--)
        {
            output[array[arr[i]] - 1] = arr[i];
            array[arr[i]]--;
        }
        // 将 output 数组中的数据原封不动地拷贝到 arr 数组中
        for (int i = 0; i < length; i++)
        {
            arr[i] = output[i];
        }
        System.out.println("结果：");
        print(arr);
    }


    public static int getMax(int[] arr)
    {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] > max)
            {
                max = arr[i];
            }
        }
        return max;
    }

    private static void print(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
