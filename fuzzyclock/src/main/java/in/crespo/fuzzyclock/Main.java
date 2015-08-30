package in.crespo.fuzzyclock;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();

        System.out.print(FuzzyClock.forTime(date.getHour(), date.getMinute()));
    }
}
