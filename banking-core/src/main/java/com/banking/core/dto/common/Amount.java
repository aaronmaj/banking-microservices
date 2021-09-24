package com.banking.core.dto.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class Amount {
    private int value;
    private  Currency currency;


}
