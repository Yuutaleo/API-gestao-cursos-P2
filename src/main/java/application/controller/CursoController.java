package application.controller;

import application.model.Curso;
import application.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Gerenciamento dos cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Listar todos os cursos")
    @GetMapping
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Operation(summary = "Buscar curso por ID")
    @GetMapping("/{id}")
    public Optional<Curso> buscar(@PathVariable Long id) {
        return cursoRepository.findById(id);
    }

    @Operation(summary = "Cadastrar novo curso")
    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @Operation(summary = "Atualizar curso")
    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoAtualizado.getNome());
            curso.setDescricao(cursoAtualizado.getDescricao());
            curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
            curso.setStatus(cursoAtualizado.getStatus());
            return cursoRepository.save(curso);
        }).orElse(null);
    }

    @Operation(summary = "Deletar curso")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }
}