package santzin.projeta.dev.model.enums;

public enum StrategyFindContents {
    NEW("NEW"), OLD("OLD"), RELEVANT("RELEVANT");

    private String strategyFindContents;

    StrategyFindContents (String strategy){
        this.strategyFindContents=strategy;
    }

    public String getStrategyFindContents() {
        return this.strategyFindContents;
    }
}
