package com.snipper.Snipper.Snippets.repository;

import com.snipper.Snipper.Snippets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
