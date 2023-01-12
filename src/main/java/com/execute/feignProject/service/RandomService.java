package com.execute.feignProject.service;

import java.util.HashMap;

public interface RandomService {

    public int executeRandomsSimultaneously(HashMap<String, String> dataToTreat);
}
