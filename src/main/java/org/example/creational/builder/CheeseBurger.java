package org.example.creational.builder;
public class CheeseBurger extends Burger {
    private String cheese;

    public CheeseBurger(Builder builder) {
        super(builder);
        this.cheese = builder.cheese;
    }

    public String getCheese() {
        return cheese;
    }

    public static class Builder extends Burger.Builder<Builder> {
        private String cheese;

        public Builder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public CheeseBurger build() {
            return new CheeseBurger(this);
        }
    }
}
