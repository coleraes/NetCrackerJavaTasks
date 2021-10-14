package com.example.inputpage.repos;

import java.util.List;
import com.example.inputpage.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByText(String text);

}