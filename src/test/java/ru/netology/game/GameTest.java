package ru.netology.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exeptions.NotRegisteredException;
import ru.netology.player.Player;

import java.util.ArrayList;

public class GameTest {
    Game game = new Game();

    Player player1 = new Player(1, "Василий", 5);
    Player player2 = new Player(2, "Ольга", 8);
    Player player3 = new Player(3, "Виталий", 5);
    Player player4 = new Player(4, "Мария", 6);

    @Test
    public void shouldRegisterPlayer() {

        game.register(player2);

        boolean expected = true;
        boolean actual = game.getPlayers().contains(player2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNobodyWin() {

        game.register(player1);
        game.register(player3);

        int expected = 0;
        int actual = game.round("Василий", "Виталий");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFirstPlayerWin() {

        game.register(player2);
        game.register(player3);

        int expected = 1;
        int actual = game.round("Ольга", "Виталий");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSecondPlayerWin() {

        game.register(player1);
        game.register(player2);

        int expected = 2;
        int actual = game.round("Василий", "Ольга");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotRegisteredExceptionPlayer1() {

        game.register(player1);
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Юлия", "Ольга");
        });
    }

    @Test
    public void shouldNotRegisteredExceptionPlayer2() {

        game.register(player2);
        game.register(player4);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Ольга", "Федор");
        });
    }
}
