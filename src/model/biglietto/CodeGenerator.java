package model.biglietto;

import java.util.Random;



public class CodeGenerator {
  private static int last;
  
  public CodeGenerator(int last) {
    CodeGenerator.last = last;
  }

  

  private static Random rand = new Random();
  private static String[] caratteri = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "y",
    "j", "k", "x", "w", "A", "B", "C", "D", "E", "F", "G", "H", "I",
    "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "Y",
    "J", "K", "X", "W"};

  /**
   * Ottieni un codice.
   */
  public synchronized String nextCode() {

    int one = rand.nextInt(caratteri.length - 1);
    int two = rand.nextInt(caratteri.length - 1);
    int three = rand.nextInt(caratteri.length - 1);
    int four = rand.nextInt(caratteri.length - 1);
    int number = last++;
    
    String numbers = "";
    if (number < 10) {
      numbers = "000" + number;
    } else if (number < 100) {
      numbers = "00" + number;
    } else if (number < 1000) {
      numbers = "0" + number;
    } else if (number < 10000) {
      numbers = "" + number;
    }
    
    int nine = rand.nextInt(caratteri.length - 1);
    int ten = rand.nextInt(caratteri.length - 1);
    int eleven = rand.nextInt(caratteri.length - 1);
    int twelve = rand.nextInt(caratteri.length - 1);

    return caratteri[one] + caratteri[two] + caratteri[three] + caratteri[four] + numbers
      + caratteri[nine] + caratteri[ten] + caratteri[eleven] + caratteri[twelve];
  }

  
}
