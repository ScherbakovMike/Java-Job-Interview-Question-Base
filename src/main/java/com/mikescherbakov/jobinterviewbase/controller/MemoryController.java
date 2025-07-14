package com.mikescherbakov.jobinterviewbase.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MemoryController {

    private final List<byte[]> longLivedList = new ArrayList<>();

    @GetMapping("/allocate")
    public String allocateMemory(@RequestParam int megabytes, @RequestParam(defaultValue="short") String type) {
        System.out.println("Allocating " + megabytes + " MB of " + type + "-lived memory.");
        byte[] memoryBlock = new byte[megabytes * 1024 * 1024];

        if ("long".equalsIgnoreCase(type)) {
            longLivedList.add(memoryBlock);
        }
        // Short-lived objects (memoryBlock) will simply go out of scope
        // and become candidates for garbage collection.

        return "Allocated " + megabytes + " MB. Long-lived objects count: " + longLivedList.size();
    }

    @GetMapping("/clear")
    public String clearMemory() {
        System.out.println("Clearing long-lived memory. Count: " + longLivedList.size());
        longLivedList.clear();
        // Trigger GC explicitly to see the effect immediately (for demo purposes)
        System.gc();
        return "Long-lived list cleared.";
    }
}
