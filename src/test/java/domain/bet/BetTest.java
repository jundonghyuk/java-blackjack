package domain.bet;

import domain.result.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BetTest {

    @DisplayName("최초의 배팅금액이 0이하이면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-100, 0, -1})
    void constructBetTest(final int money) {
        assertThrows(IllegalArgumentException.class,
                () -> new Bet(money))
                .getMessage().equals("[ERROR]: 배팅금액은 0원 초과여야 합니다.");
    }

    @DisplayName("블랙잭으로 이겼을 때 배팅금액의 1.5배를 받는다.")
    @Test
    void calculateBetByOutcomeWinWithBlackJackTest() {
        Bet bet = new Bet(1000);
        bet.calculateBetByOutcome(Outcome.WIN, true);
        int expectedMoney = 1500;

        assertEquals(expectedMoney, bet.getMoney());
    }

    @DisplayName("블랙잭이 아닌데 이겼을 때 배팅금액을 받는다.")
    @Test
    void calculateBetByOutcomeWinWithoutBlackJackTest() {
        Bet bet = new Bet(1000);
        bet.calculateBetByOutcome(Outcome.WIN, false);
        int expectedMoney = 1000;

        assertEquals(expectedMoney, bet.getMoney());
    }

    @DisplayName("비겼을 때 배팅금액을 돌려받는다.")
    @Test
    void calculateBetByOutcomeDraw() {
        Bet bet = new Bet(1000);
        bet.calculateBetByOutcome(Outcome.DRAW, false);
        int expectedMoney = 0;

        assertEquals(expectedMoney, bet.getMoney());
    }

    @DisplayName("졌을 때 배팅금액을 잃는다.")
    @Test
    void calculateBetByOutcomeLose() {
        Bet bet = new Bet(1000);
        bet.calculateBetByOutcome(Outcome.LOSE, false);
        int expectedMoney = -1000;

        assertEquals(expectedMoney, bet.getMoney());
    }
}
