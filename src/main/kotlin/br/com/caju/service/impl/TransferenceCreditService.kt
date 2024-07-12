package br.com.caju.service.impl

import br.com.caju.domain.repository.*
import br.com.caju.domain.model.*
import br.com.caju.controller.dto.*
import org.springframework.stereotype.Service
import br.com.caju.controller.exception.*
import br.com.caju.service.*

@Service
class TransferenceCreditService(private val accountScoreRepository: AccountScoreRepository) : ITransferenceCreditService<Unit> {
    @Transactional
    override fun transferenceCreditValidation(model: TransferenceCredictRequestDto): TransferenceCredictResponseDto {
        var codeTransaction : String
        codeTransaction = "07"
        // essas configurações poderiam ficar um em REDIS ou até mesmo no arquivo de configuração da aplicação como uma variável.
        // também, a implementação de logs para monitoramento seria essencial.
        val mccFood = arrayOf("EATS", "BURGER", "PIZZA");
        val mccMel = arrayOf("RESTAURANT", "PIZZARIA", "LANCHONETE");

        var typeScore : String;
        if (mccFood.contains(model.merchant))
        {
            typeScore = "FOOD"
        }
        else if(mccMel.contains(model.merchant))
        {
            typeScore = "MEAL"
        }
        else
        {
            typeScore = when (model.mcc) {
                "5411" -> "FOOD"
                "5412" -> "FOOD"
                "5811" -> "MEAL"
                "5812" -> "MEAL"
                else => "CASH"
            }
        }

        try
        {
            var accountScore = accountScoreRepository.findByAccountAndMcc(model.accountId, model.mcc).orElseThrow {NotFoundException(notFoundMessage)}

            if (accountScore != null)
            {
                if (accountScore.totalAmount < model.totalAmount)
                {

                    accountScore = accountScoreRepository.findByAccountAndMcc(model.accountId, "CASH").orElseThrow {NotFoundException(notFoundMessage)}

                    if(accountScore == null || accountScore.totalAmount < model.totalAmount)
                    {
                        codeTransaction = "51"
                    }
                    else
                    {
                        accountScore.totalAmount = accountScore.totalAmount - model.totalAmount
                        codeTransaction = "00"
                    }
                }
                else
                {
                    accountScore.totalAmount = accountScore.totalAmount - model.totalAmount
                    codeTransaction = "00"
                }
            }
        }
        catch (e: Exception)
        {
            codeTransaction = "07"
        }
        finally {
            return TransferenceCredictResponseDto(codeTransaction)
        }
    }
}