package za.co.rmb.common.model;

public enum Side {
    BUY, SELL;

    public static Side of(final String side) {
        switch (side) {
            case "BUY":
                return BUY;
            case "SELL":
                return SELL;
            default:
                throw new IllegalArgumentException("invalid side " + side);
        }
    }
}
