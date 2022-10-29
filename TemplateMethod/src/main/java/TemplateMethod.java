abstract class Game {
    protected int currentPlayer;
    protected final int numberOfPlayer;

    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public void run() {
        start();
        while(!haveWinner()) {
            takeTurn();
        }
        System.out.println("Player "+getWinningPlayer()+" wins");
    }

    protected abstract int getWinningPlayer();
    protected abstract void takeTurn();
    protected abstract boolean haveWinner();
    protected abstract void start();
}

class Chess extends Game {
    private int maxTurns=10;
    private int turn=1;

    public Chess() {
        super(2);
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }

    @Override
    protected void takeTurn() {
        System.out.println("turn "+(turn++)+" taken by "+currentPlayer);
    }

    @Override
    protected boolean haveWinner() {
        return turn==maxTurns;
    }

    @Override
    protected void start() {
        System.out.println("Starting a game of chess.");
    }
}

public class TemplateMethod {
    public static void main(String[] args) {
        Chess chess=new Chess();
        chess.run();
    }
}
