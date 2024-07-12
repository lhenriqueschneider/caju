package br.com.caju.service

public interface ITransferenceCreditService<T> {
    fun transferenceCreditValidation(model: T): T
}