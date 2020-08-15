package org.example.mower.reader;

import org.example.mower.object.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Read from file and create Lawn object and a List of Mower objects
 */
public class FileReader {

    private final String fileName;


    public FileReader(String fileName) {
        this.fileName=fileName;
    }

    /**
     * Open the file
     * Read from file and copy created lawn and list of mowers to input references
     *
     * @param lawn
     * @param mowers
     * @throws IOException
     */
    //TODO Handle input exception
    public void readStats(Lawn lawn, List<Mower> mowers) throws IOException {

        FileInputStream fstream = new FileInputStream(this.fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine = br.readLine();
        String[] size=strLine.split(" ");

        lawn.setSizeX(Integer.parseInt(size[0]));
        lawn.setSizeY(Integer.parseInt(size[1]));

        int index=0;
        while ((strLine = br.readLine()) != null) {
            String[] p=strLine.split(" ");
            int x=Integer.parseInt(p[0]);
            int y=Integer.valueOf(p[1]);
            Orientation o= Orientation.valueOf(p[2]);
            Position position= new Position(x,y,o,-1);

            strLine = br.readLine();//Get commands
            int totalSteps=strLine.length();
            List<Action> actions= new ArrayList();
            for(int i=0;i<totalSteps;i++){
                actions.add(Action.valueOf(String.valueOf(strLine.charAt(i))));
            }
            Mower mower= new Mower(position,actions,index);
            mowers.add(mower);
            index++;
        }

        fstream.close();
    }
}
