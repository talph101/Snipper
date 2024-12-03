package com.snipper.Snipper.Snippets.service.impl;

import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.repository.SnippetRepository;
import com.snipper.Snipper.Snippets.service.SnippetService;
import com.snipper.Snipper.Snippets.util.CryptoUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return snippetRepository.findAll().stream().map(snippet -> {
            try{
                //Decrypt before returning
                snippet.setCode(CryptoUtil.decrypt(snippet.getCode()));
            } catch (Exception e){
                throw new RuntimeException("Error decrypting snippet", e);
            }

            return snippet;

        }).collect(Collectors.toList());

    }

    @Override
    public Snippets getSnippetById(Long id) {

        Snippets snippet = snippetRepository.findById(id).orElse(null);
        if(snippet != null) {
            try{
                snippet.setCode(CryptoUtil.decrypt(snippet.getCode()));
            } catch (Exception e) {
                throw new RuntimeException("Error decrypting snippet", e);
            }
        }
        return snippet;
    }

    @Override
    public Snippets updateSnippet(Long snippetId, Snippets updatedSnippet) {
        Snippets existingSnippet = snippetRepository.findById(snippetId).orElse(null);
        if(existingSnippet != null) {
            existingSnippet.setLanguage(updatedSnippet.getLanguage());
            try{
                String encryptedCode = CryptoUtil.encrypt(updatedSnippet.getCode());
                existingSnippet.setCode(encryptedCode);
            }catch (Exception e) {
                throw new RuntimeException("Error encrypting code during update", e);
            }
            existingSnippet.setUser(updatedSnippet.getUser());
        }
        return snippetRepository.save(existingSnippet);
    }

    @Override
    public Snippets deleteSnippet(Long id) {
        Snippets snippetToDelete = snippetRepository.findById(id).orElse(null);
        snippetRepository.deleteById(id);
        return snippetToDelete;
    }
}
