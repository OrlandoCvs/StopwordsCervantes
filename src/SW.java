
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

    public class SW {
        public static void main(String[] args) throws IOException{

            if (args.length == 0) {
                System.out.println("Ingresa nombre del arvhivo");
                System.exit(0);
            }

            FileReader fi = null;
            FileReader stopword = null;
            try {
                fi = new FileReader(args[0]);
                stopword = new FileReader((args[1]));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
                System.exit(-1);
            }




            BufferedReader inputFile = new BufferedReader(fi);
            BufferedReader inputword = new BufferedReader(stopword);

            String textLine = null;

            int lineCount = 0;
            int wordCount = 0;
            int numberCount = 0;

            String delimiters = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                    + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*";
            Set<String> wordslist = new HashSet<>();
            String l;
            while ((l = inputword.readLine()) != null) {
                wordslist.add(l);
            }
            Set<String> list = new HashSet<>();
            long startTime = System.currentTimeMillis();
            try {
                while ((textLine = inputFile.readLine()) != null) {
                    lineCount++;

                    if (textLine.trim().length() == 0) {
                        continue;
                    }
                    String words[] = textLine.split( delimiters );

                    wordCount += words.length;

                    for (String theWord : words) {

                        theWord = theWord.toLowerCase().trim();

                        boolean isNumeric = true;
                        try {
                            Double num = Double.parseDouble(theWord);
                        } catch (NumberFormatException e) {
                            isNumeric = false;
                        }
                        if (isNumeric) {
                            numberCount++;
                            continue;
                        }
                        if ( !list.contains(theWord) ) {
                            list.add(theWord);
                        }
                    }
                }

                long tiempoEjecucion = System.currentTimeMillis() - startTime;
                inputFile.close();
                fi.close();

                System.out.printf("%2.3f  segundos, %,d lineas y %,d palabras\n",
                        tiempoEjecucion / 1000.00, lineCount, wordCount - numberCount);

                System.out.printf("%,d palabras diferentes\n", list.size());


            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            for(String li: list){
                for(String w: wordslist){
                    if(li != w){
                        System.out.println(li);
                    }
                }

            }



        }
    }
