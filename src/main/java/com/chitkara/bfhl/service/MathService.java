package com.chitkara.bfhl.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MathService {

    
    public List<Integer> generateFibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Fibonacci input must be positive");
        }
        
        List<Integer> fibonacci = new ArrayList<>();
        if (n >= 1) fibonacci.add(0);
        if (n >= 2) fibonacci.add(1);
        
        for (int i = 2; i < n; i++) {
            fibonacci.add(fibonacci.get(i - 1) + fibonacci.get(i - 2));
        }
        
        return fibonacci;
    }

    
    public List<Integer> filterPrimes(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Prime input cannot be empty");
        }
        
        return numbers.stream()
                .filter(this::isPrime)
                .collect(Collectors.toList());
    }

    
    public int calculateLCM(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("LCM input cannot be empty");
        }
        
        if (numbers.stream().anyMatch(n -> n <= 0)) {
            throw new IllegalArgumentException("LCM requires positive integers");
        }
        
        int lcm = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            lcm = lcm(lcm, numbers.get(i));
        }
        return lcm;
    }

    
    public int calculateHCF(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("HCF input cannot be empty");
        }
        
        if (numbers.stream().anyMatch(n -> n <= 0)) {
            throw new IllegalArgumentException("HCF requires positive integers");
        }
        
        int hcf = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            hcf = gcd(hcf, numbers.get(i));
        }
        return hcf;
    }

    
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    
    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
