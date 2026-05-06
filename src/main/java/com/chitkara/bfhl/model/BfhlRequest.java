package com.chitkara.bfhl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BfhlRequest {

    private Integer fibonacci;
    private List<Integer> prime;
    private List<Integer> lcm;
    private List<Integer> hcf;

    @JsonProperty("AI")
    private String AI;

    // No Args Constructor
    public BfhlRequest() {
    }

    // All Args Constructor
    public BfhlRequest(Integer fibonacci, List<Integer> prime, List<Integer> lcm,
                       List<Integer> hcf, String AI) {
        this.fibonacci = fibonacci;
        this.prime = prime;
        this.lcm = lcm;
        this.hcf = hcf;
        this.AI = AI;
    }

    // Getters and Setters
    public Integer getFibonacci() {
        return fibonacci;
    }

    public void setFibonacci(Integer fibonacci) {
        this.fibonacci = fibonacci;
    }

    public List<Integer> getPrime() {
        return prime;
    }

    public void setPrime(List<Integer> prime) {
        this.prime = prime;
    }

    public List<Integer> getLcm() {
        return lcm;
    }

    public void setLcm(List<Integer> lcm) {
        this.lcm = lcm;
    }

    public List<Integer> getHcf() {
        return hcf;
    }

    public void setHcf(List<Integer> hcf) {
        this.hcf = hcf;
    }

    public String getAI() {
        return AI;
    }

    public void setAI(String AI) {
        this.AI = AI;
    }

    @Override
    public String toString() {
        return "BfhlRequest{" +
                "fibonacci=" + fibonacci +
                ", prime=" + prime +
                ", lcm=" + lcm +
                ", hcf=" + hcf +
                ", AI='" + AI + '\'' +
                '}';
    }
}