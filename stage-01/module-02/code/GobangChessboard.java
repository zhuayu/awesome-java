import java.util.Arrays;
import java.util.Scanner;

public class GobangChessboard{
    private int[][] chessboard;
    private String[] chessboardBorder;
    private int currentSide = 1; // 1 backSide ❌ 2 whiteSide ⭕

    GobangChessboard() {}

    public GobangChessboard(int len) {
        this.chessboard = new int[len][len];
        this.chessboardBorder = chessboardBorder(len);
    }

    public void start() {
        getChessboard();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (this.currentSide == 1) {
                System.out.println("请 ❌ 下棋");
            } else {
                System.out.println("请 ⭕ 下棋");
            }
            System.out.println("请输入您下棋到第几行(用0 - f表示)");
            int $row = getPoint(scanner);
            if ($row == -1) {
                System.out.println("行数超出范围 请重新输入");
                continue;
            }
            System.out.println("请输入您下棋到第几列(用0 - f表示)");
            int $col = getPoint(scanner);
            if ($col == -1) {
                System.out.println("列数超出范围 请重新输入");
                continue;
            }
            if (this.chessboard[$col][$row] == 1 || this.chessboard[$col][$row] == 2) {
                System.out.println("此处已有棋子, 请选择其他的位置");
                continue;
            }
            this.setChess($col, $row);
            Boolean $isWin = checkWin($col, $row);
            getChessboard();
            if ($isWin) {
                System.out.println("Win ~");
                break;
            }
            this.currentSide = this.currentSide == 1 ? 2 : 1;
        }
    }

    private int getPoint(Scanner scanner) {
        String key = scanner.next();
        int index = -1;
        for (int i = 0 ; i < chessboardBorder.length ; i++){
            if (key.equals(chessboardBorder[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    public void setChess(int $col, int $row) {
        this.chessboard[$col][$row] = this.currentSide;
    }

    private Boolean checkWin(int $col, int $row) {
        if(checkWinInRowLine($col, $row)) return true;
        if(checkWinInColLine($col, $row)) return true;
        if(checkWinInLeftLine($col, $row)) return true;
        return checkWinInRightline($col, $row);
    }

    private  Boolean checkWinInColLine(int $col, int $row) {
        int[] ninePoint = new int[9];
        Arrays.fill(ninePoint,0);
        for (int i = $col -4 , j = 0 ; i <= $col + 4 ; i ++){
            if (i < 0){
                continue;
            }
            if (i > 15){
                break;
            }
            ninePoint[j] = this.chessboard[i][$row];
            j++;
        }
        return checkNinePointIsContinuity(ninePoint, this.currentSide);
    }

    private Boolean checkWinInRowLine(int $col, int $row) {
        int[] ninePoint = new int[9];
        Arrays.fill(ninePoint,0);
        for (int i = $row -4 , j = 0 ; i <= $row + 4 ; i ++){
            if (i < 0){
                continue;
            }
            if (i > 15){
                break;
            }
            ninePoint[j] = this.chessboard[$col][i];
            j++;
        }
        return checkNinePointIsContinuity(ninePoint, this.currentSide);
    }

    private  Boolean checkWinInLeftLine(int $col, int $row) {
        int[] ninePoint = new int[9];
        Arrays.fill(ninePoint,0);
        for (int i = $col -4 , j = $row - 4 , k = 0; i <= $col + 4 && j <= $row + 4; i ++ , j++){
            if (i < 0 || j < 0){
                continue;
            }
            if (i > 15 || j > 15){
                break;
            }
            ninePoint[k] = this.chessboard[i][j];
            k++;
        }
        return checkNinePointIsContinuity(ninePoint, this.currentSide);
    }

    private  Boolean checkWinInRightline(int $col, int $row) {
        int[] ninePoint = new int[9];
        Arrays.fill(ninePoint,0);
        for (int i = $col - 4 , j = $row + 4 , k = 0; i <= $col + 4 && j >= $row - 4; i ++ , j--){
            if (i < 0 || j > 15){
                continue;
            }
            if (i > 15 || j < 0){
                break;
            }
            ninePoint[k] = this.chessboard[i][j];
            k++;
        }
        return checkNinePointIsContinuity(ninePoint, this.currentSide);
    }

    private  Boolean checkNinePointIsContinuity(int[] $arr, int $num) {
        int $lastPoint = 0;
        int $count = 1;
        int $maxCount = 0;
        for (int j : $arr) {
            if (j == $lastPoint && j == $num) {
                $count++;
                $maxCount = Math.max($count, $maxCount);
            } else {
                $count = 1;
            }
            $lastPoint = j;
        }
        return $maxCount > 4;
    }

    public void getChessboard() {
        printHeader();
        printBody();
    }

    private void printHeader() {
        String[] arr = this.chessboardBorder;
        System.out.print("   ");
        for (String s : arr) {
            System.out.print(s + "  ");
        }
        System.out.println();
    }

    private String getPointStr(int $currentSide) {
        return switch ($currentSide) {
            case 2 -> "⭕️ ";
            case 1 -> "❌ ";
            default -> "+  ";
        };
    }

    private void printBody() {
        String[] arr = this.chessboardBorder;
        int[][] chessboard = this.chessboard;
        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i] + "  ");
            for (int j = 0; j < arr.length; j ++) {
                System.out.print(getPointStr(chessboard[i][j]));
            }
            System.out.println();
        }
    }

    private String[] chessboardBorder(int len) {
        String[] arr = new String[len];
        for (int r = 0; r < len; r++) {
            arr[r] = Integer.toHexString(r);
        }
        return arr;
    }
}
