package seng201.team25.services;

/**
 * Class to manage a static gold balance between controllers
 */

public class GoldManager {
    private static int gold;

    public static void setGold(int goldValue) {
        gold = goldValue;
    }

    public static int getGoldBalance() {
        return gold;
    }

    /**
     * Increases the player's gold balance.
     * @param goldToAdd Amount of gold to add to balance
     * @return Current balance
     */
    public static int increaseGoldBalance(int goldToAdd) {
        gold += goldToAdd;
        return gold;
    }

    /**
     * Decreases the players gold balance.
     * Returns -1 if insufficient gold is available. This does not change the
     * player's gold balance.
     * @param goldToTake Amount of gold to add to balance
     * @return Current balance, or -1 if insufficient funds
     */
    public static int decreaseGoldBalance(int goldToTake) {
        if (goldToTake > gold) {
            return -1;
        }
        gold -= goldToTake;
        return gold;
    }
}
