package com.cftp.calgaryflamestrackerproject;

import javafx.beans.property.SimpleStringProperty;

public class GraphObject {
    private final SimpleStringProperty scorer;
    private final SimpleStringProperty a1;
    private final SimpleStringProperty a2;


    public GraphObject(String scorer, String a1, String a2) {
        this.scorer = new SimpleStringProperty(scorer);
        this.a1 = new SimpleStringProperty(a1);
        this.a2 = new SimpleStringProperty(a2);
    }

    public String getScorer() {
        return scorer.get();
    }

    public SimpleStringProperty scorerProperty() {
        return scorer;
    }

    public void setScorer(String scorer) {
        this.scorer.set(scorer);
    }

    public String getA1() {
        return a1.get();
    }

    public SimpleStringProperty a1Property() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1.set(a1);
    }

    public String getA2() {
        return a2.get();
    }

    public SimpleStringProperty a2Property() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2.set(a2);
    }


}
