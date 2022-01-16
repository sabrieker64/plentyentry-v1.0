package at.commodussolutions.plentyentry.user.confirmation.token.service.impl;

import at.commodussolutions.plentyentry.user.confirmation.token.beans.ConfirmationToken;
import at.commodussolutions.plentyentry.user.confirmation.token.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ConfirmationTokenServiceImpl {

    @Autowired
    private ConfirmationTokenRepository repository;

    public ConfirmationToken saveConfirmationToken(ConfirmationToken token) {
        return repository.save(token);
    }


    public Optional<ConfirmationToken> getByToken(String token){
        return repository.findByToken(token);
    }


    public int setConfirmedAt(String token){
        return repository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
