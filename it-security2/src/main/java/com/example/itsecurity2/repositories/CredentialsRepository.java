package com.example.itsecurity2.repositories;

import com.example.itsecurity2.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Credentials findByUserName(String userName);
}
