package task1;



public class CardLost extends Lost{

    private String cardKey;

    public CardLost() {
    }

    public CardLost(int i,String cardKey) {
        super.time=i;
        this.cardKey = cardKey;
    }

    public String getCardKey() {
        return cardKey;
    }

    @Override
    public String toString() {
        return "CardLost{" +
                "cardKey='" + cardKey + '\'' +
                ", time=" + time +
                '}';
    }


}
