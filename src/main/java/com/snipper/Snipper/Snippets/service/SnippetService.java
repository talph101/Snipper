package com.snipper.Snipper.Snippets.service;

import com.snipper.Snipper.Snippets.entity.Snippets;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SnippetService {

    Snippets createSnippet(Snippets snippet);

    List<Snippets> findAll();

    Snippets getSnippetById(Long id);
}
