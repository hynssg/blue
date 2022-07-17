package task1;



public class BookLost extends Lost{

   private String bookKey;

   public BookLost() {
   }

   public BookLost(int i, String ksk) {
      this.bookKey=ksk;
      super.time=i;
   }

   public String getBookKey() {
      return bookKey;
   }

   @Override
   public String toString() {
      return "BookLost{" +
              "bookKey='" + bookKey + '\'' +
              ", time=" + time +
              '}';
   }

}
