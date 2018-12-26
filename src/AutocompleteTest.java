import java.util.ArrayList;
import java.util.List;

public class AutocompleteTest {

   public static void main(String[] args) {
      
      String[] terms = {"ape", "app", "ban", "bat", "bee", "car", "cat"};
      double[] weights = {6, 4, 2, 3, 5, 7, 1};
      
      // populate the trie using the add function. results of topMatch and topMatches 
      // imply correctness of add function.
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weights);
      
   
      
      // topMatch test. checks the result of the topMatch function against the given prefix query
      // and ensures that it matches the intended result. provides a success message if the function
      // works as intended and a failure message if it does not.
      System.out.println("Beginning topMatch test: \n");
      
      String[] prefixArray = {"", "a", "ap", "b", "ba", "c", "ca", "cat", "d", " "};
      String[] resultArray = {"car", "ape", "ape", "bee", "bat", "car", "car", "cat", "", ""};
      int s = 0;
      int f = 0;
      
      for (int i = 0; i < prefixArray.length; i++) {
         String prefix = prefixArray[i];
         String result = trie.topMatch(prefix);
         
         if (result.equals(resultArray[i])) { // check if result of topMatch equals index in given result array
            System.out.println("Check " + (i + 1) + ": Success!");
            s++;
         }   
         else {
            System.out.println("Check " + (i + 1) + ": FAILURE.");
            f++;
         }
      }
      System.out.println("\nNumber of successes: " + s);
      System.out.println("Number of failures: " + f);
      
      // topMatches test. checks the result of the topMatches function against the given prefix query
      // and k value and ensures that it matches the intended result. in each iteration, provides a 
      // success message if the function works as intended and a failure message if it does not.
      System.out.println("\nBeginning topMatches test: \n");
      
      String[] prefixArray2 = {"", "", "", "", "a", "ap", "b", "ba", "d"};
      // an ArrayList of arrays - each array holds one set of words to be compared in each iteration
      ArrayList<String[]> resultArray2 = new ArrayList<String[]>();
      resultArray2.add(new String[]{"car", "ape", "bee", "app", "bat", "ban", "cat"});
      resultArray2.add(new String[]{"car"});
      resultArray2.add(new String[]{"car", "ape"});
      resultArray2.add(new String[]{"car", "ape", "bee"});
      resultArray2.add(new String[]{"ape"});
      resultArray2.add(new String[]{"ape"});
      resultArray2.add(new String[]{"bee", "bat"});
      resultArray2.add(new String[]{"bat", "ban"});
      resultArray2.add(new String[]{});
      
      int[] kArray = {8, 1, 2, 3, 1, 1, 2, 2, 100};
      s = 0;
      f = 0;
      boolean failed = false;
      
      for (int i = 0; i < prefixArray2.length; i++) {
         String prefix = prefixArray2[i];
         Iterable<String> result = trie.topMatches(prefix, kArray[i]);
         
         //convert Iterable<String> to ArrayList
         ArrayList<String> list = new ArrayList<String>();
         if (result != null) {
            for (String str : result) {
               list.add(str);
            }
         }
         
         String[] finalPrefixArray = new String[list.size()];
         finalPrefixArray = list.toArray(finalPrefixArray); // convert ArrayList to array
         String[] finalResultArray = resultArray2.get(i);
         
         if (finalPrefixArray.length != finalResultArray.length) { // if numbers not equal, make false
            failed = true;
         }
         
         for (int j = 0; j < finalPrefixArray.length; j++) { 
            if (!finalPrefixArray[j].equals(finalResultArray[j])) { // if there's a mismatch, fail
               failed = true;
            }
         }
         
         if (!failed) { // check if result of topMatches equals index in given result array
            System.out.println("Check " + (i + 1) + ": Success!");
            s++;
         }   
         else {
            System.out.println("Check " + (i + 1) + ": FAILURE.");
            f++;
         }
      }
      System.out.println("\nNumber of successes: " + s);
      System.out.println("Number of failures: " + f);
   }

}