package com.sijibomiaol.skaetAss.context.repository;

import com.sijibomiaol.skaetAss.context.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
    Optional<AccountDetails> findByAccountNumber(String accountNumber);
}
