package com.contable.web.rest;

import com.contable.service.TransaccionRapidaService;
import com.contable.web.rest.dto.ResumenFinancieroDTO;
import com.contable.web.rest.dto.TransaccionRapidaRequestDTO;
import com.contable.web.rest.dto.TransaccionRapidaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransaccionRapidaResource {

    private final TransaccionRapidaService transaccionRapidaService;

    public TransaccionRapidaResource(TransaccionRapidaService transaccionRapidaService) {
        this.transaccionRapidaService = transaccionRapidaService;
    }

    /**
     * POST /api/transacciones-rapidas : Registrar nueva transacción
     */
    @PostMapping("/transacciones-rapidas")
    public ResponseEntity<TransaccionRapidaResponseDTO> registrarTransaccion(@Valid @RequestBody TransaccionRapidaRequestDTO request) {
        TransaccionRapidaResponseDTO response = transaccionRapidaService.registrarTransaccion(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/transacciones-rapidas/resumen : Obtener resumen financiero
     */
    @GetMapping("/transacciones-rapidas/resumen")
    public ResponseEntity<ResumenFinancieroDTO> obtenerResumen() {
        ResumenFinancieroDTO resumen = transaccionRapidaService.obtenerResumen();
        return ResponseEntity.ok(resumen);
    }
}
