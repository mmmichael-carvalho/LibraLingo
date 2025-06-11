package com.libras.backend.controller;

import com.libras.backend.model.Usuario;
import com.libras.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping     // ===== 1) LISTAR TODOS =====
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")     // ===== 2) BUSCAR POR ID =====
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)               // Se existir, retorna 200 + body
                .orElse(ResponseEntity.notFound().build()); // Se não existir, retorna 404
    }

    @PostMapping     // ===== 3) CRIAR NOVO USUÁRIO =====
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.salvar(usuario);
        // Cria a URI “/usuarios/{id}” para ser colocada no header “Location”
        URI uri = URI.create("/usuarios/" + salvo.getId());
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")     // ===== 4) ATUALIZAR USUÁRIO EXISTENTE =====
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuarioAtualizado
    ) {
        return usuarioService.buscarPorId(id)         // Primeiro, tenta buscar o usuário existente
                .map(usuarioExistente -> {
                    // Se existir, copia os campos que podem ser alterados
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    // Salva no banco
                    Usuario salvo = usuarioService.salvar(usuarioExistente);
                    return ResponseEntity.ok(salvo); // Retorna 200 + JSON do usuário atualizado
                })
                .orElse(ResponseEntity.notFound().build()); // Se não existir, retorna 404
    }

    @DeleteMapping("/{id}")     // ===== 5) REMOVER USUÁRIO =====
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Opcional: poderíamos verificar se existe antes de chamar deletar()
        boolean existe = usuarioService.buscarPorId(id).isPresent();
        if (!existe) {
            return ResponseEntity.notFound().build(); // 404 se não existir
        }
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
