/*

    Stores our temp codes in HashMap
    Limit: 5min
    Works: As long as app is open

    Made by: Jonas
    Date: 05-04-2025

*/

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import util.*;

public class HashMapStorage {

    // Attributes
    private static final TextUI ui = new TextUI();
    private static final long currentTime = System.currentTimeMillis();
    private static final HashMap<String, Long> storage = new HashMap<>();
    private static final long exp_time = TimeUnit.MINUTES.toMillis(5); // 5min

    // ________________________________________________________

    public static void saveCode(String input){

        storage.put(input, currentTime);

    }

    // ________________________________________________________

    public static boolean validation(String input){

        Long ts = storage.get(input);
        if (ts == null){
            ui.displayMsg("Code not found.. Try again.");
            return false;
        }

        // Checks if HashMap time vs currentTime is over 5 minutes == expired
        return (currentTime - ts) <= exp_time;

    }

} // HashMap class end
