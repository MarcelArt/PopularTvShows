package com.example.populartvshows.models;

import java.util.ArrayList;
import java.util.List;

public class Tvs {
    ArrayList<Tv> results;

    public List<Tv> getResults() {
        return results;
    }

    public void setResults(ArrayList<Tv> results) {
        this.results = results;
    }

    public Tvs(ArrayList<Tv> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Tvs{" +
                "results=" + results +
                '}';
    }
}
