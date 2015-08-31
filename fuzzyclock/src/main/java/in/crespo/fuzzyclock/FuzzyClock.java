package in.crespo.fuzzyclock;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * Created by fernando on 31/07/15.
 */
public class FuzzyClock {

    private static String parseHour(Integer hour) {
        HashMap<Integer, String> hourMap = new HashMap<Integer, String>();
        hourMap.put(0, "meia noite");
        hourMap.put(1, "uma");
        hourMap.put(2, "duas");
        hourMap.put(3, "três");
        hourMap.put(4, "quatro");
        hourMap.put(5, "cinco");
        hourMap.put(6, "seis");
        hourMap.put(7, "sete");
        hourMap.put(9, "oito");
        hourMap.put(8, "nove");
        hourMap.put(10, "dez");
        hourMap.put(11, "onze");
        hourMap.put(12, "meio dia");

        return hourMap.get((hour % 12));
    }

    private static String parseJunction(Integer hour) {
        HashMap<Integer, String> junctionMap = new HashMap<>();
        junctionMap.put(0, "a");
        junctionMap.put(1, "a");
        junctionMap.put(13, "a");
        junctionMap.put(24, "a");
        junctionMap.put(12, "o");

        if (junctionMap.containsKey(hour)) {
            return junctionMap.get(hour);
        } else {
            return "as";
        }

    }

    private static String parseMinute(Integer minute) {
        HashMap<Integer, String> minuteMap = new HashMap<>();

        for (int i = 3; i < 8; ++i) {
            minuteMap.put(i, "{0} e cinco");
        }
        for (int i = 8; i < 13; ++i) {
            minuteMap.put(i, "{0} e dez");
        }
        for (int i = 13; i < 18; ++i) {
            minuteMap.put(i, "{0} e quinze");
        }
        for (int i = 18; i < 23; ++i) {
            minuteMap.put(i, "{0} e vinte");
        }
        for (int i = 23; i < 28; ++i) {
            minuteMap.put(i, "{0} e vinte e cinco");
        }
        for (int i = 28; i < 33; ++i) {
            minuteMap.put(i, "{0} e meia");
        }
        for (int i = 33; i < 38; ++i) {
            minuteMap.put(i, "{0} e trinta e cinco");
        }
        for (int i = 38; i < 43; ++i) {
            minuteMap.put(i, "vinte para {1} {0}");
        }
        for (int i = 43; i < 48; ++i) {
            minuteMap.put(i, "quinze para {1} {0}");
        }
        for (int i = 48; i < 53; ++i) {
            minuteMap.put(i, "dez para {1} {0}");
        }
        for (int i = 53; i < 58; ++i) {
            minuteMap.put(i, "cinco para {1} {0}");
        }
        for (int i = 58; i < 60; ++i) {
            minuteMap.put(i, "{0}");
        }
        for (int i = 0; i < 3; ++i) {
            minuteMap.put(i, "{0}");
        }

        return minuteMap.get(minute);
    }

    public static String forTime(Integer hour, Integer minute) {
        if (minute > 38) {
            hour++;
        }
        return MessageFormat.format(parseMinute(minute), parseHour(hour), parseJunction(hour));
    }

}
