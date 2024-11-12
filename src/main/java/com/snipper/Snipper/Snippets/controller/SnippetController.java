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

    //
    @PostMapping
    public Snippets createSnippet(@RequestBody Snippets snippet){
        return snippetService.createSnippet(snippet);
    }

    @GetMapping
    public List<Snippets> getAllSnippets(){
        return snippetService.findAll();
    }

}
