package org.example.builder;

public abstract class Burger {
    private String bun;
    private String patty;
    private String sauce;
    private String toppings;

    public Burger(Builder<?> builder) {
        this.bun = builder.bun;
        this.patty = builder.patty;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
    }

    public String getBun() {
        return bun;
    }

    public String getPatty() {
        return patty;
    }

    public String getSauce() {
        return sauce;
    }

    public String getToppings() {
        return toppings;
    }

    public static abstract class Builder<T extends Builder<T>> {
        private String bun;
        private String patty;
        private String sauce;
        private String toppings;

        public T bun(String bun) {
            this.bun = bun;
            return self();
        }

        public T patty(String patty) {
            this.patty = patty;
            return self();
        }

        public T sauce(String sauce) {
            this.sauce = sauce;
            return self();
        }

        public T toppings(String toppings) {
            this.toppings = toppings;
            return self();
        }

        protected abstract T self();

        public abstract Burger build();
    }
}
