package naivebayes;

/** A two class naive Bayes classifier. This class stores the prior probability
 * of each class, p(C1) and p(C2), and the conditional probabilities p(x|C1) and p(x|C2) which are modelled as
 * normal densities for each feature vector component. The constructor method fits the conditional probabilities
 * by setting the mean and variance of these equal to the empirical mean and variance of data from each
 * class.
 */

public class Classifier {

    private double priorClass1; // p(C1) - prior probability for Class 1
    private double priorClass2; // p(C2) - prior probability for Class 2
    private Normal[] pxGivenClass1; // p(x|C1) for each feature dimension
    private Normal[] pxGivenClass2; // p(x|C2) for each feature dimension
    private int dimensions; // Dimension of feature vector

    /**
     * This constructor method fits the parameters of two normal densities
     * and stores the priors for each class
     */

    public Classifier (double[][] featureClass1, double[][] featureClass2, double pC1) {
        priorClass1 = pC1;
        priorClass2 = 1.0 - pC1;  // The prior probabilities for each class must sum to one
        dimensions = featureClass1.length;

	// Fit a normal density for each feature dimension

        pxGivenClass1 = new Normal[dimensions];
        pxGivenClass2 = new Normal[dimensions];
        for (int i=0; i<dimensions; i++){
            pxGivenClass1[i] = new Normal(featureClass1[i]);
            pxGivenClass2[i] = new Normal(featureClass2[i]);
        }
    }

    public Classifier (double[][] featureClass1, double[][] featureClass2) {


      dimensions = featureClass1.length;
  // Fit a normal density for each feature dimension

        pxGivenClass1 = new Normal[dimensions];
        pxGivenClass2 = new Normal[dimensions];
        for (int i=0; i<dimensions; i++){
            pxGivenClass1[i] = new Normal(featureClass1[i]);
            pxGivenClass2[i] = new Normal(featureClass2[i]);
        }

        priorClass1 = (double)featureClass1[0]/ (double) (featureClass1[0] + featureClass2[0]);
        priorClass2 = 1.0 - priorClass1;
    }


    /**
     * This method returns the probability of being in class 1 using only data from one feature
     * which is in featureVector[featureNo]
     */

    public double classify (double[] featureVector, int featureNo) {

	// Numerator of Bayes' theorem as given in the Lecture notes
        double numerator = pxGivenClass1[featureNo].density(featureVector[featureNo])* priorClass1;

	// Denominator of Bayes' theorem
        double denominator = numerator + pxGivenClass2[featureNo].density(featureVector[featureNo])*priorClass2;

        return numerator/denominator;
    }


    /**
     * This method should be modified in order to return the probability of being in class 1 using all the
     * components of the feature vector
     */

    double class1 = 1.0;
    double class2 = 1.0;
    public double classify (double[] featureVector) {
      for(int count = 0; count < this.dimensions; count++)
      {
        class1 = pxGivenClass1[count].density(featureVector[count]) * class1;
        class2 = pxGivenClass2[count].density(featureVector[count]) * class2;
      }
	// Your code should go here
        double numerator = class1 * priorClass1;
        double denominator = numerator + class2 * priorClass2;
        return numerator / denominator;

    }
}
