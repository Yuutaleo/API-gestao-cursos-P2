package application.controller;

import application.model.Matricula;
import application.repository.MatriculaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "Matrículas", description = "Gerenciamento de matrículas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Operation(summary = "Listar todas as matrículas")
    @GetMapping
    public List<Matricula> listar() {
        return matriculaRepository.findAll();
    }

    @Operation(summary = "Buscar matrícula por ID")
    @GetMapping("/{id}")
    public Optional<Matricula> buscar(@PathVariable Long id) {
        return matriculaRepository.findById(id);
    }

    @Operation(summary = "Criar nova matrícula")
    @PostMapping
    public Matricula criar(@RequestBody Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Operation(summary = "Atualizar matrícula")
    @PutMapping("/{id}")
    public Matricula atualizar(@PathVariable Long id, @RequestBody Matricula matriculaAtualizada) {
        return matriculaRepository.findById(id).map(matricula -> {
            matricula.setStatus(matriculaAtualizada.getStatus());
            matricula.setDataMatricula(matriculaAtualizada.getDataMatricula());
            if (matriculaAtualizada.getCurso() != null) matricula.setCurso(matriculaAtualizada.getCurso());
            if (matriculaAtualizada.getAluno() != null) matricula.setAluno(matriculaAtualizada.getAluno());
            return matriculaRepository.save(matricula);
        }).orElse(null);
    }

    @Operation(summary = "Deletar matrícula")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        matriculaRepository.deleteById(id);
    }
}