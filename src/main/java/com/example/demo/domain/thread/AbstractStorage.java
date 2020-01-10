package com.example.demo.domain.thread;

public interface AbstractStorage {
    void consume(int num);
    void produce(int num);
}