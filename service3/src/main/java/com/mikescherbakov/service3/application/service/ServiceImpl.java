package com.mikescherbakov.service3.application.service;

import com.mikescherbakov.service3.domain.port.in.*;
import org.springframework.stereotype.*;

@Service
public class ServiceImpl implements UseCase {

    @Override
    public String getPage() {
        return "Hello from Service3! This is the final response in the chain.";
    }
}