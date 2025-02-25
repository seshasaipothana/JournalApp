package com.seshasayee.jApp.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SentimentData {

    private String email;
    private String sentiment;
}
