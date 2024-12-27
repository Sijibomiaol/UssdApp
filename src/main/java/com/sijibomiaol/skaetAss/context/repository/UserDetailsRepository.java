package com.sijibomiaol.skaetAss.context.repository;

import com.sijibomiaol.skaetAss.context.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByPhoneNumber(String phoneNumber);
}
