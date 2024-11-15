package com.snipper.Snipper.Snippets.service.impl;

import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.repository.SnippetRepository;
import com.snipper.Snipper.Snippets.service.SnippetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetServiceImpl implements SnippetService {
    private SnippetRepository snippetRepository;

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public Snippets createSnippet(Snippets snippet) {
        return snippetRepository.save(snippet);
    }

    @Override
    public List<Snippets> findAll() {
        return snippetRepository.findAll();
    }

    @Override
    public Snippets getSnippetById(Long id) {
        return snippetRepository.findById(id).orElse(null);
    }
}
