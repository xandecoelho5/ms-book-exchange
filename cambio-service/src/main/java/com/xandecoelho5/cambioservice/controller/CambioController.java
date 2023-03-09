package com.xandecoelho5.cambioservice.controller;

import com.xandecoelho5.cambioservice.model.Cambio;
import com.xandecoelho5.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Endpoint")
@RestController
@RequestMapping("cambio-service")
@RequiredArgsConstructor
public class CambioController {

    private final Environment environment;
    private final CambioRepository repository;

    @Operation(summary = "Returns the cambio from a specific amount of money from a currency to another")
    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {
        var cambio = repository.findByFromAndTo(from, to);

        if (cambio == null) throw new RuntimeException("Currency not supported");

        var port = environment.getProperty("local.server.port");
        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = amount.multiply(conversionFactor);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnvironment(port);

        return cambio;
    }
}
