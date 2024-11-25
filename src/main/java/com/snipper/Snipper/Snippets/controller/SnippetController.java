package com.snipper.Snipper.Snippets.controller;

import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.service.SnippetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    //when created you want to encrypt code snippet
    //needs a Key or encryption password
    @PostMapping
    public Snippets createSnippet(@RequestBody Snippets snippet){
        return snippetService.createSnippet(snippet);
    }

    //when you get you want to decrypt
    @GetMapping
    public List<Snippets> getAllSnippets(){
        return snippetService.findAll();
    }

    @GetMapping("{id}")
    public Snippets getSnippetById(@PathVariable Long id) {
        return snippetService.getSnippetById(id);
    }



}
