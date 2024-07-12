package br.com.caju.controller.dto

import java.math.BigDecimal

data class TransferenceCredictRequestDto(
    val accountId: String,
    val totalAmount: BigDecimal,
    val mcc: String,
    val merchant: String,

) {
   
    fun toModel(): TransferenceCredictRequestDto {
        return TransferenceCredictRequestDto(this.accountId, this.totalAmount, this.mcc, this.merchant)
    }
}