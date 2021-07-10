import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class A_star {
    public static void main(String[] args) throws IOException {
        Node_A_star root = null;
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
        //set root
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (plan[i][j] == 'S') {
                    root = new Node_A_star(i, j);
                }
                if (plan[i][j] == 'A') {
                    new Node_A_star().setGoalPosition(i,j);
                }
            }
        }

        ArrayList<Node_A_star> unchecked=new ArrayList<Node_A_star>();
        unchecked.add(root);

        while (true){
            if(unchecked.isEmpty()){
                System.out.println("not found");
                break;
            }
            Node_A_star checking;
            checking=unchecked.get(0);
            int finali=0;
            for (int i=0;i<unchecked.size();i++){
                if(unchecked.get(i).evaluation_function()<checking.evaluation_function()){
                    checking=unchecked.get(i);
                    finali=i;
                }
            }
            unchecked.remove(finali);


            if(plan[checking.x][checking.y]=='A'){
                findPath_A_star(checking,n);
                break;
            }



            if(plan[(checking.x+1)%n][checking.y]=='0'){
                unchecked.add(new Node_A_star((checking.x+1)%n,checking.y,checking));
                plan[(checking.x+1)%n][checking.y]='1';
            }else if(plan[(checking.x+1)%n][checking.y]=='A'){
                unchecked.add(new Node_A_star((checking.x+1)%n,checking.y,checking));
            }


            if(plan[(checking.x+3)%n][checking.y]=='0'){
                unchecked.add(new Node_A_star((checking.x+3)%n,checking.y,checking));
                plan[(checking.x+3)%n][checking.y]='1';
            }else if(plan[(checking.x+3)%n][checking.y]=='A'){
                unchecked.add(new Node_A_star((checking.x+3)%n,checking.y,checking));
            }


            if(plan[checking.x][(checking.y+1)%n]=='0'){
                unchecked.add(new Node_A_star(checking.x,(checking.y+1)%n,checking));
                plan[checking.x][(checking.y+1)%n]='1';
            }else if(plan[checking.x][(checking.y+1)%n]=='A'){
                unchecked.add(new Node_A_star(checking.x,(checking.y+1)%n,checking));
            }


            if(plan[checking.x][(checking.y+3)%n]=='0'){
                unchecked.add(new Node_A_star(checking.x,(checking.y+3)%n,checking));
                plan[checking.x][(checking.y+3)%n]='1';
            }else if(plan[checking.x][(checking.y+3)%n]=='A'){
                unchecked.add(new Node_A_star(checking.x,(checking.y+3)%n,checking));
            }


        }

    }

    private static void findPath_A_star(Node_A_star checking, int n) {
        String path="";
        Node_A_star pointer=checking;
        do{
            if((pointer.dad.x-pointer.x+4)%n==1){
                path=("u").concat(path);
            }else if(pointer.x!=pointer.dad.x){
                path=("d").concat(path);
            }else if((pointer.dad.y-pointer.y+4)%n==1){
                path=("l").concat(path);
            }else{
                path=("r").concat(path);
            }
            pointer=pointer.dad;
        }while (pointer.dad!=null);
        path=path.replace("u","Up ");
        path=path.replace("r","Right ");
        path=path.replace("d","Down ");
        path=path.replace("l","Left ");
        path=path.trim();
        System.out.println(path);
    }
}
class Node_A_star{
    int x,y,h=0,g;
    static int goalX,goalY;
    Node_A_star dad;

    public Node_A_star(){}
    //for root
    public Node_A_star(int x, int y) {
        this.x = x;
        this.y = y;
        dad=null;
        g=0;

    }
    public Node_A_star(int x, int y, Node_A_star dad) {
        this.x = x;
        this.y = y;
        this.dad = dad;
        g=dad.g+1;
        //set h=manhattan distanc
        if(goalX>x){
            h+=goalX-x;
        }else {
            h+=x-goalX;
        }

        if(goalY>y){
            h+=goalY-y;
        }else {
            h+=y-goalY;
        }


    }

    public int evaluation_function() {
        return h+g;
    }

    public static void setGoalPosition(int x, int y) {
        goalX=x;
        goalY=y;
    }
}