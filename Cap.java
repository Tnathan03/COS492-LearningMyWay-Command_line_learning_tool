import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cap {

    // main that starts game/test
    public static void main(String[] args) {

        if (args.length != 2) { // testing command line args
            System.out.println("Input error");
            System.exit(0);
        } else {
            try {
                //System.out.println("\nLoading.... (This might take a minute)");
                Scanner inFile = new Scanner(new File(args[0]), "UTF-8");
                String[] question = new String[100];
                String[] answer = new String[100];
                int[] level = new int[100];
                int q = 0;
                while (inFile.hasNext()) {
                    String[] tokens = inFile.next().split(";");
                    for (String temp : tokens) {
                        //System.out.println(tokens[i]);
                        String[] t = temp.split("[|]", 0);

                        for (int j = 1; j <= t.length; j++) {
                            if (j % 3 == 1) {
                                question[q] = t[j - 1];
                            }
                            if (j % 3 == 2) {
                                answer[q] = t[j - 1];
                            }
                            if (j % 3 == 0) {
                                level[q] = Integer.parseInt(t[j - 1]);
                            }
                            //System.out.println(t[j-1]);
                        }
                        //System.out.println();
                        q++;
                    }
                }

            for(int i = 0; i < q; i++){
                System.out.println("Question: " + question[i] + " = " + answer[i] + " & level = " + level[i]);
            }
            } catch (FileNotFoundException e){
                System.out.println("Error: " + args[0] + " file not found!");
            }
        }
    }

    // method for game/test

    // method to read question input file

    // method to read test log file

    // method to add to log file

    // method to process high scores

    // method for test1

    // method for test2

    // method for test3

    // Timer stuff and time t
    // long start = System.currentTimeMillis();
    // long end = start + 60*1000; // 60 seconds * 1000 ms/sec
    // while (System.currentTimeMillis() < end)
    // {
    //    // run
    // }

}
