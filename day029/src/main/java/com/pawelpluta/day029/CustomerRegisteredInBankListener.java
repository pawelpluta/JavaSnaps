package com.pawelpluta.day029;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class CustomerRegisteredInBankListener {

    @EventListener
    public void consume(CustomerRegisteredInBankEvent event) {
    }
}
