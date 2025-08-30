package com.app.dvm.records;

import com.app.dvm.enums.SubscriptionType;

public record DealerRecord(
        Long dealerId,
        String dealerName,
        String dealerEmail,
        SubscriptionType subscriptionType
) {}
