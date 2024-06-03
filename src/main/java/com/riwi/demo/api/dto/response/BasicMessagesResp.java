package com.riwi.demo.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicMessagesResp {
    private String message_id;
    private String message_content;
    private LocalDate send_date;
}
