package com.pawelpluta.day029;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CustomerRegistration {

    private final BankRepository bankRepository;
    private final ApplicationEventPublisher eventPublisher;

    CustomerRegistration(BankRepository bankRepository, ApplicationEventPublisher eventPublisher) {
        this.bankRepository = bankRepository;
        this.eventPublisher = eventPublisher;
    }

    public RegistrationStatus register(RegisterCustomerCommand command) {
        Optional<Bank> foundBank = bankRepository.findById(command.bankId());
        if (foundBank.isPresent()) {
            foundBank.ifPresent(bank -> {
                Bank updatedBank = bank.addCustomer(command.customer().customerAsModel());
                bankRepository.save(updatedBank);
                emit(updatedBank.events());
            });
            return RegistrationStatus.SUCCESS;
        }
        return RegistrationStatus.FAILED;
    }

    private void emit(List<Event> events) {
        events.forEach(eventPublisher::publishEvent);
    }
}
