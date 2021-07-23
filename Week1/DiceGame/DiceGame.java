package educenter.tasks.basics.dicegame;

import java.util.Scanner;

/**
 * Класс DiceGame реализует симуляцию игры в кости.
 * В методах класса используются объекты класса DicePlayer.
 * Поле numberOfPlayers - число игроков.
 * Поле numberOfDice - число кубиков.
 * Поле currentWinner - номер игрока, который начинает первым (победитель прошлого раунда).
 * Cтатическое поле numberOfRequiredWins - число необходимых побед в раундах для победы в игре.
 * Статическое поле delayTime - время задержки при выводе результатов раунда на экран.
 * @author Valentin Timoshkin
 */
public class DiceGame {
    
    private int numberOfPlayers = 2;
    private int numberOfDice = 1;
    private int currentWinner = 0;
    private static int numberOfRequiredWins = 7;
    private static int delayTime = 3000;
    
    /**
     * Создает объект новой игры со значениями по умолчанию.
     */
    public DiceGame() {}
    /**
     * Создает объект новой игры с заданных количеством игроков и кубиков.
     * @param numberOfPlayers количество игроков
     * @param numberOfDice количество кубиков
     */
    public DiceGame(int numberOfPlayers, int numberOfDice) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfDice = numberOfDice;
    }
    /**
     * @return количество игроков
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    /**
     * Устанавливает количество игроков
     * @param numberOfPlayers количество игроков
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    /**
     * @return количество кубиков
     */
    public int getNumberOfDice() {
        return numberOfDice;
    }
    /**
     * Устанавливает количество кубиков
     * @param numberOfDice количество кубиков
     */
    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }
    /**
     * @return победитель прошлого раунда
     */
    public int getCurrentWinner() {
        return currentWinner;
    }
    /**
     * Устанавливает игрока, который будет бросать первым.
     * @param currentWinner номер игрока
     */
    public void setCurrentWinner(int currentWinner) {
        this.currentWinner = currentWinner;
    }
    /**
     * @return необходимое для победы количество выигранных раундов
     */
    public static int getNumberOfRequiredWins() {
        return numberOfRequiredWins;
    }
    /**
     * Устанавливает необходимое для победы количество выигранных раундов.
     * @param newNumberOfRequiredWins количество побед в раундах
     */
    public static void setNumberOfRequiredWins(int newNumberOfRequiredWins) {
        numberOfRequiredWins = newNumberOfRequiredWins;
    }
    /**
     * @return время задержки при выводе результатов раунда на экран
     */
    public static int getDelayTime() {
        return delayTime;
    }
    /**
     * Устанавливает время задержки при выводе результатов раунда на экран.
     * @param newDelayTime новое время задержки
     */
    public static void setDelayTime(int newDelayTime) {
        delayTime = newDelayTime;
    }
    
    /**
     * Производит ввод параметров игры (количество игроков и кубиков) и имён игроков
     * @return массив объектов DicePlayer с заданными именами
     */
    public DicePlayer[] startDiceGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество игроков:");
        numberOfPlayers = in.nextInt();
        System.out.println("Введите количество кубиков:");
        numberOfDice = in.nextInt();
        DicePlayer[] dicePlayers = new DicePlayer[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers - 1; i++) {
            System.out.println("Введите имя игрока №" + (i + 1) + ":");
            DicePlayer dicePlayer = new DicePlayer(in.next());
            dicePlayers[i] = dicePlayer;
        }
        DicePlayer computer = new DicePlayer("Computer");
        dicePlayers[numberOfPlayers - 1] = computer;
        return dicePlayers;
    }
    /**
     * Производит симуляцию раунда игры. Каждый игрок по очереди бросает кубики.
     * В случае равенства очков объявляется ничья и победные очки не присуждаются.
     * @param dicePlayers игроки в текущем раунде
     * @return игроки после окончания раунда
     * @throws InterruptedException 
     */
    public DicePlayer[] diceGameRound(DicePlayer[] dicePlayers) throws InterruptedException {
        int[] rollResults = new int[numberOfPlayers];
        for(int i = currentWinner; i < numberOfPlayers; i++) {
            rollResults[i] = dicePlayers[i].diceRoll(numberOfDice);
        }
        for(int i = 0; i < currentWinner; i++) {
            rollResults[i] = dicePlayers[i].diceRoll(numberOfDice);
        }
        int maxResult = 0;
        for(int i = 0; i < numberOfPlayers; i++) {
            if(rollResults[i] > maxResult) {
                maxResult = rollResults[i];
            }
        }
        int numberOfWinners = 0;
        int currentLeader = 0;
        for(int i = 0; i < numberOfPlayers; i++) {
            if(rollResults[i] == maxResult) {
                numberOfWinners += 1;
                currentLeader = i;
            }
        }
        if (numberOfWinners > 1) {
            System.out.println("Ничья");
            System.out.println("");
            Thread.sleep(delayTime);
        }
        else {
            currentWinner = currentLeader;
            System.out.println("Победил " + dicePlayers[currentWinner].getName() + ". Конец раунда");
            System.out.println("");
            dicePlayers[currentWinner].incNumberOfWins();
            Thread.sleep(delayTime);
        }
        return dicePlayers;
    }
    
    /**
     * Производит симуляцию игры в кости.
     * Раунды проводятся до тех пор, пока один из игроков не наберёт необходимое для победы в игре число побед в раундах.
     * @param dicePlayers игроки в данной игре
     * @throws InterruptedException 
     */
    public void diceGame (DicePlayer[] dicePlayers) throws InterruptedException {
        boolean endGame = false;
        while (endGame == false) {
            dicePlayers = this.diceGameRound(dicePlayers);
            if (dicePlayers[currentWinner].getNumberOfWins() >= numberOfRequiredWins ) {
                System.out.println("");
                System.out.println("Игра окончена. Победитель " + dicePlayers[currentWinner].getName());
                endGame = true;
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
    DiceGame diceGame = new DiceGame();
    DicePlayer[] dicePlayers;
    dicePlayers = diceGame.startDiceGame();
    diceGame.diceGame(dicePlayers);
    }
}
