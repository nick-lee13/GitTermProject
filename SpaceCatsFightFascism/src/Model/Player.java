public class Player {

    Cat cat;
    Deck deck;
    int playerNum;
    int actions;
    
    public Player(int playerNum, Cat cat) {
            this.cat = cat;
            deck = new Deck(this);
            this.playerNum = playerNum;
            
    }


    /*public int getInput(int action, Object subject) {
            return 1;
    }*/

    public Cat getCat() {
        return cat;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck newDeck){
        deck = newDeck;
    }

    public int getPlayerNum(){
        return playerNum;
    }

    public void setCat(Cat inCat) {
        cat = inCat;
    }
}
