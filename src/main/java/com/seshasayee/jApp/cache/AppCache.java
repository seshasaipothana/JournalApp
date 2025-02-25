package com.seshasayee.jApp.cache;

import com.seshasayee.jApp.entity.ConfigJournalAppEntity;
import com.seshasayee.jApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum Keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    //This will work as in-memory cache.We'll extract all the data from DB all put it here
    public Map<String,String> appCACHE;



   @PostConstruct
   public void init(){
       appCACHE = new HashMap<>();
       List<ConfigJournalAppEntity> list = configJournalAppRepository.findAll();
       for (ConfigJournalAppEntity configJournalAppEntity : list){
           appCACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
       }
   }
}
