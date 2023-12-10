package com.example.common.service;

public interface SecurityService<U> extends Service {

    String currentUsername();

    U currentUser();

}
