package com.snipper.Snipper.Snippets.repository;

import com.snipper.Snipper.Snippets.entity.Snippets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository extends JpaRepository<Snippets, Long> {
}
