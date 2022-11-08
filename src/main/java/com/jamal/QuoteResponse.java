package com.jamal;

import lombok.Data;

@Data
public class QuoteResponse {
    String quoteText;
    String quoteAuthor;
    String senderName;
    String senderLink;
    String quoteLink;
}
