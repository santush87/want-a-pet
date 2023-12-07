package com.martinaleksandrov.wantapet.services.schedulers;

import com.martinaleksandrov.wantapet.services.AdoptedPetsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdoptedPetsScheduler {

    private final AdoptedPetsService adoptedPetsService;
    private final Logger LOGGER = LoggerFactory.getLogger(AdoptedPetsScheduler.class);

    // Scheduled task to mark pets every day at 7am and 7pm
    @Scheduled(cron = "0 0 7,19 * * *")
    public void markAdoptedPets() {

        LOGGER.info("Marking adopted pets...");

        for (String pet : this.adoptedPetsService.findAllAdoptedPets()) {
            LOGGER.info(pet);
        }
        LOGGER.info("END of marking adopted pets...");
    }


}
