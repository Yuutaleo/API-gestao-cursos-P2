package application.controller;

import application.model.Aluno;
import application.repository.AlunoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Gerenciamento dos alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Operation(summary = "Listar todos os alunos")
    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @Operation(summary = "Buscar aluno por ID")
    @GetMapping("/{id}")
    public Optional<Aluno> buscar(@PathVariable Long id) {
        return alunoRepository.findById(id);
    }

    @Operation(summary = "Cadastrar novo aluno")
    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Operation(summary = "Atualizar aluno")
    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setTelefone(alunoAtualizado.getTelefone());
            aluno.setDataMatricula(alunoAtualizado.getDataMatricula());
            return alunoRepository.save(aluno);
        }).orElse(null);
    }

    @Operation(summary = "Deletar aluno")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}