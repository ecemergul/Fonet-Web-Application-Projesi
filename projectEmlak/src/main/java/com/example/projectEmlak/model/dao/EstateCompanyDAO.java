package com.example.projectEmlak.model.dao;

import com.example.projectEmlak.model.EstateCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstateCompanyDAO extends JpaRepository<EstateCompany, Long> {

    Optional<EstateCompany> findByAgentUsernameIgnoreCase(String username);
    Optional<EstateCompany> findByAgentEmailIgnoreCase(String email);

    EstateCompany findByAgentUsername(String agentUsername);


}
