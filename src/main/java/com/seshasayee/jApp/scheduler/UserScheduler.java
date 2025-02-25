package com.seshasayee.jApp.scheduler;

import com.seshasayee.jApp.cache.AppCache;
import com.seshasayee.jApp.entity.JEntry;
import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.enums.Sentiment;
import com.seshasayee.jApp.model.SentimentData;
import com.seshasayee.jApp.repository.UserRepositoryImpl;
import com.seshasayee.jApp.service.EmailService;
import com.seshasayee.jApp.service.SentimentAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisServices;
    @Autowired
    private AppCache appCache;
    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Scheduled(cron = "0 1 * ? * *")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepositoryImpl.getUserForSA();
        for (User user : users){
            List<JEntry> jEntries = user.getJEntries();
            List<Sentiment> sentiments = jEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments){
                if(sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null){
                SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days " + mostFrequentSentiment).build();
                try{
                    kafkaTemplate.send("weekly-sentiments",sentimentData.getEmail(),sentimentData);
                }
                catch (Exception e){
                    emailService.sendEmail(sentimentData.getEmail(), "Sentiment previous week",sentimentData.getSentiment());
                }

            }
        }
     }
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
