package application.controller;

import application.model.Usuario;
import application.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 
    @Operation(summary = "Cadastrar novo usuário")
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Listar usuários")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}