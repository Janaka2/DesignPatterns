package org.example.creational.builder;

public class VeggieBurger extends Burger {
    private final boolean vegan;

    public VeggieBurger(Builder builder) {
        super(builder);
        this.vegan = builder.vegan;
    }

    public boolean isVegan() {
        return vegan;
    }

    public static class Builder extends Burger.Builder<Builder> {
        private boolean vegan;

        public Builder vegan(boolean vegan) {
            this.vegan = vegan;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public VeggieBurger build() {
            validateRequiredFields();
            return new VeggieBurger(this);
        }
    }
}
