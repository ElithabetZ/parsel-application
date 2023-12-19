package com.pingwit.parsel.api.converters;

import com.pingwit.parsel.api.dto.RouteDto;
import com.pingwit.parsel.entity.Route;
import com.pingwit.parsel.entity.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class RouteConverter {

    public RouteDto toDto(Route route){
        return RouteDto.builder()
                .id(route.getId())
                .sender(route.getSender())
                .receiver(route.getReceiver())
                .status(route.getStatus().getStatus())
                .build();
    }

    public Route toModel(RouteDto dto){
        Route model = Route.builder()
                .id(dto.getId())
                .sender(dto.getSender())
                .receiver(dto.getReceiver())
                .build();
        if(dto.getStatus() != null){model.setStatus(Status.valueOf(dto.getStatus()));}
        return model;
    }
}
