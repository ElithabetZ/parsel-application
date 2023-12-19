package com.pingwit.parsel.api.controlles;

import com.pingwit.parsel.api.converters.RouteConverter;
import com.pingwit.parsel.api.dto.RouteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pingwit.parsel.entity.Route;
import com.pingwit.parsel.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
public class RouteController {

    private final RouteService routeService;
    private final RouteConverter routeConverter;

    public RouteController(RouteService routeService, RouteConverter routeConverter) {

        this.routeService = routeService;
        this.routeConverter = routeConverter;
    }

    @PostMapping
    public ResponseEntity<RouteDto> save(@RequestBody RouteDto dto) {
        ResponseEntity<RouteDto> response;
        Route saved = routeService.save(routeConverter.toModel(dto));
        if (saved == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(routeConverter.toDto(saved), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<RouteDto> update(@RequestBody RouteDto dto) {
        Route updated = routeService.update(routeConverter.toModel(dto));
        return new ResponseEntity<>(routeConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getById(@PathVariable Long id){
        return routeService.findById(id)
                .map(result -> new ResponseEntity<>(routeConverter.toDto(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }
}
