package br.com.maxdev.screenmatch.interfaces;

public interface IConvertDataJson {
    <T> T getData(String json, Class<T> data);
}
