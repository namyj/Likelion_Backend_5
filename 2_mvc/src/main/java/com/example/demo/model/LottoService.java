package com.example.demo.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LottoService {
    private int hits = 0;

    public int addHit() {
        return ++hits;
    }

    public List<Integer> nextLottoNumber() {
        List<Integer> listOfNums = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listOfNums.add((int) (Math.random() * 60));
        }

        return listOfNums;
    }


}
