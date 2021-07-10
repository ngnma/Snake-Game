import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
    public static void main(String[] args) throws IOException {
        Node_dfs root = null;
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
                    root = new Node_dfs(i, j, null);
                }
            }
        }


        //init ds
        Stack<Node_dfs> unchecked = new Stack<Node_dfs>();
        unchecked.push(root);


        while (true){
            if(unchecked.empty()){
                System.out.println("not found");
                break;
            }
            Node_dfs checking=unchecked.pop();

            if(plan[(checking.x+1)%n][checking.y]=='0'){
                unchecked.push(new Node_dfs((checking.x+1)%n,checking.y,checking));
                plan[(checking.x+1)%n][checking.y]='1';
            }else if(plan[(checking.x+1)%n][checking.y]=='A'){
               findPath_DFS(new Node_dfs((checking.x+1)%n,checking.y,checking),n);
               break;
            }


            if(plan[(checking.x+3)%n][checking.y]=='0'){
                unchecked.push(new Node_dfs((checking.x+3)%n,checking.y,checking));
                plan[(checking.x+3)%n][checking.y]='1';
            }else if(plan[(checking.x+3)%n][checking.y]=='A'){
                findPath_DFS(new Node_dfs((checking.x+3)%n,checking.y,checking),n);
                break;
            }


            if(plan[checking.x][(checking.y+1)%n]=='0'){
                unchecked.push(new Node_dfs(checking.x,(checking.y+1)%n,checking));
                plan[checking.x][(checking.y+1)%n]='1';
            }else if(plan[checking.x][(checking.y+1)%n]=='A'){
                findPath_DFS(new Node_dfs(checking.x,(checking.y+1)%n,checking),n);
                break;
            }


            if(plan[checking.x][(checking.y+3)%n]=='0'){
                unchecked.push(new Node_dfs(checking.x,(checking.y+3)%n,checking));
                plan[checking.x][(checking.y+3)%n]='1';
            }else if(plan[checking.x][(checking.y+3)%n]=='A'){
                findPath_DFS(new Node_dfs(checking.x,(checking.y+3)%n,checking),n);
                break;
            }
        }

    }

    static void findPath_DFS(Node_dfs goal,int n) {
        String path="";
        Node_dfs pointer=goal;
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

class Node_dfs{
    int x,y;
    Node_dfs dad;
    public Node_dfs(int x, int y, Node_dfs dad) {
        this.x = x;
        this.y = y;
        this.dad = dad;
    }
}
