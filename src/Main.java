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

        int dominant1 = DominantStrategy_firstPlayer(matrix);
        int dominant2 = DominantStrategy_secondPlayer(matrix);

        if ((dominant1 != 0) && (dominant2 != 0)) {
            System.out.println("Равновесие в доминирующих стратегиях - (" + matrix[dominant1 - 1][dominant2 - 1][0] + ";" + matrix[dominant1 - 1][dominant2 - 1][1] + "), " +
                    "профиль s" + dominant1 + ", t" + dominant2);
        }
        else {
            System.out.println("В матрице нет равновесия в доминирующих стратегиях");
        }

        int[][] nash = NashEquilibrium(matrix);
        if(nash != null) {
            System.out.println("Равновесие Нэша - (" + nash[1][0] + "," + nash[1][1] + "), " +
                    "профиль s" + nash[0][0] + ", t" + nash[0][1]);
        }
        else {
            System.out.println("В матрице нет равновесия Нэша");
        }
    }

    public static int DominantStrategy_firstPlayer(int[][][] matrix) {
        int n = matrix.length;
        int maxInRow;
        int columnIndex = Integer.MIN_VALUE;
        boolean isFound = true;
        int result;
        for (int j = 0; j < n; j++) {
            maxInRow = 0;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j][0] >= maxInRow) {
                    maxInRow = matrix[i][j][0];
                    if ((columnIndex > 0) && (columnIndex != j)) {
                        isFound = false;
                        break;
                    }
                    else {
                        columnIndex = j;
                    }
                }
            }
            if (!isFound) {
                break;
            }
        }
        if (isFound) {
            result = columnIndex + 1;
        }
        else {
            result = 0;
        }
        return result;
    }

    public static int DominantStrategy_secondPlayer(int[][][] matrix) {
        int n = matrix.length;
        int maxInColumn;
        int rowIndex = Integer.MIN_VALUE;
        boolean isFound = true;
        int result;
        for (int i = 0; i < n; i++) {
            maxInColumn = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j][1] >= maxInColumn) {
                    maxInColumn = matrix[i][j][1];
                    if ((rowIndex > 0) && (rowIndex != i)) {
                        isFound = false;
                        break;
                    }
                    else {
                        rowIndex = i;
                    }
                }
            }
            if (!isFound) {
                break;
            }
        }
        if (isFound) {
            result = rowIndex + 1;
        }
        else {
            result = 0;
        }
        return result;
    }

    public static int[][] NashEquilibrium(int[][][] matrix) {
        int n = matrix.length;
        int maxInColumn;
        int maxInRow;
        int[] columnIndex = new int[2];
        int[] rowIndex = new int[2];
        boolean isFound = false;
        int[][] result = new int[2][2];
        for (int j = 0; j < n; j++) {
            maxInColumn = 0;
            maxInRow = 0;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j][0] > maxInColumn) {
                    maxInColumn = matrix[i][j][0];
                    columnIndex[0] = (int) Math.sqrt(i);
                    columnIndex[1] = (int) Math.sqrt(j);
                }
                if (matrix[j][i][1] > maxInRow) {
                    maxInRow = matrix[i][j][1];
                    rowIndex[0] = (int) Math.sqrt(i);
                    rowIndex[1] = (int) Math.sqrt(j);
                }
            }

            if (columnIndex[0] == rowIndex[0] && columnIndex[1] == rowIndex[1]) {
                isFound = true;
                int[] equilibriumProfile = {columnIndex[0] + 1, columnIndex[1] + 1};
                int[] equilibriumValue = {matrix[columnIndex[0]][columnIndex[1]][0], matrix[columnIndex[0]][columnIndex[1]][1]};
                result[0] = equilibriumProfile;
                result[1] = equilibriumValue;
            }
        }
        if (!isFound) {
            return null;
        }
        else {
            return result;
        }
    }
}

