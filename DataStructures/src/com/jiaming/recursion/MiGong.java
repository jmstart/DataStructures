package com.jiaming.recursion;

/**
 * @author jmstart
 * @create 2020-02-17 17:37
 * 递归遍历迷宫
 * 算法:递归回溯
 */
public class MiGong {

    public static void main(String[] args) {
        //创建一个二维数组 模拟迷宫
        //地图
        // 1:墙  0:空地
        int[][] map = new int[8][7];
        // 1表示墙 上下左右都包上一圈墙 都置为 1
        // 上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("原始地图的情况:");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.printf("%d \t",anInt);
            }
            System.out.println();
        }

        //验证递归地找路
        setWay(map,1,1);

        //输出新地图
        System.out.println("走完地图的情况:");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.printf("%d \t",anInt);
            }
            System.out.println();
        }

    }

    //使用递归回溯走迷宫
    //输入/** ,点击“Enter”，自动根据参数和返回值生成注释模板
    /**
     * @param map 表示地图
     * @param i 表示从哪行出发
     * @param j 表示从哪列出发
     * @return 找到路线 返回 true
     */
    //说明:
    //小球从[1][1]出发到[6][5]结束
    //约定: 1:墙  0:空足迹  2:正确的路线足迹  3:错误的路线足迹(走不通)
    //策略: 走的方向 下——>右——>上——>左 不通回溯
    //策略不同到达的路线就不同
    public static boolean setWay(int[][] map, int i, int j){
        // 路线找到
        if (map[6][5] == 2){

            return true;
        }else {
            //判断当前点是否是空足迹
            if (map[i][j] == 0){
                //按照策略走 下——>右——>上——>左
                map[i][j] = 2;

                if (setWay(map, i + 1, j)){ //向下走  行加一
                    return true;
                }else if (setWay(map, i, j + 1)){ //向右走  列加一
                    return true;
                }else if (setWay(map, i - 1, j)){ //向上走  行减一
                    return true;
                }else if (setWay(map, i, j - 1)){ //向左走  列减一
                    return true;
                }else {
                    //走不通
                    map[i][j] = 3;
                    return false;
                }

            }else {

                return false;
            }
        }

    }

}
