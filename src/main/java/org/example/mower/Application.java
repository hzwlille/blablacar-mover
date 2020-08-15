package org.example.mower;

import lombok.extern.slf4j.Slf4j;
import org.example.mower.worker.MowerWorker;
import org.example.mower.worker.Worker;
import org.example.mower.reader.FileReader;
import org.example.mower.object.Lawn;
import org.example.mower.object.Mower;
import org.example.mower.util.MowerUtils;

import java.io.IOException;
import java.util.*;

/**
 * Entry point of the application
 */
@Slf4j
public class Application {
    public static void main(String[] args) {


        System.out.print("Start the program\n");
        ApplicationOptions options = new ApplicationOptions();

        FileReader fileReader = new FileReader(options.getFileName());

        Lawn lawn= new Lawn();
        List<Mower> mowers= new ArrayList();

        try {
            fileReader.readStats(lawn, mowers);
        } catch (IOException e) {
            log.info("Erro parsing the file");
            System.exit(1);
        }

        //Start each thread for the calculation
        List<Thread> list = new ArrayList<Thread>();
        long start = System.currentTimeMillis();


        for (int i = 0; i < mowers.size(); i++) {
            //Create a thread for each mower
            final int index = i;
            Thread thread = new Thread(() -> {
                Worker newWorker = new MowerWorker(index, lawn, mowers);
                newWorker.start();
            }, "thread_" + i);
            thread.start();
            list.add(thread);
        }
        //Finish calculation, print result
        try {
            for (Thread thread : list) {
                thread.join();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }

        long time = System.currentTimeMillis() - start;

        MowerUtils.printResult(mowers);

        System.out.printf("Caculation took %d milliseconds", time);

        System.out.println("Finish the program");
    }
}
