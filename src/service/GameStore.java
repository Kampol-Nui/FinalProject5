package service;


import game.GameStatus;
import game.Game;
import java.util.ArrayList;
import java.util.Objects;


public class GameStore implements AdminService{


    private ArrayList<Game> gameStore;
    private GameStatus gamestatus;
    private String name;

    public GameStore(String name) {
        this.name = name;
        gameStore = new ArrayList<>();

    }

    public boolean listGameFromStore() {
        System.out.println("************************ เกมในร้านค้า " + this + " *************************");
        if (this.gameStore.isEmpty()) {
            System.out.println("ร้านค้ายังไม่มีเกม");
            return false;
        }
        for (Game game : gameStore) {
            System.out.print(game + "\t");
            System.out.println("Index is " + gameStore.indexOf(game));
        }
        System.out.println("------------------------------------------------------------------------------");
        return true;
    }

    public ArrayList<Game> getGames() {
        return gameStore;
    }

    @Override
    public boolean addGame(AdminAccount admin, Game game) {
        try {
            Objects.requireNonNull(admin, "AdminAccount cannot be null");
            if (game == null) {
                System.out.println("โปรดป้อนข้อมูลที่ไม่ใช่ Null");
                return false;
            }
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).equals(game)) {
                    System.out.println("มีเกมนี้อยู่แล้วบนร้านค้า "+this.name);
                    return false;
                }
            }
            game.setStatus(GameStatus.ONSALED);
            this.gameStore.add(game);
            System.out.println("คุณได้เพิ่มเกม "+game.getTitle()+" ไปยังร้านค้า "+this.name+"");
            return true;
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public boolean changeStatus(AdminAccount admin, String title, GameStatus status) {
        try {
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).getTitle().equals(title)) {
                    this.gameStore.get(i).setStatus(status);
                    return true;
                }

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("not found the game to add");
        return false;
    }

    @Override
    public boolean removeGame(AdminAccount admin, Game game) {
        try {
            Objects.requireNonNull(admin, "AdminAccount cannot be null");
            if (game == null) {
                System.out.println("Game is Not Null");
                return false;
            }
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).equals(game)) {
                    this.gameStore.remove(game);
                    System.out.println("Successfully Removed");
                    return true;
                }

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("Not found game to remove");
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

}
