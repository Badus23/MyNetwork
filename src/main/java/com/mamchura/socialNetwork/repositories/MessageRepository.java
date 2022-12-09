package com.mamchura.socialNetwork.repositories;

import com.mamchura.socialNetwork.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
