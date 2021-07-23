package educenter.tasks.basics.dicegame;

import static java.lang.Math.random;

/**
 * Класс DicePlayer содержит информацию об игроке.
 * Поле name - имя игрока.
 * Поле numberOfWins - количество побед в раундах игры.
 * Статическое поле delayTime - задержка при выводе результатов броска
 * @author Valentin Timoshkin
 */
public class DicePlayer {

    private String name;
    private int numberOfWins = 0;
    private static int delayTime = 3000;
    
    /**
     * Создает нового игрока с именем "Player".
     */   
    public DicePlayer() {
        this.name = "Player";
    }
    /**
     * Создает нового игрока с именем name.
     * @param name имя игрока 
     */    
    public DicePlayer(String name) {
        this.name = name;
    }
    
    /**
     * @return имя игрока
     */
    public String getName() {
        return name;
    }
    /**
     * Устанавливает имя игрока.
     * @param name новое имя
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return число побед в раундах
     */
    public int getNumberOfWins() {
        return numberOfWins;
    }
    /**
     * Устанавливает число побед в раундах.
     * @param numberOfWins новое число побед
     */
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
    /**
     * Увеличивает число побед в раундах на 1.
     */
    public void incNumberOfWins() {
        this.numberOfWins += 1;
    }
    /**
     * @return время задержки при выводе результатов броска на экран
     */
    public static int getDelayTime() {
        return delayTime;
    }
    /**
     * Устанавливает время задержки при выводе результатов броска на экран.
     * @param newDelayTime новое время задержки
     */
    public static void setDelayTime(int newDelayTime) {
        delayTime = newDelayTime;
    }
    
    
    /**
     * Производит симуляцию броска игральных кубиков.
     * dieResult[i] - значение i-го кубика
     * rollResult - результат броска
     * @param numberOfDice число бросаемых кубиков
     * @return результат броска
     * @throws InterruptedException 
     */
    public int diceRoll (int numberOfDice) throws InterruptedException {
        int[] dieResult = new int[numberOfDice];
        int rollResult = 0;
        System.out.println(name + " бросает кубики");
        Thread.sleep(delayTime);
        System.out.println("Выпали значения:");
        for (int i = 0; i < numberOfDice; i++) {
            dieResult[i] = (int) (random() * 6 + 1);
            rollResult += dieResult[i];
            System.out.print(dieResult[i] + " ");
        }
        System.out.println("");
        System.out.println("Сумма значений: " + rollResult);
        return rollResult;
    }
}
