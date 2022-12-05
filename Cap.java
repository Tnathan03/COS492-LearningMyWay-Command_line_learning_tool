// Program by Nathan Taylor

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Cap {
    public int x;

    // main that starts game/test
    public static void main(String[] args) {

        if (args.length != 2) { // testing command line args
            System.out.println("Input error");
            System.exit(0);
        } else {
            try {
                //System.out.println("\nLoading.... (This might take a minute)");
                Scanner inFile = new Scanner(new File(args[0]));
                String f = args[1];
                String[] question = new String[100];
                String[] answer = new String[100];
                int q = 0;
                while (inFile.hasNext()) {
                    String[] tokens = inFile.nextLine().split(";");
                    for (String temp : tokens) {
                        // System.out.println(tokens[i++]);
                        String[] t = temp.split("[|]", 0);

                        for (int j = 1; j <= t.length; j++) {
                            if (j % 2 == 1) {
                                question[q] = t[j - 1].trim();
                            }
                            if (j % 2 == 0) {
                                answer[q] = t[j - 1].trim().toLowerCase();
                            }
                        }
                        q++;
                    }
                }


                Cap test = new Cap();
                test.setX(q);

                System.out.println("\n_____________________________________________________________________\n");
                test.runGame(question, answer,f);

                inFile.close();
            } catch (FileNotFoundException e){
                System.out.println("Error: " + args[0] + " file not found!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void setX(int c){
        x = c;
    }

    public int getX(){
        return x;
    }

    // method for game/test
    public void runGame(String[] question, String[] answer,String log) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);
        System.out.println("Enter your name: ");
        String name = keyboard.nextLine();
        System.out.println("\n_____________________________________________________________________\n");
        System.out.println("Select one of the options:");
        System.out.println("1) Multiple Choice");
        System.out.println("2) Match The Following");
        System.out.println("3) True/False");
        System.out.println("4) Evaluate");
        System.out.println("5) View High Scores");
        System.out.println("6) View Game History");
        System.out.println("7) Exit");
        System.out.println("\n_____________________________________________________________________\n");
        System.out.println("Enter the number you want to select: ");
        try {
            int selection = Integer.parseInt(keyboard.next());
            String type;
            String score;
            if (selection == 1) {
                type = "Multiple Choice";
                score = Test1(question, answer);
                addLogEntry(name, type, score, time);
            }
            if (selection == 2) {
                type = "Match the following";
                score = Test2(question, answer);
                addLogEntry(name, type, score, time);
            }
            if (selection == 3) {
                type = "True/False";
                score = Test3(question, answer);
                addLogEntry(name, type, score, time);
            }
            if (selection == 4) {
                type = "Evaluate";
                score = Test4(question, answer);
                addLogEntry(name, type, score, time);
            }
            if(selection == 5){
                readLogHighScore(log);
            }
            if (selection == 6) {
                readLogFile(log);
            }
            if (selection == 7) {
                System.out.println("\n_____________________________________________________________________\n");
                System.out.println("Thank you for playing " + name + "!");
                System.out.println("\n_____________________________________________________________________\n");
                System.exit(0);
            }
        } catch (NumberFormatException e){
            System.out.println("Error! - Must enter a valid selection");
        }
    }
    // method to read test log file
    public void readLogFile(String log) throws FileNotFoundException {
        Scanner logFile = new Scanner(new File(log));
        int q = 0;
        String[] name = new String[100];
        String[] type = new String[100];
        String[] score = new String[100];
        String[] time = new String[100];
        while (logFile.hasNext()) {
            String[] tokens = logFile.nextLine().split(";");
            for (String temp : tokens) {
                // System.out.println(tokens[i++]);
                String[] t = temp.split("[|]", 0);

                for (int j = 1; j <= t.length; j++) {
                    if (j % 4 == 1) {
                        name[q] = t[j - 1].trim();
                    }
                    if (j % 4 == 2) {
                        type[q] = t[j - 1].trim().toLowerCase();
                    }
                    if (j % 4 == 3) {
                        score[q] = (t[j - 1].trim());
                    }
                    if (j % 4 == 0) {
                        time[q] = (t[j - 1].trim());
                    }
                }
                q++;
            }
        }
        System.out.println("\n____________________________Log History______________________________\n");
        for(int i = 0; i < q; i++){
            System.out.println("Name: " + name[i]  + " | Score: " + score[i] + " | Test: " + type[i] + " | Time: " + time[i]);
        }
        System.out.println("\n_____________________________________________________________________\n");
    }

    public void readLogHighScore(String log) throws FileNotFoundException {
        Scanner logFile = new Scanner(new File(log));
        int q = 0;
        String[] name = new String[100];
        String[] type = new String[100];
        String[] score = new String[100];
        String[] time = new String[100];
        while (logFile.hasNext()) {
            String[] tokens = logFile.nextLine().split(";");
            for (String temp : tokens) {
                // System.out.println(tokens[i++]);
                String[] t = temp.split("[|]", 0);

                for (int j = 1; j <= t.length; j++) {
                    if (j % 4 == 1) {
                        name[q] = t[j - 1].trim();
                    }
                    if (j % 4 == 2) {
                        type[q] = t[j - 1].trim().toLowerCase();
                    }
                    if (j % 4 == 3) {
                        score[q] = (t[j - 1].trim());
                    }
                    if (j % 4 == 0) {
                        time[q] = (t[j - 1].trim());
                    }
                }
                q++;
            }
        }
        System.out.println("\n____________________________Top 5 Scores_____________________________\n");
        for(int j = 0; j < 5; j++) {
            int tem = 0;
            float max = (float)1.0;
            for (int i = 0; i < q; i++) {
                float n = Float.parseFloat(score[i].replace("%",""));
                if (n > max) {
                    max = n;
                    tem = i;
                }
            }
            System.out.println("Name: " + name[tem]  + " | Score: " + score[tem] + " | Test: " + type[tem] + " | Time: " + time[tem]);
            score[tem] = "1.0";
        }
        System.out.println("\n_____________________________________________________________________\n");


    }


    // method to add to log file
    public void addLogEntry(String name, String type,String score, String time) throws IOException {

        String temp = name + "|" + type +"|"+score+"%|" + time+";";
        FileWriter log = new FileWriter("logs.txt",true);
        log.write(temp+"\r\n");
        log.close();
    }
    // method to process high scores



    // method for test1 - mcq (multiple choice)
    public String Test1(String[] question, String[] answer){

        Scanner keyboard = new Scanner(System.in);
        int correct = 0;
        String[] mcq = new String[4];
        Random rand = new Random();

        for(int i = 0; i < getX(); i++){
            System.out.println("\n_____________________________________________________________________\n");
            System.out.println("Question: " + question[i] + " = ?");
            int unique1 = rand.nextInt(4);
            mcq[unique1] = answer[i];
            if(unique1 == 0){
                mcq[1] = answer[rand.nextInt(getX())];
                mcq[2] = answer[rand.nextInt(getX())];
                mcq[3] = answer[rand.nextInt(getX())];
            }
            if(unique1 == 1){
                mcq[0] = answer[rand.nextInt(getX())];
                mcq[2] = answer[rand.nextInt(getX())];
                mcq[3] = answer[rand.nextInt(getX())];
            }
            if(unique1 == 2){
                mcq[0] = answer[rand.nextInt(getX())];
                mcq[1] = answer[rand.nextInt(getX())];
                mcq[3] = answer[rand.nextInt(getX())];
            }
            if(unique1 == 3){
                mcq[0] = answer[rand.nextInt(getX())];
                mcq[1] = answer[rand.nextInt(getX())];
                mcq[2] = answer[rand.nextInt(getX())];
            }
            System.out.println("Pick the correct option: ");
            for(int j = 0; j < 4; j++){
                System.out.println((j+1)+") " +mcq[j]);
            }
            System.out.println("\n_____________________________________________________________________\n");
            System.out.print("Enter the number for your answer: ");

            int x2 = Integer.parseInt(keyboard.next().trim());
            String x;
            if(x2 > 4 || x2 < 1){
                x = " n/a ";
            } else {
                x = mcq[x2-1];}
            System.out.println("\n_____________________________________________________________________\n");
            if(x.equals(answer[i])){
                System.out.println("Correct - You answered: " + x + "  |  correct answer: " + answer[i]);
                correct++;
            } else {
                System.out.println("Incorrect - You answered: " + x + "  |  correct answer: " + answer[i]);}

        }
        System.out.println("\n_____________________________________________________________________\n");
        String score = ""+((float)correct/(float)getX())*100;

        System.out.println("Your Score: "+correct+"/"+getX() +" -> " +((float)correct/(float)getX())*100+"%" );
        System.out.println("\n_____________________________________________________________________\n");
        keyboard.close();
        return score;
    }

    // method for test2 - match the following
    public String Test2(String[] question, String[] answer){

        Scanner keyboard = new Scanner(System.in);

        int[] array = {0,1,2,3,4,5,6,7,8,9};

        Random rand = ThreadLocalRandom.current();
        for(int i = array.length - 1; i > 0 ; i--){
            int index = rand.nextInt(i + 1);
            int x = array[index];
            array[index] = array[i];
            array[i] = x;
        }
        String[] stringTemp = {"A","B","C","D","E","F","G","H","I","J"};
        System.out.println("_______________________________Questions_______________________________\n");
        for(int i = 0; i < 10;i++){
            System.out.println((i+1) + ") " + question[i] + " = ?");
        }
            System.out.println("_______________________________Answers_______________________________\n");
        for(int i = 0; i < 10;i++){
            System.out.print(stringTemp[i] + ")  " + answer[array[i]] + " | ");
        }
        System.out.println("\n_____________________________________________________________________\n");
        System.out.println("Enter the correct order of answers separated by commas (ex. A,E,D,F,J...): ");
        String temp = keyboard.nextLine();
        int correct = 0;
        String[] tokens = temp.split(",");
        String tmp = "";
        for(int i = 0; i < tokens.length; i++) {
            if (tokens[i].trim().equalsIgnoreCase("a")) {
                tmp = answer[array[0]];
            }
            if (tokens[i].trim().equalsIgnoreCase("b")) {
                tmp = answer[array[1]];
            }
            if (tokens[i].trim().equalsIgnoreCase("c")) {
                tmp = answer[array[2]];
            }
            if (tokens[i].trim().equalsIgnoreCase("d")) {
                tmp = answer[array[3]];
            }
            if (tokens[i].trim().equalsIgnoreCase("e")) {
                tmp = answer[array[4]];
            }
            if (tokens[i].trim().equalsIgnoreCase("f")) {
                tmp = answer[array[5]];
            }
            if (tokens[i].trim().equalsIgnoreCase("g")) {
                tmp = answer[array[6]];
            }
            if (tokens[i].trim().equalsIgnoreCase("h")) {
                tmp = answer[array[7]];
            }
            if (tokens[i].trim().equalsIgnoreCase("i")) {
                tmp = answer[array[8]];
            }
            if (tokens[i].trim().equalsIgnoreCase("j")) {
                tmp = answer[array[9]];
            }

            if (tmp.equals(answer[i])) {
                correct++;
            }
        }
        System.out.println("\n_____________________________________________________________________\n");
        String score = ""+((float)correct/(float)getX())*100;
        System.out.println("Your Score: "+correct+"/"+getX() +" -> " +((float)correct/(float)getX())*100+"%" );
        System.out.println("\n_____________________________________________________________________\n");
        keyboard.close();
        return score;
    }

    // method for test3 - true/false
    public String Test3(String[] question, String[] answer){
        Random rand = new Random();
        int correct = 0;
        Scanner keyboard = new Scanner(System.in);
        for(int i = 0; i < getX(); i++){
            int temp = rand.nextInt(2);
            String tempAnswer;
            String bool;
            System.out.println("\n_____________________________________________________________________\n");
            if(temp == 1) {
                tempAnswer = answer[i];
                bool = "true";
            } else {
                if(i < getX()-1){
                    tempAnswer = answer[i+1];
                } else {
                    tempAnswer = answer[i-1];
                }
                bool = "false";
            }
            System.out.println("Question: " + question[i] + " = " +tempAnswer);
            System.out.println("\n_____________________________________________________________________\n");
            System.out.print("Enter True/False: ");

            String x = keyboard.next().trim().toLowerCase();
            System.out.println("\n_____________________________________________________________________\n");
            if(x.equals(bool)){
                System.out.println("Correct - You answered: " + x + "  |  correct answer: " + bool);
                correct++;
            } else {
                System.out.println("Incorrect - You answered: " + x + "  |  correct answer: " + bool);}
        }

        System.out.println("\n_____________________________________________________________________\n");
        String score = ""+((float)correct/(float)getX())*100;
        System.out.println("Your Score: "+correct+"/"+getX() +" -> " +((float)correct/(float)getX())*100+"%" );
        System.out.println("\n_____________________________________________________________________\n");
        keyboard.close();
        return score;

    }


    // method for test4 - enter the answer
    public String Test4(String[] question, String[] answer){
        int correct = 0;
        Scanner keyboard = new Scanner(System.in); // maybe do a thing for how many questions:
        for(int i = 0; i < getX(); i++){
            System.out.println("\n_____________________________________________________________________\n");
            System.out.println("Question: " + question[i] + " = ?");
            System.out.println("\n_____________________________________________________________________\n");
            System.out.print("Enter your answer: ");

            String x = keyboard.next().trim().toLowerCase();
            System.out.println("\n_____________________________________________________________________\n");
            if(x.equals(answer[i])){
                System.out.println("Correct - You answered: " + x + "  |  correct answer: " + answer[i]);
                correct++;
            } else {
                System.out.println("Incorrect - You answered: " + x + "  |  correct answer: " + answer[i]);}
        }

        String score = ""+((float)correct/(float)getX())*100;
        System.out.println("\n_____________________________________________________________________\n");
        System.out.println("Your Score: "+correct+"/"+getX() +" -> " +((float)correct/(float)getX())*100+"%" );
        System.out.println("\n_____________________________________________________________________\n");
        keyboard.close();
        return score;
    }

    // rn it does one file but this can be easily changed to make it take multiple files, so we can study various things at once
}
