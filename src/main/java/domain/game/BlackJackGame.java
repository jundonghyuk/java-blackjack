package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Deck deck;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.players = new Players(playerNames);
        players.drawTwoCardsAtFirstTime(deck);
    }

    public void drawCard(final String playerName) {
        players.findPlayer(playerName).drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return findDealer().isDealerDraw();
    }

    private Dealer findDealer() {
        return players.findDealer();
    }

    public EnumMap<Outcome, Integer> decideDealerOutcome() {
        Map<String, Outcome> playerOutcome = decidePlayersOutcome();
        EnumMap<Outcome, Integer> dealerOutcome = Outcome.initializeOutcomes();
        for (String key : playerOutcome.keySet()) {
            Outcome outcome = playerOutcome.get(key);
            Outcome dealerEachOutcome = Outcome.reverseOutcome(outcome);
            dealerOutcome.put(dealerEachOutcome, dealerOutcome.get(dealerEachOutcome) + 1);
        }
        return dealerOutcome;
    }

    public Map<String, Outcome> decidePlayersOutcome() {
        return Outcome.decidePlayersOutcome(findDealer().getScore(), this.players.getPlayersWithOutDealer());
    }

    public boolean isEqualOrLargerThanBlackJackNumber(final String playerName) {
        if(players.isEqualOrLargerThanBlackJackNumber(playerName)) {
            return true;
        }
        return false;
    }

    public List<Card> getCards(final String playerName) {
        return players.findPlayer(playerName).getCards();
    }

    public int getScore(final String playerName) {
        return players.findPlayer(playerName).getScore();
    }
}
