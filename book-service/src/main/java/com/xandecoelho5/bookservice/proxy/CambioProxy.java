package com.xandecoelho5.bookservice.proxy;

import com.xandecoelho5.bookservice.response.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service", url = "localhost:8000/cambio-service")
public interface CambioProxy {

    @GetMapping("/{amount}/{from}/{to}")
    Cambio getCambio(@PathVariable Double amount, @PathVariable String from, @PathVariable String to);
}
