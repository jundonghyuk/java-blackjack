package view;

import domain.deck.Card;
import domain.game.Outcome;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDistributeCard(final List<String> names) {
        final String joinedName = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinedName);
    }

    public static void printDealerCard(final Card card) {
        final String rank = card.getRank().getRank();
        final String suit = card.getSuit().getSuit();
        System.out.printf("딜러: %s%s%n", rank, suit);
    }

    public static void printPlayerCard(final String playerName, final List<Card> cards) {
        String toStringCards = toStringCards(cards);

        System.out.printf("%s: %s%n", playerName, toStringCards);
    }

    private static String toStringCards(final List<Card> cards) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        cards.forEach(card -> {
            String temp = card.getRank().getRank() + card.getSuit().getSuit();
            stringJoiner.add(temp);
        });
        return stringJoiner.toString();
    }

    public static void printOneMoreCard(final String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
    }

    public static void printDealerDrawCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(final String name, final List<Card> cards, final int score) {
        System.out.printf("%s 카드: %s - 결과: %s%n", name, toStringCards(cards), score);
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printGameResult(final EnumMap<Outcome, Integer> dealerOutcome, final Map<String, Outcome> playerOutcome) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerOutcome);
        printPlayerResult(playerOutcome);
    }

    private static void printDealerResult(final EnumMap<Outcome, Integer> dealerOutcome) {
        printGameEachResult(
                "딜러",
                dealerOutcome.get(Outcome.WIN),
                dealerOutcome.get(Outcome.DRAW),
                dealerOutcome.get(Outcome.LOSE)
        );
    }

    private static void printPlayerResult(final Map<String, Outcome> playerOutcome) {
        playerOutcome.keySet().forEach(name ->
                printEachPlayerResult(playerOutcome, name)
        );
    }

    private static void printEachPlayerResult(final Map<String, Outcome> playerOutcome, final String name) {
        if (playerOutcome.get(name).equals(Outcome.WIN)) {
            printGameEachResult(name, 1, 0, 0);
        }
        if (playerOutcome.get(name).equals(Outcome.DRAW)) {
            printGameEachResult(name, 0, 1, 0);
        }
        if (playerOutcome.get(name).equals(Outcome.LOSE)) {
            printGameEachResult(name, 0, 0, 1);
        }
    }

    private static void printGameEachResult(final String playerName, final int win, final int draw, final int lose) {
        System.out.printf("%s: %s승 %s무 %s패%n", playerName, win, draw, lose);
    }
}
