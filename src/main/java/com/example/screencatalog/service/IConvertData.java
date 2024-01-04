package com.example.screencatalog.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> classType); //Create interface with generic type return
}
