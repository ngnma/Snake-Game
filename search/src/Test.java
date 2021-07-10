import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        File f=new File("/Users/ngn/Desktop/matrix.txt");
        Scanner input=new Scanner(f);

        int n=0;
        String [] a=new String[2000];
        while (input.hasNextLine()){
            a[n]=input.nextLine();
            n++;
        }
        char [][] plan= new char[n][n];
        for (int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
               plan[i][j]=a[i].charAt(j);
            }
        }


    }
}
