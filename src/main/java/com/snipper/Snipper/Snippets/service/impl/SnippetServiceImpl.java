package com.snipper.Snipper.Snippets.service.impl;

import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.repository.SnippetRepository;
import com.snipper.Snipper.Snippets.service.SnippetService;
import com.snipper.Snipper.Snippets.util.CryptoUtil;
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
        try{
            String encryptedCode = CryptoUtil.encrypt(snippet.getCode());
            snippet.setCode(encryptedCode);
        } catch(Exception e){
            throw new RuntimeException("Error encrypting snippet", e);
        }
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
