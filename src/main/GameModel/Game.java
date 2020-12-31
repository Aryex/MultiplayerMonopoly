package GameModel;

import UI.GameBoardUi;

import java.util.*;


/**
 * GameModel.Game model for monopoly. Designed with a server-client architecture where it is the server
 * expecting some UI client to connect to it and handle UI/displaying information.
 */
public class Game {

    public interface Event {
        void playerLandedOnEmptyTile();

        void playerLandedOnBoughtTile();

        void playerLandedOnEffectTile();

        void playerBuyTile();

        void playerAuctionTile();
    }

    private static final int STREET_NUM = 40;
    GameBoardUi ui;
    List<Player> playerList;
    int playerCount = 0;
    int currentPlayer = 0;
    private int[] diceValue = new int[2];

    final Bank BANK;

    List<Property> properties;

    public Game() {
        playerList = new ArrayList<Player>();
        properties = new ArrayList<Property>();
        BANK = new Bank();
//        Generate list of Tiles
        properties.add(new Go());
        properties.add(new StreetProperty("South Marpole", 60,2, 1));
        properties.add(new CommunityChest());
        properties.add(new StreetProperty("East Hasting", 60,4, 1));
        properties.add(new Tax());
        properties.add(new Railroad("Burrad Station", 200));
        properties.add(new StreetProperty("China Town", 100, 6, 2));

    }

    public void addPlayer(String name) {
        playerList.add(new Player(name, playerCount));
        playerCount++;
    }

    public void endTurn() {
        currentPlayer = (currentPlayer + 1) % playerCount;
    }

    public int roll(Event event) {
        diceValue[0] = diceRoll();
        diceValue[1] = diceRoll();
        int moveValue = diceValue[0] + diceValue[1];
        Player player = playerList.get(currentPlayer);
        player.moveBy(moveValue);
        /*
         * If player.pos tile
         *   event.playerLandedOnEmptyTile()
         * if player landed on card tile
         *   invoke card effects.
         *   even.playerLandedOnCardTile(cardId)
         * if Player landed on bought tile()
         *   player balance is handled
         *   event.playerLandedOnBoughtTile(tile.rentAmount);
         *
         * */
        return currentPlayer;
    }

    public void buyTile(int playerId) {
//        Current player buy the current position
    }

    public void payPlayer(int fromPlayer, int toPlayer) {
//        Player transfer money to each other.
    }

    public void getStipends() {
// current player get $200 from bank
    }

    public void bankPaysPlayer(int amount) {
//        Bank give player the amount.
    }

    public int getDice1() {
        return diceValue[0];
    }

    public int getDice2() {
        return diceValue[1];
    }

    private class Property {

        final String name;
        final int price;
        final int rentAmount;
        private Owner owner = BANK;

        protected Property(String name, int price, int rentAmount) {
            this.name = name;
            this.price = price;
            this.rentAmount = rentAmount;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public Owner getOwner() {
            return owner;
        }

        public int getRentAmount() {
            return rentAmount;
        }

        public int getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Property property = (Property) o;

            return Objects.equals(name, property.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    class StreetProperty extends Property {
        final int rank;
        int houses = 0;
        boolean hotel = false;
        public StreetProperty(String name, int price, int rentAmount, int rank) {
            super(name, price, rentAmount);
            if(rank < 1 || rank > 8) throw new RuntimeException("Invalid streetProperty rank");
            this.rank = rank;
        }
    }

    class Railroad extends Property {
        public Railroad(String name, int price) {
            super(name, price, 25);
        }
    }

    class Ultilities extends Property {
        public Ultilities(String name, int price) {
            super(name, price, 0);
        }
    }

    class Tax extends Property {
        public Tax() {
            super("Income Tax", 0, 200);
        }
    }

    class CommunityChest extends Property {
        public CommunityChest() {
            super("Community Chest", 0, 0);
        }
    }

    class Chance extends Property {
        public Chance() {
            super("Community Chest", 0, 0);
        }
    }

    class Go extends Property {
        public Go() {
            super("GO", 0, 0);
        }
    }

    class Jail extends Property {
        public Jail() {
            super("Jail", 0, 0);
        }
    }

    class GotoJail extends Property {
        public GotoJail() {
            super("Goto Jail", 0, 0);
        }
    }

    class Parking extends Property {
        public Parking() {
            super("Free Parking", 0, 0);
        }
    }

    private class Owner {
        List<Property> properties;
        protected int balance;

        protected Owner(int balance) {
            this.balance = balance;
            properties = new ArrayList<>();
        }

        public int getBalance() {
            return balance;
        }

        public void addProperty(Property property) {
            properties.add(property);
        }

        public Property removeProperty(Property property) {
            return properties.remove(properties.indexOf(property));
        }



    }

    class Player extends Owner {
        final String name;
        final int id;
        int position;

        public Player(String name, int id) {
            super(0);
            this.name = name;
            this.id = id;
            this.position = 0;
        }

        public void moveBy(int move) {
            position = (position + move) % STREET_NUM;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }


        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return name +
                    " - id=" + id;
        }
    }

    public class Bank extends Owner {
        public Bank() {
            super(0);
        }

    }

    //


    public static int diceRoll() {
        return new Random().nextInt(6) + 1;
    }
}






