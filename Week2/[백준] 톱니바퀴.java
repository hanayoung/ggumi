import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
  static ArrayList<Integer>[] arr;
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    arr = new ArrayList[4];
    StringTokenizer st = null;
    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(bf.readLine());
      String str = st.nextToken();
      arr[i] = new ArrayList<>();
      for (int j = 0; j < 8; j++) {
        arr[i].add(str.charAt(j)-'0');
      }
    }
    st = new StringTokenizer(bf.readLine());
    int K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(bf.readLine());
      int order = Integer.parseInt(st.nextToken())-1;
      int dir = Integer.parseInt(st.nextToken());
      updateArr(order, dir);
      if(order == 0) compare(1, 0, dir);
      else if(order == 3) compare(2, 3, dir);
      else {
        compare(order-1, order, dir);
        compare(order+1, order, dir);
      }
    }

    int score = 0;
    for (int i = 0; i < 4; i++) {
      if(arr[i].get(0) == 1) {
        score += Math.pow(2, i);
      }
    }
    System.out.println(score);
  }

  static void compare(int nextTo, int cur, int dir){ // 비교하기, return 값이 true일 경우 같음, false일 경우 다름 -> 다를 경우 배열 update 해야함
    // nextTo가 비교할 톱니의 인덱스, cur이 현재 톱니의 인덱스, dir이 현재 톱니가 움직이고자 하는 방향
    if(nextTo > cur){ // 오른쪽 톱니와의 비교
      if(dir == -1){
        if(arr[nextTo].get(6).equals(arr[cur].get(1)) == false){
          updateArr(nextTo, dir*-1);
          if(nextTo < 3) compare(nextTo+1, nextTo, dir*-1);
        }
      }else if(dir == 1) {
        if(arr[nextTo].get(6).equals(arr[cur].get(3)) == false){
          updateArr(nextTo, dir*-1);
          if(nextTo < 3) compare(nextTo+1, nextTo, dir*-1);
        }
      }
    }else if(nextTo < cur){
      if(dir == -1){
        if(arr[nextTo].get(2).equals(arr[cur].get(5)) == false){
          updateArr(nextTo, dir*-1);
          if(nextTo > 0) compare(nextTo-1, nextTo, dir*-1);
        }
      }else if(dir == 1){
        if(arr[nextTo].get(2).equals(arr[cur].get(7)) == false){
          updateArr(nextTo, dir*-1);
          if(nextTo > 0) compare(nextTo-1, nextTo, dir*-1);
        }
      }
    }
  }

  static void updateArr(int order, int dir){ // 여기서는 배열 값 이동만
    if(dir == 1){
      int tmp = arr[order].get(7);
      arr[order].remove(7);
      arr[order].add(0,tmp);
    }else if(dir == -1){
      int tmp = arr[order].get(0);
      arr[order].remove(0);
      arr[order].add(tmp);      
    }
  }
}

// 회전시켰을 때의 극과 다른 톱니의 극이 같으면 회전 x
// 다른 톱니의 극이 다르다면 반대방향으로 회전
// 예를 들면, 1번 톱니가 시계방향으로 회전하고, 극이 다르다면 2번 톱니는 반시계방향으로 회전
// 톱니 개수는 8개, 각 톱니의 방향을 나타내는 ArrayList 생성(크기가 8인 ArrayList 4개)
// N극이 0, S극이 1
// 2번 3번 톱니가 회전한다면 양옆을 확인해야함
// 왼쪽 톱니랑 비교할 때:
// 시계 방향이면 현재 톱니의 5번이랑 왼쪽 톱니의 2번이랑 비교, 빈시계방향이면 현재 톱니의 6번이랑 왼쪽 톱니의 2번이랑 비교
// 왼쪽 톱니의 2번이랑 비교한다는 건 동일함
// 오른쪽 톱니랑 비교할 때:
// 시계 방향이면 현재 톱니의 1번이랑 오른쪽 톱니의 6번이랑 비교, 반시계방향이면 현재 톱니의 3번이랑 오른쪽 톱니의 6번이랑 비교
// 오른쪽 톱니의 6번이랑 비교한다는 건 동일함
// 바꿀 거 한 줄 씩 받으면서
// 바꿀 톱니가 2,3일 때, 1,4일 때 분리
// 톱니가 회전할 때, 어떻게 다시 배열에 넣어줄지?
// -> 시계방향이면, ArrayList의 마지막 값을 저장해놓고, removeAt(7)한 후, add(0, 값)으로 넣어주기
// -> 반시계방향이면, ArrayList의 첫 번째 값을 저장해놓고, removeAt(0)한 후, add(값)으로 넣기
// 시계 방향 - 1, 반시계 방향 - -1
// 0 1 2 3 4 5 6 7 
// 1 2 3 4 5 6 7 0
// 7 0 1 2 3 4 5 6 
