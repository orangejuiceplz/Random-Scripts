public class ReviewCollector

{

private ArrayList<ProductReview> reviewList;

private ArrayList<String> productList;

  /** Constructs a ReviewCollector object and initializes the instance variables. */

public ReviewCollector()

{

reviewList = new ArrayList<ProductReview>();

productList = new ArrayList<String>();

}

  /** Adds a new review to the collection of reviews, as described in part (a). */

public void addReview(ProductReview prodReview) {
	reviewList.add(prodReview);
    String name = productReview.getName();
    for (int i = 0; i < productList.size(); i++) {
    	if (!productList.get(i).contains(name) {
        	productList.add(name);
        }
    }
}

  /** Returns the number of good reviews for a given product name, as described in part (b). */

public int getNumGoodReviews(String prodName) {
	int goodReviews = 0;
    for (int i = 0; i < reviewList.size(); i++) {
    	if (reviewList.get(i).getName().equals(prodName) {
        	if (reviewList.get(i).contains("best") {
            	goodReviews++;
            }
         }
    }
    
   return goodReviews;
  }
}
