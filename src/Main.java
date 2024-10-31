import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][][] matrix = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j][0] = Integer.parseInt(sc.next());
                matrix[i][j][1] = sc.nextInt();
            }
        }
        String result = NashEquilibrium(n, matrix);
        System.out.println(result);
    }

    public static String NashEquilibrium(int n, int[][][] matrix) {
        int maxInColumn;
        int maxInRow;
        int[] columnIndex = new int[2];
        int[] rowIndex = new int[2];
        String result = "";
        boolean isFound = false;
        for (int j = 0; j < n; j++) {
            maxInColumn = 0;
            maxInRow = 0;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j][0] > maxInColumn) {
                    maxInColumn = matrix[i][j][0];
                    columnIndex[0] = i;
                    columnIndex[1] = j;
                }
                if (matrix[j][i][1] > maxInRow) {
                    maxInRow = matrix[i][j][1];
                    rowIndex[0] = i;
                    rowIndex[1] = j;
                }
            }

            if (columnIndex[0] == rowIndex[0] && columnIndex[1] == rowIndex[1]) {
                isFound = true;
                String equilibriumValue = "(" + matrix[columnIndex[0]][columnIndex[1]][0] + ";"
                        + matrix[columnIndex[0]][columnIndex[1]][1] + ")";
                String equilibriumProfile = "s" + (columnIndex[0] + 1) + " " + "t" + (columnIndex[1] + 1);
                result = result + equilibriumValue + " - равновесие Нэша, профиль " + equilibriumProfile + '\n';
            }
        }
        if (!isFound) {
            return "В матрице нет равновесия Нэша";
        }
        else {
            return result;
        }
    }
}

