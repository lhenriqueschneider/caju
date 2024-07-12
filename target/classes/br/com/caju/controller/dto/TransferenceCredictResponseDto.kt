package br.com.caju.controller.dto

import java.math.BigDecimal

data class TransferenceCredictResponseDto(
    val transferenceCode: String
) {

    fun toModel(): TransferenceCredictResponseDto {
        return TransferenceCredictResponseDto(this.transferenceCode)
    }
}