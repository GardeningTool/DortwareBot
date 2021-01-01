package dev.gardeningtool.dortwarebot.event.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class EventGatewayPing {

    private long oldPing, newPing;

}
