package controller;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.result.GameResult;
import view.OutputView;

import java.util.Map;

public class ResultController {

    private final OutputView outputView;
    private GameResult gameResult;

    public ResultController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void run(final Dealer dealer, final Players players) {
        gameResult = new GameResult(dealer, players);
        printCardAndScore(dealer, players);
        printGameResult(players);
    }

    private void printCardAndScore(final Dealer dealer, final Players players) {
        printDealerCardAndScore(dealer);
        printPlayersCardAndScore(players);
    }

    private void printDealerCardAndScore(final Dealer dealer) {
        outputView.printDealerCardAndScore(dealer);
    }

    private void printPlayersCardAndScore(final Players players) {
        players.getPlayers()
                .forEach(this::printPlayerCardAndScore);
    }

    private void printPlayerCardAndScore(final Player player) {
        outputView.printPlayerCardAndScore(player);
    }

    private void printGameResult(final Players players) {
        Map<String, Integer> playerBetMoneyResults = gameResult.makePlayerBetMoneyResults(players);
        int dealerProfit = gameResult.calculateDealerBetMoneyResult(playerBetMoneyResults);

        outputView.printGameResult(dealerProfit, playerBetMoneyResults);
    }
}
