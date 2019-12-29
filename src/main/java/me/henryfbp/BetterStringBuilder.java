package me.henryfbp;

public class BetterStringBuilder {

    StringBuilder stringBuilder;

    public BetterStringBuilder(StringBuilder sb) {
        this.stringBuilder = sb;
    }

    public BetterStringBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    public BetterStringBuilder append(String s) {
        this.stringBuilder.append(s);

        return this;
    }

    public BetterStringBuilder appendWithNewline(String s) {
        return this.append(s + "\n");
    }

    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }
}
