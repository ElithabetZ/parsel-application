package com.pingwit.parsel.service;

import org.springframework.stereotype.Service;
import com.pingwit.parsel.entity.Route;
import com.pingwit.parsel.repository.RouteRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final BeanUtilService beanUtilService;
    public RouteService(RouteRepository routeRepository, BeanUtilService beanUtilService) {

        this.routeRepository = routeRepository;
        this.beanUtilService = beanUtilService;
    }

    @Transactional
    public Route save(@Valid Route route){
        return routeRepository.save(route);
    };

    @Transactional(readOnly = true)
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    @Transactional
    public Route update(Route route) {
        Route existing = findById(route.getId())
                .orElseThrow(() -> new IllegalArgumentException(format("Route with such id=%d wasn't found", route.getId())));
        beanUtilService.copyProperties(route, existing);

        return routeRepository.save(existing);
    }
}
