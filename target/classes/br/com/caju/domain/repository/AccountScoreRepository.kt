package br.com.caju.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.com.caju.domain.model.*

interface AccountScoreRepository : JpaRepository<AccountScore, String>
{
    AccountScore findByAccountAndMcc(String accountId, String mcc);
}