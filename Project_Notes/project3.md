# Design Document Discussion

maybe this will help? https://arxiv.org/pdf/1011.0935.pdf

* Exact Class
    * algorithm
    * test records


* Approximate Class
    * algorithm - gibbs sampling/variable elimination
    * test records


* Main Class
    * store results - results from each algorithm on each networ (10 results)
    * initialize different parameters


* Reader Class
    * read in networks by common data structure
        * reading if file
        * storing it into data structure
            * variables and probability separately


* Helper Function Class
    * extra variables




UML Diagram
Reader class reads in BIF files, reads in test samples, and translates the data for Bayes Network. THe files are split into variables and probability

Bayes Net is the data structure(I think) for storing the sample data. The sample data has three parameters, report, little evidence, and moderate evidence.


The exact inference uses the variable elimination algorithm. (Not sure what for just yet.) The approximate inference uses the Gibbs Sampling algorithm. Both approaches use two of the same functions. The getSamples() function will retrieve the samples from Bayes Network. The whole point of this project is calculating Marginal Distribution, so there is a calculateMarginalDistribution() function to calculate the marginal distribution for each algorithm.

The exact class will use the ELIMINATION-ASK algorithm from the book.

The approximate class will use the GIBBS-ASK algorithm from the book.

Finally we have of main/driver class, where we store the results from running each algorithm from each network. Each network is calculated using siz different properties: 
1. Number of Nodes
2. Number of Edges
3. Number of Parameters
4. Average Degree
5. Maximum Number of Parents
6. Average Markov Blanket Size

The functions in the driver class will be the reportCalculations() function, which is of the two algorithms which was better, for what network was the algorithm better. And reportAlgorithm() function is the results from each algorithm calculation.


Here is a python example of the Bayesian Belief Network https://towardsdatascience.com/bbn-bayesian-belief-networks-how-to-build-them-effectively-in-python-6b7f93435bba

