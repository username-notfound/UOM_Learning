package naivebayes;
import java.util.*;
import javagently.*;

/**
 * Using a naive Bayes classifier to distinguish utterances of the word yes from the word no
 */

public class YesNoClassifier {

    public static void main(String[] args)
    {
	// Read in MFCC data

        String mfccDataDirectory = "data/yesno/mfcc/";
        Data yesData = new Data (mfccDataDirectory+"yes");
        Data noData = new Data (mfccDataDirectory+"no");

	// Build a naive Bayes classifier

        Classifier classifier = new Classifier(yesData.getMeanMfcc(),noData.getMeanMfcc(),0.5);
        Classifier classifier1 = new Classifier(yesData.getMeanMfcc(),noData.getMeanMfcc(),0.0);

	// Compute the probability of being in class one for the first yes example
	// using the 1st time-averaged MFCC as the feature

        int featureNumber = 0; // Using this MFCC component (0 is 1st component)
        int errorNumber = 0;

        for (int yes = 0; yes < yesData.getNumberExamples(); yes++)
        {
          double answerForYes = classifier.classify(yesData.getMeanMfcc(yes),featureNumber);
          if (answerForYes < 0.5)
          {
            errorNumber++;
          } // if
        } // for

        for (int no = 0; no < noData.getNumberExamples(); no++)
        {
          double answerForNo = classifier.classify(noData.getMeanMfcc(no),featureNumber);
          if (answerForNo >= 0.5)
          {
            errorNumber++;
          } // if
        } // for

        int exampleNumber = yesData.getNumberExamples()
                             + noData.getNumberExamples();
        double percentageOfErrors
                           = (double)errorNumber / (double)exampleNumber * 100;
        System.out.println("The percentage of errors is "+ percentageOfErrors
                           + "%");

        int errorNumberForYes = 0;
        int errorNumberForNo = 0;
         for (int yes = 0; yes < yesData.getNumberExamples(); yes++)
         {
           double answerForYes = classifier1.classify(yesData.getMeanMfcc(yes));
           if (answerForYes < 0.5)
           {
             errorNumberForYes++;
           } // if
         } // for

         for (int no = 0; no < noData.getNumberExamples(); no++)
         {
           double answerForNo = classifier1.classify(noData.getMeanMfcc(no));
           if (answerForNo >= 0.5)
           {
             errorNumberForNo++;
           } // if
         } // for
        int errorNumber2 = errorNumberForYes + errorNumberForNo;
        double percentageOfErrors2 = (double)errorNumber2 / (double)exampleNumber * 100;
        System.out.println("------------------");
        System.out.println("The number of answer for yes is " + errorNumberForYes);
        System.out.println("The number of answer for no is " + errorNumberForNo);
        System.out.println("The percentage of errors is "+ percentageOfErrors2
                           + "%");
        //System.out.println("The probability of being in class 1 using all the" +
                    //  " components of the feature vector " + classifier1);
    }
}
