public class BookTester {
    public static void main(String[] args) {
        // Create a new Book object using the first constructor
        Book book1 = new Book("If I Did It: Confessions of the Killer", "O. J. Simpson", 316);
       
        // Prints all the detailS of the booK
        
        book1.printDetails();
       
        // Prints Title 'by' Author
        System.out.println(book1.getShortDescription());
       
        // Prints if book is long or short. (Pages>300)
        System.out.println(book1.isLong());

        // Create a new Book object using the second constructor
        Book book2 = new Book("Minecraft Essential Handbook", "Mojang");
       
        book2.printDetails();
       
        System.out.println(book2.getShortDescription());
        
        System.out.println(book2.isLong());
        
        // Lines 19-23 do the same as lines 8-14
    }
}
