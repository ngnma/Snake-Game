import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BFS {
    public static void main(String[] args) throws IOException {
        Node_bfs root = null;
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
                    root = new Node_bfs(i, j, null);
                }
            }
        }





        ArrayList<Node_bfs> unchecked=new ArrayList<>();
        unchecked.add(root);

        while (true){
            if(unchecked.isEmpty()){
                System.out.println("not found");
                break;
            }
            Node_bfs checking=unchecked.get(0);
            unchecked.remove(0);

            if(plan[checking.x][checking.y]=='A'){
                findPath_BFS(checking,n);
                break;
            }

            if(plan[(checking.x+1)%n][checking.y]=='0'){
                unchecked.add(new Node_bfs((checking.x+1)%n,checking.y,checking));
                plan[(checking.x+1)%n][checking.y]='1';
            }else if(plan[(checking.x+1)%n][checking.y]=='A'){
                unchecked.add(new Node_bfs((checking.x+1)%n,checking.y,checking));
            }


            if(plan[(checking.x+3)%n][checking.y]=='0'){
                unchecked.add(new Node_bfs((checking.x+3)%n,checking.y,checking));
                plan[(checking.x+3)%n][checking.y]='1';
            }else if(plan[(checking.x+3)%n][checking.y]=='A'){
                unchecked.add(new Node_bfs((checking.x+3)%n,checking.y,checking));
            }


            if(plan[checking.x][(checking.y+1)%n]=='0'){
                unchecked.add(new Node_bfs(checking.x,(checking.y+1)%n,checking));
                plan[checking.x][(checking.y+1)%n]='1';
            }else if(plan[checking.x][(checking.y+1)%n]=='A'){
                unchecked.add(new Node_bfs(checking.x,(checking.y+1)%n,checking));
            }


            if(plan[checking.x][(checking.y+3)%n]=='0'){
                unchecked.add(new Node_bfs(checking.x,(checking.y+3)%n,checking));
                plan[checking.x][(checking.y+3)%n]='1';
            }else if(plan[checking.x][(checking.y+3)%n]=='A'){
                unchecked.add(new Node_bfs(checking.x,(checking.y+3)%n,checking));
            }

        }


    }

    private static void findPath_BFS(Node_bfs goal,int n) {
        String path="";
        Node_bfs pointer=goal;
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
class Node_bfs{
    int x,y;
    Node_bfs dad;
    public Node_bfs(int x, int y, Node_bfs dad) {
        this.x = x;
        this.y = y;
        this.dad = dad;
    }
}

/*
after visiting node
0->1
S->1
A->A
 */


