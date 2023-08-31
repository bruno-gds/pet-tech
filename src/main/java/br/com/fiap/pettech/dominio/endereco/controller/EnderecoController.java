package br.com.fiap.pettech.dominio.endereco.controller;

import br.com.fiap.pettech.dominio.endereco.dto.EnderecoPessoaDTO;
import br.com.fiap.pettech.dominio.endereco.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 17/08/2023
 * Project Name: pet-tech
 */

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoPessoaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var enderecos = enderecoService.findAll(pageRequest);

        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoPessoaDTO> findById(@PathVariable Long id) {
        var endereco = enderecoService.findById(id);

        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<EnderecoPessoaDTO> save(@Valid @RequestBody EnderecoPessoaDTO dto) {
        var endereco = enderecoService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand((endereco.id())).toUri();

        return ResponseEntity.created(uri).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoPessoaDTO> update(
            @Valid
            @RequestBody EnderecoPessoaDTO dto,
            @PathVariable Long id) {
        var endereco = enderecoService.update(id, dto);

        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
