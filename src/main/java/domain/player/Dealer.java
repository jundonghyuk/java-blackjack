package domain.player;

public class Dealer extends Player {
    private static final int STOP_LOWER_BOUND = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isDealerDraw() {
        return getScore() < STOP_LOWER_BOUND;
    }
}
