package com.example.concurrency.dto;

public record CouponIssueDto(
        long fromUserId,
        long targetOwnerId
) {
}