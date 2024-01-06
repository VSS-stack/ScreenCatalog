package com.example.screencatalog.service;

/* Interface with a method that receive a json string and a generic object and return a generic object */
public interface IConvertData {
    <T> T getData(String json, Class<T> classType); //Create interface with generic type return
}
