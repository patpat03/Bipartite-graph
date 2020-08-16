import java.util.*;
import java.io.*;
public class revegetate{
  static ArrayList[] graph;
  static boolean[] visited;
  static int[] color;
  static boolean  error = false;
  public static void main(String[] args) throws Exception
  {
    PrintWriter print = new PrintWriter(new File("revegetate.out"));
    Scanner scan = new Scanner(new File("revegetate.in"));
    int n = scan.nextInt();
    int m = scan.nextInt();
    int counter =0;
    color = new int[n+1];
    graph = new ArrayList[n+1];
    visited = new boolean[n+1];
    for(int i=0; i<graph.length; i++)
    {
      graph[i] = new ArrayList<cow>();
    }
    for(int i=0; i<m; i++)
    {
      String s = scan.next();
      int from = scan.nextInt();
      int to = scan.nextInt();
      graph[from].add(new cow(s, to));
      graph[to].add(new cow(s, from));
    }
    for(int i=1; i<color.length; i++)
    {
      if(color[i] == 0)
      {
        System.out.println(i);
        color[i] = 1;
        counter++;
        bfs(i);
      }
    }
    if(!error)
    {
      print.print("1");
      for(int i=0; i<counter; i++)
      {
        print.print("0");
      }
      System.out.println("");
    }
    else
    {
      print.println("0");
    }
    print.close();
    scan.close();
  }
  static void bfs(int vertex)
  {
    if(visited[vertex])
    {
      return;
    }
    visited[vertex] = true;
    for(int i=0; i<graph[vertex].size(); i++)
    {
      cow cur = (cow)graph[vertex].get(i);
      if(cur.color == 1)
      {
        if(color[cur.node] != 0 && color[cur.node] != color[vertex])
        {
          error = true;
          color[cur.node] = 1;
        }
        else
        {
            color[cur.node] = color[vertex];
        }
      }
      if(cur.color == 2)
      {
        if(color[cur.node] != 0 && color[cur.node] == color[vertex])
        {
          error = true;
          color[cur.node] = 1;
        }
        else
        {
          if(color[vertex] == 1)color[cur.node] = 2;
          if(color[vertex] == 2)color[cur.node] = 1;
        }
      }
      bfs(((cow)graph[vertex].get(i)).node);
    }
  }
}
class cow
{
  int color;
  int node;
  public cow(String type, int node)
  {
    if(type.equals("S"))
    {
      this.color = 1;
    }
    else
    {
      this.color = 2;
    }
    this.node = node;
  }
}
