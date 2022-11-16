public class Anagrams
{
  // Program to output all the permutations of a String given as
  // commond line argument
  private static char[] originalPermutations;
  private static char[] currentString;
  private static boolean[] recordCharacters;

  public static void main(String[] args)
  {
    originalPermutations = args[0].toCharArray();
    currentString = args[0].toCharArray();
    // to build current permutations and another
    recordCharacters = new boolean[(originalPermutations.length)];
    for (int index = 0; index < originalPermutations.length; index++)
      recordCharacters[index] = false;
    // Call the method to output
    printPermutations(0);

  } // main

  public static void printPermutations(int currentIndex)
  {
    // if currentIndex has gone past the end of the permutations array
    // print out the permutations
    if (currentIndex == originalPermutations.length)
    {
      // it is the original permutation, i.e. the commond line argument
      System.out.println(currentString);
    } // if

    for (int index = 0; index < originalPermutations.length; index++)
    {
      if(!recordCharacters[index])
      {
        // mark the character is being use
        recordCharacters[index] = true;
        currentString[currentIndex] = originalPermutations[index];
        printPermutations(currentIndex + 1);
        recordCharacters[index] = false;
      } // if
    } // for
  } // printPermutations
} // Anagrams
