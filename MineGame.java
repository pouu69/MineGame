import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by kuPark on 2016. 4. 27..
 */
public class MineGame {
    // 보드 행과 열
    public static int MAX_ROW = 10;
    public static int MAX_COL = 10;
    //지뢰 총갯수
    public static int TOTAL_MINE = 10;
    // 보드 배열
    public static int[][] boards = new int[MAX_ROW][MAX_COL];
    // 지뢰 위치 hashmap
    public static HashMap<Integer, Integer> mines = new HashMap();

    /*
        지뢰 위치를 랜덤하게 생성
        HashMap을 이용하여 랜덤값 중복을 방지 한다
     */
    public void getMinePosition(){
        Random random = new Random();

        for(int i=0 ; i < TOTAL_MINE ; ++i){
            int ranNum = random.nextInt(100);
            if(!mines.containsKey(ranNum)){
                mines.put(ranNum, -1);
            }else{
                --i;
            }
        }
    }

    /*
        0으로 초기화 되어있는 보드 배열에 지뢰 위치값에 따라 -1 로 지뢰를 세팅한다.
     */
    public void boardInit(){
        Collection k = mines.keySet();
        Iterator itr = k.iterator();
        while(itr.hasNext()){
            int position = Integer.parseInt(itr.next().toString());
            boards[position/10][position%10] = -1;
        }
    }

    /*
        각 포지션 별로 자신의 위치를 기준으로 8칸을 검사하여 -1(지뢰값)일 경우 cnt를 +1 해주어 주변 지뢰 갯수를 카운팅한다
     */
    public void makeBoard(){
        for(int i=0 ; i<MAX_ROW ; ++i){
            for(int j=0 ; j<MAX_COL ; ++j){
                if(boards[i][j] == -1){
                    continue;
                }
                int cnt = 0;

                for(int n=-1 ; n<=1 ; ++n){
                    for(int m=-1 ; m<=1 ; ++m){
                        if(n==0 && m==0){
                            continue;
                        }
                        if(i+n >= 0 && j+m >= 0 && i+n < MAX_ROW  && j+m < MAX_COL){
                            if(boards[i+n][j+m] == -1){
                                cnt++;
                            }
                        }
                    }
                }
                boards[i][j] = cnt;
            }
        }
    }

    /*
        배열을 순회하며 배열값이 -1일 경우 지뢰로 간주하여 '*' 로 출력 한다.
     */
    public void printBoard(){
        for(int i=0 ; i<MAX_ROW ; ++i){
            for(int j=0 ; j<MAX_COL ; ++j){
                int val = boards[i][j];
                if(val == -1){
                    System.out.print("*");
                }else{
                    System.out.print(val);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        MineGame mineGame = new MineGame();

        mineGame.getMinePosition();
        mineGame.boardInit();
        mineGame.makeBoard();
        mineGame.printBoard();
    }
}
