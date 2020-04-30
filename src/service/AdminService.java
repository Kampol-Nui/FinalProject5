package service;


import game.GameStatus;
import game.Game;


public interface AdminService {
    public abstract boolean addGame(AdminAccount admin, Game game);
    public abstract boolean removeGame(AdminAccount admin, Game game);
    public abstract boolean changeStatus(AdminAccount admin, String title, GameStatus status);
}
