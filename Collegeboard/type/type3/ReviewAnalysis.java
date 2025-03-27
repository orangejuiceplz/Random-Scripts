public class ReviewAnalysis

{

/** All user reviews to be included in this analysis */

private Review[] allReviews;

 

/** Initializes allReviews to contain all the Review objects to be analyzed */

public ReviewAnalysis()

{ /* implementation not shown */ }

 

/** Returns a double representing the average rating of all the Review objects to be

* analyzed, as described in part (a)

* Precondition: allReviews contains at least one Review.

*     No element of allReviews is null.

*/

public double getAverageRating()

{ 

  int sum = 0;

  for (Review a: allReviews) {
    sum += a.getRating();
  }
  return (double) sum / allReviews.length;

}

 

/** Returns an ArrayList of String objects containing formatted versions of

* selected user comments, as described in part (b)

* Precondition: allReviews contains at least one Review.

*     No element of allReviews is null.

* Postcondition: allReviews is unchanged.

*/

public ArrayList<String> collectComments()

{

  ArrayList<String> commentList = new ArrayList<>();

  for (int i = 0; i < allReviews.length; i++) {
      String c = allReviews[i].getComment();
      if (c.indexOf("!" >= 0) {
          String l = c.substring(c.length() - 1);
          if (!l.equals("!") && !l.equals(".")) {
            c += ".";
          }
          commentList.add(i + "-" _ c);
      }
  }
}

}

