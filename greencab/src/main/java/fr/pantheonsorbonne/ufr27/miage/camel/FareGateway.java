package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Fare;
import fr.pantheonsorbonne.ufr27.miage.service.FareService;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FareGateway {

    @Inject
    FareService fareService;

    public Fare register(Fare fare) {

        return fareService.register(fare);
    }


}

